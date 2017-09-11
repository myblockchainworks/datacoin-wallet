/**
 * 
 */
package com.aequalis.datacoin.dto;

/**
 * @author leoanbarasanm
 *
 */
public class SendTokenDTO {
	private String recipientAddress;
	private String tokenAmount;
	private String publicAddress;
	private String privateKey;
	/**
	 * @return the publicAddress
	 */
	public String getPublicAddress() {
		return publicAddress;
	}
	/**
	 * @param publicAddress the publicAddress to set
	 */
	public void setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
	}
	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * @return the recipientAddress
	 */
	public String getRecipientAddress() {
		return recipientAddress;
	}
	/**
	 * @param recipientAddress the recipientAddress to set
	 */
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	/**
	 * @return the tokenAmount
	 */
	public String getTokenAmount() {
		return tokenAmount;
	}
	/**
	 * @param tokenAmount the tokenAmount to set
	 */
	public void setTokenAmount(String tokenAmount) {
		this.tokenAmount = tokenAmount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendTokenDTO [recipientAddress=" + recipientAddress + ", tokenAmount=" + tokenAmount
				+ ", publicAddress=" + publicAddress + ", privateKey=" + privateKey + "]";
	}
}
