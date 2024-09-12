package com.mis.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import com.mis.gui.BGPRouter;
import com.mis.gui.Server;


public class DRx extends Thread {

	private Socket soc;
	private ServerSocket serSoc;
	private ObjectInputStream ois;
	
	Server destination;
	int port;
	Control action;
	boolean flag;
	int len;
	

	public DRx(Server destination, int port, Control action) {
		this.destination=destination;
		this.port = port;
		this.action = action;
		start();
	}

	public void run() {
		try {
			receive();
		} catch (Exception e) {
		}
	}

	public void receive() {
		try {
			serSoc = new ServerSocket(port);
			while (true) {
				soc = serSoc.accept();
				ois = new ObjectInputStream(soc.getInputStream());
				String str = (String) ois.readObject();
				checkStatus(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkStatus(String str) {
		try {
			if (str.equals("Req")) {
				Data data = (Data) ois.readObject();
				Vector<String> vec=new Vector<String>();
				vec.add(""+(++len));
				vec.add(data.sourceIP);
				vec.add(""+data.initTTL);
				vec.add(""+data.TTL);
				vec.add(""+(data.initTTL-data.TTL));
				vec.add(data.reqData);
				vec.add(""+data.sourceport);
				destination.dftIP2HC.addRow(vec);
			} else if (str.equals("CTS")) {

			} else if (str.equals("Data")) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
