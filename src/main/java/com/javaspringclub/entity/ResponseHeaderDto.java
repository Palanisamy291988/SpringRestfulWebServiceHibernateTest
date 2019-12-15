/**
 * 
 */
package com.javaspringclub.entity;

import java.io.Serializable;

/**
 * @author 327084
 *
 */
public class ResponseHeaderDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518346611051195449L;
	private String status;
	private String responseCode;
	private String responseMessage;
	private String transactionID;
	/**
	 * 
	 */
	public ResponseHeaderDto() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}
	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

}
