package com.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Assignckeck extends JFrame {
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane scroll;
	Object[] cols = { "이름", "첨부파일", "다운로드" };
	public Assignckeck() {
		setTitle("과제 제출 확인");

		tableModel = new DefaultTableModel(cols, 50) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);

		
		add(BorderLayout.CENTER, new JScrollPane(table));

		setBounds(200, 200, 400, 450);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}


}
