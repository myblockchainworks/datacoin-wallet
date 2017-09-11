/**
 * 
 */
package com.aequalis.datacoin.main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aequalis.datacoin.blockchainapi.WebAPICall;
import com.aequalis.datacoin.dto.AccountDTO;
import com.aequalis.datacoin.dto.BuyTokenDTO;
import com.aequalis.datacoin.dto.LoginDTO;
import com.aequalis.datacoin.dto.SendTokenDTO;
import com.aequalis.datacoin.dto.UserDTO;
import com.aequalis.datacoin.model.TransactionLog;
import com.aequalis.datacoin.model.User;
import com.aequalis.datacoin.service.TransactionLogService;
import com.aequalis.datacoin.service.UserService;

/**
 * @author leoanbarasanm
 *
 */
@RestController
@RequestMapping(value = "/mobile/restApi")
public class MobileWebController {

	@Autowired
	UserService userService;
	
	@Autowired
	TransactionLogService transactionLogService;
	
	@RequestMapping(value = "user/createAccount", method = RequestMethod.POST)
	public String registerationDevice(@RequestBody AccountDTO account) {
		JSONObject json = new JSONObject();
		User availableUser = userService.findByUsername(account.getUsername());
		if (availableUser == null) {
			
			UserDTO userDTO = WebAPICall.createAccount(account.getPassword());
			if (userDTO != null) {
				User user = new User();
				user.setUsername(account.getUsername());
				user.setFullname(account.getFullname());
				user.setPassword(account.getPassword());
				user.setContactnumber(account.getContactnumber());
				user.setEmail(account.getEmail());
				user.setBcaddress(userDTO.getAddress());
				user.setKeystore(userDTO.getKeystore());
				user.setPrivatekey(userDTO.getPrivateKey());
				userService.addUser(user);
				json.put("result", true);
				json.put("message", "User account created successfully!");
				json.put("publicAddress", userDTO.getAddress());
				json.put("keystore", userDTO.getKeystore());
				json.put("privateKey", userDTO.getPrivateKey());
			} else {
				json.put("result", false);
				json.put("message", "Failed to create user account in blockchain, Please try again!");
			}
			
		} else {
			json.put("result", false);
			json.put("message", "Username is not available, Please try again!");
		}
		return json.toString();
	}
	
	@RequestMapping(value = "user/getAccountDetail", method = RequestMethod.POST)
	public String getAccountDetail(@RequestBody LoginDTO login) {
		JSONObject json = new JSONObject();
		User user = userService.loginUser(login.getUsername(), login.getPassword());
		if (user != null) {
			json.put("result", true);
			json.put("publicAddress", user.getBcaddress());
			json.put("keystore", user.getKeystore());
			json.put("privateKey", user.getPrivatekey());
		} else {
			json.put("result", false);
			json.put("message", "Invalid username and password, Please try again!");
		}
		
		return json.toString();
	}
	
	@RequestMapping(value = "user/getAccountBalance", method = RequestMethod.GET)
	public String getAccountDetail(@RequestParam("publicAddress") String publicAddress) {
		JSONObject json = new JSONObject();
		if (publicAddress != null && publicAddress.startsWith("0x")) {
			json.put("result", true);
			json.put("publicAddress", publicAddress);
			BigDecimal tokenBalance = new BigDecimal(0);
			String tokens = WebAPICall.myTokenBalance(publicAddress);
			if (tokens != null) {
				tokenBalance = new BigDecimal(tokens);
			}
			json.put("tokenBalance", tokenBalance);
			BigDecimal myBlanace = getMyBalance(publicAddress);
			json.put("etherBalance", myBlanace.setScale(8, BigDecimal.ROUND_UP));
		} else {
			json.put("result", false);
			json.put("message", "Invalid public address, Please try again!");
		}
		
		return json.toString();
	}
	
	@RequestMapping(value = "user/buyDataCoinToken", method = RequestMethod.POST)
	public String buyDataCoinToken(@RequestBody BuyTokenDTO token) {
		JSONObject json = new JSONObject();
		BigDecimal etherAmount = new BigDecimal(token.getEtherAmount());
		BigDecimal myBalance = getMyBalance(token.getPublicAddress());
		
		if (myBalance != null && myBalance.compareTo(etherAmount) > 0) {
			String privateKey = token.getPrivateKey();
			if (privateKey != null && !privateKey.isEmpty()) {
				privateKey = privateKey.substring(2, privateKey.length());
			} 
			String result = WebAPICall.buyToken(token.getPublicAddress(), privateKey, token.getEtherAmount());
			if (result != null && !result.contains("Error:")) {
				TransactionLog transactionLog = new TransactionLog();
				transactionLog.setTransactiondate(new Date());
				transactionLog.setFromaddress(token.getPublicAddress());
				transactionLog.setToaddress(WebAPICall.icoAddress);
				transactionLog.setAmount(token.getEtherAmount());
				transactionLog.setStatus(true);
				transactionLog.setReferencenumber(result);
				transactionLog.setType("Bought Token");
				transactionLogService.addTransactionLog(transactionLog);
				json.put("result", true);
				json.put("message", "Token bought successfully!");
			} else {
				json.put("result", false);
				json.put("message", "Could not buy token. Please try again.");
			}
		} else {
			json.put("result", false);
			json.put("message", "Insufficient ether in your account!");
		}
		
		return json.toString();
	}
	
	@RequestMapping(value = "user/sendDataCoinToken", method = RequestMethod.POST)
	public String sendDataCoinToken(@RequestBody SendTokenDTO token) {
		JSONObject json = new JSONObject();
		
		if (token.getRecipientAddress() != null && token.getTokenAmount() != null) {
			if(token.getRecipientAddress().length() == 42 && token.getRecipientAddress().startsWith("0x")) {
				if (!token.getRecipientAddress().equals(token.getPublicAddress())) {
					String myBalance = WebAPICall.myTokenBalance(token.getPublicAddress());
					if (myBalance != null && Long.parseLong(myBalance) >= Long.parseLong(token.getTokenAmount())) {
						String privateKey = token.getPrivateKey();
						if (privateKey != null && !privateKey.isEmpty()) {
							privateKey = privateKey.substring(2, privateKey.length());
						}
						String result = WebAPICall.transferToken(token.getPublicAddress(), privateKey, token.getRecipientAddress(), token.getTokenAmount());
						if (result != null && !result.contains("Error:")) {
							TransactionLog transactionLog = new TransactionLog();
							transactionLog.setTransactiondate(new Date());
							transactionLog.setFromaddress(token.getPublicAddress());
							transactionLog.setToaddress(token.getRecipientAddress());
							transactionLog.setAmount(token.getTokenAmount());
							transactionLog.setStatus(true);
							transactionLog.setReferencenumber(result);
							transactionLog.setType("Token Transaction");
							transactionLogService.addTransactionLog(transactionLog);
							json.put("result", true);
							json.put("message", "Token transferred successfully!");
						} else {
							json.put("result", false);
							json.put("message", "Could not transfer token. Please try again.");
						}
					} else {
						json.put("result", false);
						json.put("message", "Insufficient token!");
					}
				} else {
					json.put("result", false);
					json.put("message", "You can't send token to yourself!");
				}
			} else {
				json.put("result", false);
				json.put("message", "Invalid Recipient Address. Please try again.");
			}
		} else {
			json.put("result", false);
			json.put("message", "Recipient address or token amount is empty");
		}
		
		return json.toString();
	}
	
	private BigDecimal getMyBalance(String myAddress) {
		
		BigDecimal wei = new BigDecimal("1000000000000000000");
		BigDecimal myBlanace = new BigDecimal("0");
		String currentBalance = WebAPICall.getBalance(myAddress);
		
		if (currentBalance != null) {
			myBlanace = new BigDecimal(new BigInteger(currentBalance.substring(2, currentBalance.length()), 16));
			myBlanace = myBlanace.divide(wei);
		}
		
		return myBlanace;
	}
}
