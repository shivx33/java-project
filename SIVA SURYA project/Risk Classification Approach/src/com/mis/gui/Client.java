/*
 * Client.java
 *
 * Created on __DATE__, __TIME__
 */

package com.mis.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.mis.actions.Control;
import com.mis.actions.SRx;

/**
 * 
 * @author __USER__
 */
public class Client extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Control action;
	String sourceIP;
	int port;

	/** Creates new form Client */
	public Client() {
		initComponents();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		action = new Control();
		sourceIP = action.getSourceIP();
		txtSource.setText(sourceIP);
		port = action.getPort();
		new SRx(this, port);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel2 = new javax.swing.JLabel();
		txtDestination = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jtaData = new javax.swing.JTextArea();
		btnClear = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jtaIPHead = new javax.swing.JTextArea();
		btnPact = new javax.swing.JButton();
		btnSendPacket = new javax.swing.JButton();
		btnExit = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		txtSource = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		txtInitTTL = new javax.swing.JTextField("30");
		jScrollPane3 = new javax.swing.JScrollPane();
		jtaRouter = new javax.swing.JTextArea();
		btnRouter = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		jLabel2.setText("Destination");
		getContentPane().add(jLabel2);
		jLabel2.setBounds(340, 70, 90, 14);
		getContentPane().add(txtDestination);
		txtDestination.setBounds(430, 70, 180, 20);

		jLabel3.setText("Enter File name");
		getContentPane().add(jLabel3);
		jLabel3.setBounds(40, 130, 150, 14);

		jtaData.setColumns(20);
		jtaData.setRows(5);
		jScrollPane1.setViewportView(jtaData);

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(40, 150, 270, 210);

		btnClear.setText("Clear");
		btnClear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearActionPerformed(evt);
			}
		});
		getContentPane().add(btnClear);
		btnClear.setBounds(40, 370, 80, 23);

		jLabel4.setText("IP Header");
		getContentPane().add(jLabel4);
		jLabel4.setBounds(340, 230, 110, 14);

		jtaIPHead.setColumns(20);
		jtaIPHead.setRows(5);
		jScrollPane2.setViewportView(jtaIPHead);

		getContentPane().add(jScrollPane2);
		jScrollPane2.setBounds(340, 250, 280, 180);

		btnPact.setText("Pactorization");
		btnPact.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPactActionPerformed(evt);
			}
		});
		getContentPane().add(btnPact);
		btnPact.setBounds(200, 440, 110, 23);

		btnSendPacket.setText("Send Packets");
		btnSendPacket.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSendPacketActionPerformed(evt);
			}
		});
		getContentPane().add(btnSendPacket);
		btnSendPacket.setBounds(340, 440, 150, 23);

		btnExit.setText("Exit");
		btnExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
		});
		getContentPane().add(btnExit);
		btnExit.setBounds(540, 440, 80, 23);

		jLabel5.setText("Source IP");
		getContentPane().add(jLabel5);
		jLabel5.setBounds(50, 70, 70, 14);
		getContentPane().add(txtSource);
		txtSource.setBounds(120, 70, 190, 20);
		txtSource.setEditable(false);

		jLabel6.setText("Initial TTL");
		getContentPane().add(jLabel6);
		jLabel6.setBounds(40, 410, 60, 14);
		getContentPane().add(txtInitTTL);
		txtInitTTL.setBounds(110, 410, 200, 20);

		jtaRouter.setColumns(20);
		jtaRouter.setRows(5);
		jScrollPane3.setViewportView(jtaRouter);

		getContentPane().add(jScrollPane3);
		jScrollPane3.setBounds(340, 130, 280, 96);

		btnRouter.setText("Routers");
		btnRouter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRouterActionPerformed(evt);
			}
		});
		getContentPane().add(btnRouter);
		btnRouter.setBounds(430, 100, 130, 23);

		setSize(660, 537);
		setTitle("Source::");
		setResizable(false);
		setVisible(true);
	}// </editor-fold>

	// GEN-END:initComponents

	private void btnRouterActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here
		jtaRouter.setText("");
		action.setRouter(jtaRouter);
	}

	private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		System.exit(0);
	}

	private void btnSendPacketActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		action.sendRequest();
	}

	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		jtaData.setText("");
	}

	private void btnPactActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		action.setPackets(jtaIPHead, txtSource.getText(), jtaData.getText(),
				txtInitTTL.getText(), txtDestination.getText(), port);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceAquaTheme");
		SubstanceLookAndFeel
				.setCurrentButtonShaper("org.jvnet.substance.button.StandardButtonShaper");
		
		SubstanceLookAndFeel
				.setCurrentGradientPainter("SpecularGradientPainter");
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Client().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnClear;
	private javax.swing.JButton btnExit;
	private javax.swing.JButton btnPact;
	private javax.swing.JButton btnRouter;
	private javax.swing.JButton btnSendPacket;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextField txtInitTTL;
	private javax.swing.JTextArea jtaData;
	private javax.swing.JTextArea jtaIPHead;
	private javax.swing.JTextArea jtaRouter;
	private javax.swing.JTextField txtDestination;
	private javax.swing.JTextField txtSource;
	// End of variables declaration//GEN-END:variables

}