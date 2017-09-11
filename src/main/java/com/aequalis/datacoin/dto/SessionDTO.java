/**
 * 
 */
package com.aequalis.datacoin.dto;

/**
 * @author leoanbarasanm
 *
 */
public class SessionDTO {
	String sid;
    String unitsForPrice;
    String unitPrice;
    String deviceId;
    String sessionType;
    boolean isStop;
    String buyer;
    String seller;
    int limit;
    int totalSessionCost;
    int totalDataConsumed;
    String totalSessionTime;
    String startedAt;
    String stoppedAt;
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the unitsForPrice
	 */
	public String getUnitsForPrice() {
		return unitsForPrice;
	}
	/**
	 * @param unitsForPrice the unitsForPrice to set
	 */
	public void setUnitsForPrice(String unitsForPrice) {
		this.unitsForPrice = unitsForPrice;
	}
	/**
	 * @return the unitPrice
	 */
	public String getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the sessionType
	 */
	public String getSessionType() {
		return sessionType;
	}
	/**
	 * @param sessionType the sessionType to set
	 */
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	/**
	 * @return the isStop
	 */
	public boolean isStop() {
		return isStop;
	}
	/**
	 * @param isStop the isStop to set
	 */
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	/**
	 * @return the buyer
	 */
	public String getBuyer() {
		return buyer;
	}
	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	/**
	 * @return the seller
	 */
	public String getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(String seller) {
		this.seller = seller;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the totalSessionCost
	 */
	public int getTotalSessionCost() {
		return totalSessionCost;
	}
	/**
	 * @param totalSessionCost the totalSessionCost to set
	 */
	public void setTotalSessionCost(int totalSessionCost) {
		this.totalSessionCost = totalSessionCost;
	}
	/**
	 * @return the totalDataConsumed
	 */
	public int getTotalDataConsumed() {
		return totalDataConsumed;
	}
	/**
	 * @param totalDataConsumed the totalDataConsumed to set
	 */
	public void setTotalDataConsumed(int totalDataConsumed) {
		this.totalDataConsumed = totalDataConsumed;
	}
	/**
	 * @return the totalSessionTime
	 */
	public String getTotalSessionTime() {
		return totalSessionTime;
	}
	/**
	 * @param totalSessionTime the totalSessionTime to set
	 */
	public void setTotalSessionTime(String totalSessionTime) {
		this.totalSessionTime = totalSessionTime;
	}
	/**
	 * @return the startedAt
	 */
	public String getStartedAt() {
		return startedAt;
	}
	/**
	 * @param startedAt the startedAt to set
	 */
	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}
	/**
	 * @return the stoppedAt
	 */
	public String getStoppedAt() {
		return stoppedAt;
	}
	/**
	 * @param stoppedAt the stoppedAt to set
	 */
	public void setStoppedAt(String stoppedAt) {
		this.stoppedAt = stoppedAt;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SessionDTO [sid=" + sid + ", unitsForPrice=" + unitsForPrice + ", unitPrice=" + unitPrice
				+ ", deviceId=" + deviceId + ", sessionType=" + sessionType + ", isStop=" + isStop + ", buyer=" + buyer
				+ ", seller=" + seller + ", limit=" + limit + ", totalSessionCost=" + totalSessionCost
				+ ", totalDataConsumed=" + totalDataConsumed + ", totalSessionTime=" + totalSessionTime + ", startedAt="
				+ startedAt + ", stoppedAt=" + stoppedAt + "]";
	}
}
