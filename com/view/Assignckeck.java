package com.view;

import com.view.datastructure.ListRecord;
import com.view.layout.JTableModel;
import com.view.listener.JTableButtonMouseListener;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;


public class Assignckeck extends JFrame {
    //DefaultTableModel tableModel;

    JTable table;
    JScrollPane scroll;
    ArrayList<ListRecord> info;

    public Assignckeck(ArrayList<ListRecord> info) {
        setTitle("과제 제출 확인");
        this.info = info;

        table = new JTable(new JTableModel(info));
        scroll = new JScrollPane(table);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);

        TableCellRenderer buttonRenderer = new JTableCellRenderer();
        table.getColumn("다운로드").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));

        add(BorderLayout.CENTER, new JScrollPane(table));

        setBounds(200, 200, 400, 450);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private class JTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            return button;
        }
    }
}



