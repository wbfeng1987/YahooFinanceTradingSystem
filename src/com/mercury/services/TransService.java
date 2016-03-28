package com.mercury.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mercury.beans.Stock;
//import com.mercury.beans.Transaction;
import com.mercury.beans.User;
import com.mercury.beans.UserStockTransaction;
import com.mercury.daos.StockDao;
import com.mercury.daos.UserStockTransactionDao;
import com.mercury.daos.UserDao;
import com.mercury.util.CsvUtil;

/**
 * This is the service class for transaction related logic
 * @author Ethan
 *
 */
@Service
public class TransService {
	
	@Autowired
	UserDao ud;
	@Autowired
	StockDao sd;
	@Autowired
	UserStockTransactionDao td;
	@Autowired
	CsvUtil cu;
	
	/**
	 * save a transaction
	 * @param trans
	 */
	//@Transactional
	public void addTransaction(UserStockTransaction trans){
		td.save(trans);
	}

	//@Transactional
//	public void deleteTransaction(UserStockTransaction trans){
//		td.deleteTransaction(trans);
//	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	//@Transactional
//	public List<UserStockTransaction> queryByUser(User user){
//		return td.queryStockByUsername(user.getUsername());
//	}
	
	/**
	 * return all transactions
	 * @return
	 */
	//@Transactional
	public List<UserStockTransaction> queryAll(){
		return td.queryAll();
	}
	
	
	/**
	 * add a pending transaction by appending it to the CSV file
	 * @param trans
	 */
	
	//@Transactional
	public void addPending(UserStockTransaction trans){
		User user = trans.getUser();
		double cash = user.getCash();
		int amount = trans.getQuantity();
		double price = trans.getPrice();
		
		cash = cash - price * amount;
		// can the cash be negative value?
		// if cash is greater or equal than 0, update user and save transaction to pending
		// if cash is less than 0, abort the transaction
		
		if (cash >= 0) {
			user.setCash(cash);
			ud.update(user);
			cu.appendCSV(trans);
		} else{
			System.out.println("User don't have enough balance");
		}
		
	}
	
	/**
	 * read all pending transactions from pending.csv fiel using CsvUtil
	 * @return
	 */
	//@Transactional
	public List<UserStockTransaction> getAllPendings(){
		List<UserStockTransaction> list = cu.parseCSV();
		return list;
	}
	
	/**
	 * get all pending transactions for a user
	 * @param user
	 * @return
	 */
	//@Transactional
	public List<UserStockTransaction> findPendingByUser(User user){
		
		List<UserStockTransaction> list = getAllPendings();
		if (list == null) return null;
		
		List<UserStockTransaction> newList = new ArrayList<UserStockTransaction>();
		for (UserStockTransaction t: list){
			if (t.getUser().getUid() == user.getUid()){
				newList.add(t);
			}
		}
		return newList;
	}
//	
//	
//
	/**
	 * Commit pending transaction in csv file, save it to database, update balance
	 * @param index -- index of the pending transaction
	 */
	//@Transactional
	public void commitPending(int index){
		//Parsing pending to transaction
		//List<UserStockTransaction> transList = getAllPendings();
		List<UserStockTransaction> allpendings = getAllPendings();
		UserStockTransaction tx = allpendings.get(index);
		User user = tx.getUser();
		
		//we will do this in another service
//		Stock stock = tx.getStock();
//		List<OwnershipInfo> ownList = od.findByOwn(user, stock);
//		
//		//Calculate the quantity after transaction
//		int amount = tx.getAmount();
//		if (ownList == null || ownList.size() == 0){			
//			if (amount > 0){
//				OwnershipInfo ois = new OwnershipInfo(user, stock, amount < 0 ? 0 : amount);
//				user.addOwns(ois);
//			}
//		}else {
//			amount = ownList.get(0).getQuantity() + amount;
//			if (amount > 0){
//				for (OwnershipInfo ois: user.getOwns()){
//					if (ois.getOwn().getStock().getSid() == stock.getSid()){
//						ois.setQuantity(amount);
//					}
//				}
//			}else{
//				user.removeOwns(ownList.get(0));
//				od.delete(ownList.get(0));
//			}
//		}
		//Calculate and update balance after transaction
		double cash = user.getCash();
		cash = cash - 5;
		
		// persist the transaction in db only when the cash is greater or equal than 0
		if (cash >= 0) {
			user.setCash(cash);
			addTransaction(tx);			//Save transaction to databse
			ud.update(user);
		}
			
	}
	
	/**
	 * commit a list of pendings by their indicex
	 * @param indexs
	 */
	public void commitPendings(List<Integer> indexs){
		List<UserStockTransaction> allpendings = getAllPendings();
		for(int i: indexs){
			commitPending(i);
			//allpendings.remove(i);
		}
		List<UserStockTransaction> newList = new ArrayList<UserStockTransaction>();
		//List<UserStockTransaction> restore = new ArrayList<UserStockTransaction>();
		
		for (int i=0; i<allpendings.size(); i++){
			if (!indexs.contains(i) ){
				newList.add(allpendings.get(i));
			}
		}
		
		cu.rewriteCSV(newList);
	}
	
	/**
	 * drop a pending transaction and credit the user's cash
	 * @param index -- index of the pending transaction
	 * @param reimberse
	 */
	//Delete pending transaction from csv file
	//@Transactional
	public void dropPending(int index){
		
		List<UserStockTransaction> list = getAllPendings();
		
		UserStockTransaction tran = list.get(index);
		User user = tran.getUser();
		
		//credit back user's cash
		double cash = user.getCash();
		int amount = tran.getQuantity();
		double price = tran.getPrice();
		cash = cash + price * amount;
		
		//if (balance < 0) balance = 0;
		user.setCash(cash);
		ud.update(user);
			
		list.remove(index);
		cu.rewriteCSV(list);
	}
	
	/**
	 * Delete pending transactions and credit back to users
	 * @param indexs
	 */
	//@Transactional
	public void dropPendings(List<Integer> indexs){
		List<UserStockTransaction> list = getAllPendings();
		List<UserStockTransaction> newList = new ArrayList<UserStockTransaction>();
		List<UserStockTransaction> restore = new ArrayList<UserStockTransaction>();
		for (int i=0; i<list.size(); i++){
			if (!indexs.contains(i) ){
				newList.add(list.get(i));
			}else{
				restore.add(list.get(i));
			}
		}
		
		for(UserStockTransaction tran: restore){
//			System.out.println("in the loop!");
//			UserStockTransaction tran = list.get(i);
//			System.out.println(tran);
			User user = tran.getUser();
			
			//credit back user's cash
			double cash = user.getCash();
			int amount = tran.getQuantity();
			double price = tran.getPrice();
			cash = cash + price * amount;
			
			//if (balance < 0) balance = 0;
			user.setCash(cash);
			ud.update(user);
			//list.remove(i);
		}
		
		cu.rewriteCSV(newList);
	}
	
}
