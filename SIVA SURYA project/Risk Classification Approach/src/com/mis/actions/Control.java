package com.mis.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Control {

	Data data = new Data();

	public String getRouter() {
		// TODO Auto-generated method stub
		return new Client_Port().getRouter();
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return new Client_Port().getPort();
	}

	public void setProperty(String file, String source, String text) {
		// TODO Auto-generated method stub
		try {
			Properties properties = new Properties();
			FileOutputStream fos = new FileOutputStream(file, true);
			properties.setProperty(source, text);
			properties.store(fos, source);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRouter(JTextArea jtaNeigh) {
		// TODO Auto-generated method stub
		data.routers=new Vector<String>();
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream("Router.properties");
			properties.load(fis);
			fis.close();
			Enumeration<Object> em = properties.keys();
			while (em.hasMoreElements()) {
				String key = (String) em.nextElement();
				data.routers.add(key);
				jtaNeigh.append(key + "\n");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProperty(String file, String key) {
		// TODO Auto-generated method stub
		String value = "";
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			fis.close();
			value = properties.getProperty(key);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public int getPort(String nei) {
		// TODO Auto-generated method stub
		int mydis = 0;
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream("Router.properties");
			properties.load(fis);
			fis.close();
			mydis = Integer.parseInt(properties.getProperty(nei));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mydis;
	}

	public void sendData(String dest, int[] endata) {
		// TODO Auto-generated method stub
		try {
			int nPort = getPort(dest);
			Socket socket = new Socket("localhost", nPort);
			ObjectOutputStream oos = new ObjectOutputStream(socket
					.getOutputStream());
			oos.writeObject("Data");
			oos.writeObject(endata);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSourceIP() {
		// TODO Auto-generated method stub

		return new Client_Port().getSourceIP();
	}

	public void setPackets(JTextArea jtaIPHead, String sourceIP,
			String reqData, String initTTL, String dest,int port) {
		// TODO Auto-generated method stub
		jtaIPHead.append("[" + "\n");
		jtaIPHead.append("Source IP=" + sourceIP + "\n");
		jtaIPHead.append("Destination IP=" + dest + "\n");
		jtaIPHead.append("Initial TTL=" + initTTL + "\n");
		jtaIPHead.append("TTL=" + initTTL + "\n");
		jtaIPHead.append("request Data=" + reqData + "\n");
		jtaIPHead.append("]" + "\n");

		data.sourceIP = sourceIP;
		data.destiIP = dest;
		data.initTTL = Integer.parseInt(initTTL);
		data.TTL = Integer.parseInt(initTTL);
		data.reqData = reqData;
		data.sourceport=port;
		
	}

	public void sendRequest() {
		// TODO Auto-generated method stub
		sendREQ(data);
	}

	public void sendREQ(Data data2) {
		// TODO Auto-generated method stub
		try {
			int port = getPort(data2.routers.get(0));
			data2.routers.remove(0);
			System.out.println("data2:"+data2);
			Socket socket = new Socket("localhost", port);
			ObjectOutputStream oos = new ObjectOutputStream(socket
					.getOutputStream());
			oos.writeObject("Req");
			oos.writeObject(data2);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLocalHost() {
		// TODO Auto-generated method stub
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	Vector<String> legiport;
	Vector<String> legifiles;
	public void setLegimate(DefaultTableModel dftIP2HC, JTextArea jtaLegimate, JTextArea jtaAttack) {
		// TODO Auto-generated method stub
		jtaLegimate.setText("");
		jtaAttack.setText("");
		int hs=getHopCount();
		legiport=new Vector<String>();
		legifiles=new Vector<String>();
		for (int i = 0; i < dftIP2HC.getRowCount(); i++) {
			int hc=Integer.parseInt(dftIP2HC.getValueAt(i, 4).toString().trim());
			String pno=dftIP2HC.getValueAt(i, 0).toString().trim();
			String pip=dftIP2HC.getValueAt(i, 1).toString().trim();
			String req=dftIP2HC.getValueAt(i, 5).toString().trim();
			String port=dftIP2HC.getValueAt(i, 6).toString().trim();
			if(hs==hc)
			{
				jtaLegimate.append("["+pno+","+pip+","+req+"]\n");
				legiport.add(port);
				legifiles.add(req);
			}else{
				jtaAttack.append("["+pno+","+pip+","+req+"]\n");
			}
		}
		
	}

	private int getHopCount() {
		// TODO Auto-generated method stub
		int ret=0;
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream("Router.properties");
			properties.load(fis);
			fis.close();
			Enumeration<Object> em = properties.keys();
			while (em.hasMoreElements()) {
				em.nextElement();
				ret++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public void loadFiles(DefaultTableModel dftFiles) {
		// TODO Auto-generated method stub
		File[] file=new File("ServerDB").listFiles();
		for (int i = 0; i < file.length; i++) {
			dftFiles.addRow(new Object[]{file[i].getName()});
		}
		
		
	}

	public void sendResponse() {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < legiport.size(); i++) {
				System.out.println("Send Res");
				File file=new File("ServerDB/"+legifiles.get(i));
				FileInputStream fis=new FileInputStream(file);
				byte[] b=new byte[fis.available()];
				fis.read(b);
				String str=new String(b);
				int port=Integer.parseInt(legiport.get(i));
				
				Socket socket = new Socket("localhost", port);
				ObjectOutputStream oos = new ObjectOutputStream(socket
						.getOutputStream());
				oos.writeObject("Rep");
				oos.writeObject(str);
				oos.writeObject(file.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
