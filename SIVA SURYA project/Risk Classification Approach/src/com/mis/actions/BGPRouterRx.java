package com.mis.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import com.mis.gui.BGPRouter;


public class BGPRouterRx extends Thread {

	private Socket soc;
	private ServerSocket serSoc;
	private ObjectInputStream ois;
	int port;
	BGPRouter router;
	Control action;
	boolean flag;
	int len;
	Vector<Vector<String>> txData = new Vector<Vector<String>>();

	public BGPRouterRx(BGPRouter router, int port, Control action) {
		this.router = router;
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
				Vector<String> vec = new Vector<String>();
				vec.add(data.sourceIP);
				vec.add(data.destiIP);
				if (data.routers.size() == 0)
					vec.add(data.destiIP);
				else
					vec.add(data.routers.get(0));
				vec.add("" + data.initTTL);
				data.TTL = data.TTL - 1;
				vec.add("" + data.TTL);
				vec.add(data.reqData);
				router.dftPackinfor.addRow(vec);
				txData.add(vec);

				if (data.routers.size() == 0)
					sendDestination(data);
				else {

					action.sendREQ(data);
				}

			} else if (str.equals("aReq")) {
				sendaRep();

			} else if (str.equals("Data")) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendaRep() {
		// TODO Auto-generated method stub
try {
			
			Socket socket = new Socket("localhost", 1267);
			ObjectOutputStream oos = new ObjectOutputStream(socket
					.getOutputStream());
			oos.writeObject("aRep");
			oos.writeObject(txData);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendDestination(Data data) {
		// TODO Auto-generated method stub
		try {
			int nPort = 7982;
			Socket socket = new Socket(data.destiIP, nPort);
			ObjectOutputStream oos = new ObjectOutputStream(socket
					.getOutputStream());
			oos.writeObject("Req");
			oos.writeObject(data);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
