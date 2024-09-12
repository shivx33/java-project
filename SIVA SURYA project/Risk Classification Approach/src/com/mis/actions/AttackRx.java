package com.mis.actions;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import com.mis.gui.Attacker;
import com.mis.gui.BGPRouter;


public class AttackRx extends Thread {

	private Socket soc;
	private ServerSocket serSoc;
	private ObjectInputStream ois;
	int port;
	Attacker attacker;

	public AttackRx(Attacker attacker, int port) {
		this.attacker = attacker;
		this.port = port;

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
			if (str.equals("aRep")) {
				Vector<Vector<String>> vec=(Vector<Vector<String>>)ois.readObject();
				for (int i = 0; i < vec.size(); i++) {
					attacker.dftPackinfor.addRow(vec.get(i));
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
