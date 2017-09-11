package com.aequalis.datacoin.dto;

public class AccountDTO {
	private String username;
	private String password;
	private String fullname;
	private String contactnumber;
	private String email;
	private String address;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * @return the contactnumber
	 */
	public String getContactnumber() {
		return contactnumber;
	}
	/**
	 * @param contactnumber the contactnumber to set
	 */
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountDTO [username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", contactnumber=" + contactnumber + ", email=" + email + ", address=" + address + "]";
	}
}
