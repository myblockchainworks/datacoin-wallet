/**
 * 
 */
package com.aequalis.datacoin.service;

import java.util.List;

import com.aequalis.datacoin.model.User;

/**
 * @author anand
 *
 */
public interface UserService {
	public void addUser(User user);
	
	public User loginUser(String username, String password);
	
	public User findByUserid(Long userid);
	
	public User findByUsername(String username);
	
	public 	List<User> findAll();
	
	public User findByBcaddress(String bcaddress);
	
	public User findByPrivatekey(String privatekey);
	
}
