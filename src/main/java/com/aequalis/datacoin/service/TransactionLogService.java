/**
 * 
 */
package com.aequalis.datacoin.service;

import java.util.List;

import com.aequalis.datacoin.model.TransactionLog;


/**
 * @author anand
 *
 */
public interface TransactionLogService {
	
	public void addTransactionLog(TransactionLog transactionLog);
	
	public List<TransactionLog> findMyTransactions(String useraddress);
}
