package com.mis.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mis.gui.Attacker;
import com.mis.gui.BGPRouter;
import com.mis.gui.Client;


public class SRx extends Thread {

	private Socket soc;
	private ServerSocket serSoc;
	private ObjectInputStream ois;
	int port;
	Client source;

	public SRx(Client source , int port) {
		this.source = source;
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
			if (str.equals("Rep")) {
				String data=(String)ois.readObject();
				String filename=(String)ois.readObject();
				File file=new File(filename);
				FileOutputStream fos=new FileOutputStream(file);
				fos.write(data.getBytes());
				fos.close();
				JOptionPane.showMessageDialog(null, "File Received and Saved at: "+ file.getAbsolutePath());
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
