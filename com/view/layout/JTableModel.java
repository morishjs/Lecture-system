package com.view.layout;

import com.view.datastructure.ListRecord;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-14.
 */
public class JTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = new String[] {"이름","과제명", "다운로드"};
    private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, JButton.class};
    int rowCount = 0;
    ArrayList<ListRecord> info;
    public JTableModel(ArrayList<ListRecord> info) {
        rowCount = info.size();
        this.info = info;
    }

    @Override public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override public int getRowCount() {
        return rowCount;
    }

    @Override public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
                /*Adding components*/
        switch (columnIndex) {
            case 0:
                return info.get(rowIndex).getStudentName();
            case 1:
                return info.get(rowIndex).getAssignName();
            case 2: final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button),
                                "Button clicked for row "+rowIndex);
                                
                    }
                });
                return button;
            default: return "Error";
        }
    }
}