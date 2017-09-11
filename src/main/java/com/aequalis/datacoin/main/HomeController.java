/**
 * 
 */
package com.aequalis.datacoin.main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aequalis.datacoin.blockchainapi.WebAPICall;
import com.aequalis.datacoin.dto.SessionDTO;
import com.aequalis.datacoin.dto.UserDTO;
import com.aequalis.datacoin.model.TransactionLog;
import com.aequalis.datacoin.model.User;
import com.aequalis.datacoin.service.TransactionLogService;
import com.aequalis.datacoin.service.UserService;

/**
 * @author anand
 *
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionLogService transactionLogService;
	
	@Autowired(required = true)
	public HttpServletRequest request;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	public HttpSession getSession() {
		if (request != null) {
			return request.getSession();
		}
		return null;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginScreen(Model model) {
	    return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerScreen(Model model) {
	    return "register";
	}
	
	@RequestMapping(value = "/registerprivatekey", method = RequestMethod.GET)
	public String registerprivatekeyScreen(Model model) {
	    return "registerprivatekey";
	}
	
	@RequestMapping(value = "/registerprivatekeyverify", method = RequestMethod.GET)
	public String registerprivatekeyverifyScreen(Model model) {
	    return "registerprivatekeyverify";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutScreen(Model model) {
		HttpSession session = getSession();
		session.removeAttribute("loginUser");
	    return "index";
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
	
	
	@RequestMapping(value = "/proceedRegister", method = RequestMethod.POST)
	public ModelAndView validatePrivateKey(Model model, HttpServletRequest httpServletRequest) {
		String privatekey = httpServletRequest.getParameter("privatekey");
		String address = httpServletRequest.getParameter("address");
		if (privatekey != null && address != null) {
			return new ModelAndView("redirect:/register?privatekey="+privatekey+"&address="+address);
		} else {
			model.addAttribute("errormsg", "please try again!");
			return new ModelAndView("redirect:/registerprivatekeyverify");
		}
	}
	
	@RequestMapping(value = "/validatePrivateKey", method = RequestMethod.POST)
	public ModelAndView proceedRegister(Model model, HttpServletRequest httpServletRequest) {
		String privatekey = httpServletRequest.getParameter("privatekey");
		User user = userService.findByPrivatekey(privatekey);
		if (user == null) {
			String address = WebAPICall.accessAccountUsingPrivateKey(privatekey);
			if (address != null && privatekey.startsWith("0x")) {
				return new ModelAndView("redirect:/registerprivatekeyverify?privatekey="+privatekey+"&address="+address);
			} else {
				model.addAttribute("errormsg", "Invalid Private key, please try again!");
				return new ModelAndView("redirect:/registerprivatekey");
			}
		} else {
			model.addAttribute("errormsg", "User with this private key is already registered, please try again!");
			return new ModelAndView("redirect:/registerprivatekey");
		}
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(Model model, HttpServletRequest httpServletRequest) {
		
		String fullname = httpServletRequest.getParameter("fullname");
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");
		String confirmPassword = httpServletRequest.getParameter("confirmPassword");
		String contactNumber = httpServletRequest.getParameter("contactNumber");
		String email = httpServletRequest.getParameter("email");
		String privatekey = httpServletRequest.getParameter("privatekey");
		String address = httpServletRequest.getParameter("address");
		
		if (password.equals(confirmPassword)) {
			User availableUser = userService.findByUsername(username);
			if (availableUser == null) {
				
				UserDTO userDTO = null;
				if (privatekey != null && address != null && !privatekey.isEmpty() && !address.isEmpty()) {
					userDTO = new UserDTO();
					userDTO.setAddress(address);
					userDTO.setPrivateKey(privatekey);
				} else {
					userDTO = WebAPICall.createAccount(password);
				}
				
				if (userDTO != null) {
					
					User user = new User();
					user.setUsername(username);
					user.setFullname(fullname);
					user.setPassword(password);
					user.setContactnumber(contactNumber);
					user.setEmail(email);
					user.setBcaddress(userDTO.getAddress());
					user.setKeystore(userDTO.getKeystore());
					user.setPrivatekey(userDTO.getPrivateKey());
					userService.addUser(user);
					model.addAttribute("successmsg", "User registration is successful!");
					return new ModelAndView("redirect:/register");
				} else {
					model.addAttribute("errormsg", "Failed to create user account in blockchain, Please try again!");
					return new ModelAndView("redirect:/register");
				}
				
			} else {
				model.addAttribute("errormsg", "Username is not available, Please try again!");
				model.addAttribute("privatekey", privatekey);
				model.addAttribute("address", address);
				return new ModelAndView("redirect:/register");
			}
			
		} else {
			model.addAttribute("errormsg", "Password does not match, Please try again!");
			model.addAttribute("privatekey", privatekey);
			model.addAttribute("address", address);
			return new ModelAndView("redirect:/register");
		}
			
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(Model model, HttpServletRequest httpServletRequest) {
		Date loginTime = new Date();
		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");
		
		User user = userService.loginUser(userName, password);
		if (user != null) {
			HttpSession session = getSession();
			session.setAttribute("loginUser", user.getUserid());
			user.setLastLogin(user.getCurrentLogin());
			user.setCurrentLogin(loginTime);
			userService.addUser(user);
			
			return new ModelAndView("redirect:/mywallet");
		}
		
		model.addAttribute("errormsg", "Invalid user name or password. Please try again.");
		return new ModelAndView("redirect:/");	
		
	}
	
	@RequestMapping(value = "/mywallet", method = RequestMethod.GET)
	public String mywalletScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			user.setTokenBalance(WebAPICall.myTokenBalance(user.getBcaddress()));
			model.addAttribute("currentUser", user);
			
			BigDecimal myBlanace = getMyBalance(user.getBcaddress());
			model.addAttribute("myBalance", myBlanace.setScale(2, BigDecimal.ROUND_UP));
			
			return "mywallet";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/sendtoken", method = RequestMethod.GET)
	public String sendToken(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			model.addAttribute("currentUser", user);
			
			return "sendtoken";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/buytoken", method = RequestMethod.GET)
	public String buyToken(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			model.addAttribute("currentUser", user);
			model.addAttribute("icoAddress", WebAPICall.icoAddress);
			return "buytoken";
		}
		
		return "index";
	}
	
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String myTransactions(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			user.setTokenBalance(WebAPICall.myTokenBalance(user.getBcaddress()));
			model.addAttribute("currentUser", user);
			
			List<TransactionLog> transactionLogs = transactionLogService.findMyTransactions(user.getBcaddress());
			model.addAttribute("transactionLogs", transactionLogs);
			
			return "transactions";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String walletSettingScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			BigDecimal myBlanace = getMyBalance(user.getBcaddress());
			model.addAttribute("myBalance", myBlanace.setScale(2, BigDecimal.ROUND_UP));
			
			List<SessionDTO> sessions = WebAPICall.listSessions();
			model.addAttribute("sessions", sessions);
			
			return "settings";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/buyTokenFromICO", method = RequestMethod.POST)
	public ModelAndView buyTokenFromICO(Model model, HttpServletRequest httpServletRequest) {
		String etheramount = httpServletRequest.getParameter("etheramount");
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			if (etheramount != null) {
				BigDecimal etherAmount = new BigDecimal(etheramount);
				BigDecimal myBalance = getMyBalance(user.getBcaddress());
				
				if (myBalance != null && myBalance.compareTo(etherAmount) > 0) {
					String privateKey = user.getPrivatekey();
					if (privateKey != null && !privateKey.isEmpty()) {
						privateKey = privateKey.substring(2, privateKey.length());
					}
					String result = WebAPICall.buyToken(user.getBcaddress(), privateKey, etheramount);
					if (result != null) {
						if (!result.contains("Error:")) {
							TransactionLog transactionLog = new TransactionLog();
							transactionLog.setTransactiondate(new Date());
							transactionLog.setFromaddress(user.getBcaddress());
							transactionLog.setToaddress(WebAPICall.icoAddress);
							transactionLog.setAmount(etheramount);
							transactionLog.setStatus(true);
							transactionLog.setReferencenumber(result);
							transactionLog.setType("Bought Token");
							transactionLogService.addTransactionLog(transactionLog);
							model.addAttribute("successmsg", "Successfully Bought Token!");
							return new ModelAndView("redirect:/buytoken");
						} else {
							model.addAttribute("errormsg", result);
							return new ModelAndView("redirect:/buytoken");
						}
						
					} else {
						model.addAttribute("errormsg", "Could not buy token. Please try again.");
						return new ModelAndView("redirect:/buytoken");
					}
				} else {
					model.addAttribute("errormsg", "Insufficient ether!");
					return new ModelAndView("redirect:/buytoken");
				}
			} else {
				model.addAttribute("errormsg", "Invalid ether amount. Please try again.");
				return new ModelAndView("redirect:/buytoken");
			}
		}
		
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(value = "/sendTokenToUser", method = RequestMethod.POST)
	public ModelAndView sendTokenToUser(Model model, HttpServletRequest httpServletRequest) {
		String toaddress = httpServletRequest.getParameter("toaddress");
		String tokenamount = httpServletRequest.getParameter("tokenamount");
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			if (toaddress != null && tokenamount != null) {
				if(toaddress.length() == 42 && toaddress.startsWith("0x")) {
					if (!toaddress.equals(user.getBcaddress())) {
						String myBalance = WebAPICall.myTokenBalance(user.getBcaddress());
						
						if (myBalance != null && Long.parseLong(myBalance) >= Long.parseLong(tokenamount)) {
							String privateKey = user.getPrivatekey();
							if (privateKey != null && !privateKey.isEmpty()) {
								privateKey = privateKey.substring(2, privateKey.length());
							}
							String result = WebAPICall.transferToken(user.getBcaddress(), privateKey, toaddress, tokenamount);
							if (result != null) {
								if (!result.contains("Error:")) {
									TransactionLog transactionLog = new TransactionLog();
									transactionLog.setTransactiondate(new Date());
									transactionLog.setFromaddress(user.getBcaddress());
									transactionLog.setToaddress(toaddress);
									transactionLog.setAmount(tokenamount);
									transactionLog.setStatus(true);
									transactionLog.setReferencenumber(result);
									transactionLog.setType("Token Transaction");
									transactionLogService.addTransactionLog(transactionLog);
									model.addAttribute("successmsg", "Successfully Transferred Token!");
									return new ModelAndView("redirect:/sendtoken");
								} else {
									model.addAttribute("errormsg", result);
									return new ModelAndView("redirect:/sendtoken");
								}
								
							} else {
								model.addAttribute("errormsg", "Could not transfer token. Please try again.");
								return new ModelAndView("redirect:/sendtoken");
							}
						} else {
							model.addAttribute("errormsg", "Insufficient token!");
							return new ModelAndView("redirect:/sendtoken");
						}
					} else {
						model.addAttribute("errormsg", "You can't send token to yourself!");
						return new ModelAndView("redirect:/sendtoken");
					}
				} else {
					model.addAttribute("errormsg", "Invalid To Address. Please try again.");
					return new ModelAndView("redirect:/sendtoken");
				}
			}
		}
		
		return new ModelAndView("redirect:/index");
	}
}
