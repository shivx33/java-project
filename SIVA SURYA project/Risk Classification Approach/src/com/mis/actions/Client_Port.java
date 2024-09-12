package com.mis.actions;
import java.util.Random;
public class Client_Port 
{
	Random rr=new Random();
	String str="";
	public String getRouter()
	{
		str="";
		str="Router"+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10));
		return str;
	}
	public int getPort()
	{
		String str="";
		str=String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10));
		return Integer.parseInt(str);
	}
	public String getSourceIP() {
		// TODO Auto-generated method stub
		
		return getInteger()+"."+getInteger()+"."+getInteger()+"."+getInteger();
	}
	private int getInteger() {
		// TODO Auto-generated method stub
		return rr.nextInt(255);
	}
	
}