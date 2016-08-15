package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import com.control.ClientController;
import com.model.Assignment;
import com.model.Session;
import com.view.datastructure.ListRecord;

public class Assign extends JFrame implements ActionListener {
    JLabel assignment_name, assignmentex, deadline, uploadfile;
    JTextArea assignta;
    JTextField fileRo, assignment_nametf;
    JButton sebtn, okbtn, canbtn, chbtn;
    JComboBox<String> lectureDropdown;
    JFileChooser fileChooser;

    String selectItem;

    String lecname, lecrname, deaddate;
    boolean checkType;
    ClientController controller = ClientController.getInstance();
    SwingCalender swingCalender = controller.getCalender();
    int index;

    private String deadLine;
    private String description;
    private String name;
    private File selectedFile;

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
        fileChooser = new JFileChooser();


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
        sebtn.addActionListener(this);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));


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
        sebtn.setVisible(!checkType);
        uploadfile.setVisible(!checkType);
        fileRo.setVisible(!checkType);


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
        Session session = controller.getSession();
        if (obj == chbtn) {
            String lectureId = lectureDropdown.getSelectedItem().toString();
            String assignmentName = assignment_nametf.getText();
            //DB evaluation에서 레코드를 가져오고 리스트에 뿌려줘야함.
            ArrayList<ListRecord> info = controller.getEvalutation(lectureId,assignmentName);

            new Assignckeck(info);


        } else if (obj == okbtn) {
            String type = session.getType();
            //DB에 Assignment를 등록하는 코드 (강사)
            if (type.equals("강사")) {


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
                        swingCalender.setButtonColor(getIndex());
                }
            } else if (type.equals("학생")) {
                if(selectedFile != null)
                {
                    //db에 레코드 업로드
                    int result = controller.submitAssignment(session.getId(), assignment_nametf.getText(), lectureDropdown.getSelectedItem().toString());
                    switch (result) {
                        case ClientController.EVALUATION_REG_ERROR:
                            setMessage("파일을 등록할 수 없습니다.");
                            break;
                        case ClientController.RESULT_OK:
                            setMessage("과제를 성공적으로 제출하였습니다.");
                            //file upload
                            controller.fileUpload(session.getId(),selectedFile.getAbsolutePath());

                    }
                }
            }


            SwingCalender.chk = true;
            dispose();
        }

        else if (obj == sebtn) {

            int result = fileChooser.showOpenDialog(getParent());
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileRo.setText(selectedFile.getName());
            }

        } else if (obj == canbtn)

        {
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

