/**
 * 
 */
package com.aequalis.datacoin.dto;

/**
 * @author leoanbarasanm
 *
 */
public class BuyTokenDTO {
	private String etherAmount;
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
	 * @return the etherAmount
	 */
	public String getEtherAmount() {
		return etherAmount;
	}
	/**
	 * @param etherAmount the etherAmount to set
	 */
	public void setEtherAmount(String etherAmount) {
		this.etherAmount = etherAmount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BuyTokenDTO [etherAmount=" + etherAmount + ", publicAddress=" + publicAddress + ", privateKey="
				+ privateKey + "]";
	}
}
