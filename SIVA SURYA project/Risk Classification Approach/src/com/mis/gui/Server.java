/*
 * Destination.java
 *
 * Created on __DATE__, __TIME__
 */

package com.mis.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.mis.actions.Control;
import com.mis.actions.DRx;

/**
 * 
 * @author __USER__
 */
public class Server extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Control action;

	/** Creates new form Destination */
	public Server() {
		initComponents();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		action = new Control();
		action.loadFiles(dftFiles);
		setTitle(action.getLocalHost());
		new DRx(this, 7982, action);
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
		jScrollPane1 = new javax.swing.JScrollPane();
		tblIP2HC = new javax.swing.JTable();
		btnInspection = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jtaAttack = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jtaLegimate = new javax.swing.JTextArea();
		jLabel5 = new javax.swing.JLabel();
		btnResponse = new javax.swing.JButton();
		jScrollPane4 = new javax.swing.JScrollPane();
		tblFiles = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		jLabel2.setText("IP2HC Table");
		getContentPane().add(jLabel2);
		jLabel2.setBounds(30, 40, 230, 14);

		dftIP2HC = new DefaultTableModel();
		tblIP2HC.setModel(dftIP2HC);
		jScrollPane1.setViewportView(tblIP2HC);

		dftIP2HC.addColumn("P.No");
		dftIP2HC.addColumn("Source IP");
		dftIP2HC.addColumn("Initial TTL");
		dftIP2HC.addColumn("TTL");
		dftIP2HC.addColumn("Hop Count");
		dftIP2HC.addColumn("Request");
		dftIP2HC.addColumn("SourcePort");

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(30, 60, 650, 170);

		btnInspection.setText("IDPF Inspection");
		btnInspection.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInspectionActionPerformed(evt);
			}
		});
		getContentPane().add(btnInspection);
		btnInspection.setBounds(130, 250, 170, 30);

		jLabel3.setText("Attacked Packets");
		getContentPane().add(jLabel3);
		jLabel3.setBounds(70, 290, 110, 14);

		jtaAttack.setColumns(20);
		jtaAttack.setRows(5);
		jScrollPane2.setViewportView(jtaAttack);

		getContentPane().add(jScrollPane2);
		jScrollPane2.setBounds(30, 310, 166, 140);

		jLabel4.setText("Legitimate Packets");
		getContentPane().add(jLabel4);
		jLabel4.setBounds(250, 290, 100, 14);

		jtaLegimate.setColumns(20);
		jtaLegimate.setRows(5);
		jScrollPane3.setViewportView(jtaLegimate);

		getContentPane().add(jScrollPane3);
		jScrollPane3.setBounds(210, 310, 166, 140);

		jLabel5.setText("Files");
		getContentPane().add(jLabel5);
		jLabel5.setBounds(410, 240, 90, 14);

		btnResponse.setText("Send Response");
		btnResponse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnResponseActionPerformed(evt);
			}
		});
		getContentPane().add(btnResponse);
		btnResponse.setBounds(410, 460, 270, 23);

		dftFiles = new DefaultTableModel();
		tblFiles.setModel(dftFiles);
		jScrollPane4.setViewportView(tblFiles);
		dftFiles.addColumn("All files");

		getContentPane().add(jScrollPane4);
		jScrollPane4.setBounds(410, 260, 270, 190);

		setSize(710, 537);
		setResizable(false);
		setVisible(true);
	}// </editor-fold>

	// GEN-END:initComponents

	private void btnResponseActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		action.sendResponse();
	}

	private void btnInspectionActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		action.setLegimate(dftIP2HC, jtaLegimate, jtaAttack);

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
				new Server().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnInspection;
	private javax.swing.JButton btnResponse;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTextArea jtaAttack;
	private javax.swing.JTextArea jtaLegimate;
	private javax.swing.JTable tblFiles;
	private javax.swing.JTable tblIP2HC;
	public DefaultTableModel dftIP2HC;
	public DefaultTableModel dftFiles;
	// End of variables declaration//GEN-END:variables

}