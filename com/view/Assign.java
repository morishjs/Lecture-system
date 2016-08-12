package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.*;

import com.control.ClientController;
import com.model.Assignment;
import com.model.Session;

public class Assign extends JFrame implements ActionListener {
    JLabel assignment_name, assignmentex, deadline, uploadfile;
    JTextArea assignta;
    JTextField fileRo, assignment_nametf;
    JButton sebtn, okbtn, canbtn, chbtn;
    JComboBox<String> lectureDropdown;
    String selectItem;

    String lecname, lecrname, deaddate;
    boolean checkType;
    ClientController controller = ClientController.getInstance();
    SwingCalender swingCalender = controller.getCalender();
    int index;
    private String deadLine;
    private String description;
    private String name;

    public Assign(int index) {
        Session session = controller.getSession();
        this.index = index;
        if (session.getType().equals("강사")) {
            checkType = true;
        } else {
            checkType = false;
        }

        deaddate = String.valueOf(swingCalender.year) + "-" + String.valueOf(swingCalender.month) + "-"
                + String.valueOf(swingCalender.day);

        this.setTitle(lecname);
        assignment_name = new JLabel("과제명 : ");
        assignmentex = new JLabel("과제 설명");
        deadline = new JLabel("제출 기간 : " + deaddate);
        uploadfile = new JLabel("제출 파일 : ");
        sebtn = new JButton("파일 제출");
        chbtn = new JButton("제출 확인");
        okbtn = new JButton("확인");
        canbtn = new JButton("취소");

        Font font = new Font("sansSerif", 0, 12);
        assignment_name.setFont(font);
        assignmentex.setFont(font);
        deadline.setFont(font);
        uploadfile.setFont(font);
        sebtn.setFont(font);
        okbtn.setFont(font);
        canbtn.setFont(font);
        chbtn.setFont(font);

        assignta = new JTextArea();
        fileRo = new JTextField(15);
        assignment_nametf = new JTextField(15);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        model.addElement("java");
        model.addElement("spring");
        model.addElement("oracle");
        lectureDropdown = new JComboBox<String>(model);


        chbtn.addActionListener(this);
        okbtn.addActionListener(this);
        canbtn.addActionListener(this);

        setLayout(null);
        assignment_name.setBounds(0, 0, 50, 30);
        assignment_nametf.setBounds(80, 5, 200, 20);
        assignment_nametf.setBackground(Color.white);
        chbtn.setBounds(300, 185, 90, 20);

        lectureDropdown.setBounds(300, 5, 90, 20);

        assignmentex.setBounds(0, 30, 400, 30);
        assignta.setBounds(0, 70, 400, 90);
        deadline.setBounds(0, 170, 300, 30);

        uploadfile.setBounds(0, 200, 80, 40);
        fileRo.setBounds(80, 210, 200, 20);
        fileRo.setBackground(Color.white);
        sebtn.setBounds(300, 210, 90, 20);

        okbtn.setBounds(110, 250, 70, 30);
        canbtn.setBounds(220, 250, 70, 30);

        assignment_nametf.setEditable(checkType);
        assignta.setEditable(checkType);
        chbtn.setVisible(checkType);
        fileRo.setEditable(false);
        lectureDropdown.setVisible(checkType);

        add(lectureDropdown);
        add(assignment_name);
        add(assignment_nametf);
        add(chbtn);
        add(assignmentex);
        add(assignta);
        add(deadline);
        add(uploadfile);
        add(fileRo);
        add(sebtn);
        add(okbtn);
        add(canbtn);

        setBounds(200, 200, 400, 320);
        setVisible(true);
        setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SwingCalender.chk = true;
                dispose();
            }
        });

    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == chbtn) {
            new Assignckeck();
        } else if (obj == okbtn) {




            //DB에 Assignment를 등록하는 코드
            if (controller.getSession().getType().equals("강사")) {
                swingCalender.setButtonColor(getIndex());
                //swingCalender.setAssignInfo(assignta.getText(), assignment_nametf.getText() );
                ClientController clientController = ClientController.getInstance();
                SwingCalender swingCalender = clientController.getCalender();
                HashMap<Integer, Assignment> info = swingCalender.getAssignmentInfo();
                Assignment assignment = new Assignment();

                assignment.setAssignmentName(assignment_nametf.getText());
                assignment.setAssignmentDeadlline(deadline.getText());
                assignment.setAssignmentDescription(assignta.getText());

                info.put(getIndex(), assignment);
                int result = clientController.registerAssignment(assignment, lectureDropdown.getSelectedItem().toString());
                switch (result) {
                    case ClientController.ASSIGNMENT_REG_ERROR:
                        setMessage("과제를 등록할 수 없습니다.");
                        break;
                    case ClientController.RESULT_OK:
                        setMessage("과제를 등록하였습니다.");
                }
            }


            SwingCalender.chk = true;
            dispose();

        } else if (obj == sebtn) {
            System.out.println("마지막에 합시다.");
        } else if (obj == canbtn) {
            SwingCalender.chk = true;
            dispose();
        }

    }

    public void setAssignmentDeadline(String assignmentDeadline) {
        this.deadLine = assignmentDeadline;
        deadline.setText(this.deadLine);
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.description = assignmentDescription;
        assignta.setText(this.description);
    }

    public void setAssignmentName(String assignmentName) {
        this.name = assignmentName;
        assignment_nametf.setText(this.name);
    }

    public void setMessage(String msg) {
        JLabel label = new JLabel(msg);
        JOptionPane.showMessageDialog(this, label);

    }// end setMessage
}

