package com.mis.actions;

import java.io.Serializable;
import java.util.Vector;

public class Data implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String sourceIP;
	public int initTTL;
	public int TTL;
	public String reqData;
	public String destiIP;
	public Vector<String> routers;
	public int sourceport;
}
