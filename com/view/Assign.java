package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.control.ClientController;
import com.model.Session;

class Assigns extends JFrame implements ActionListener{
	JLabel lecture, lecturer,assignmentex,deadline,uploadfile,explanation;
	JTextArea assignta,explanta;
	JTextField fileRo;
	JButton sebtn, okbtn, canbtn, chbtn;
	String lecname,lecrname,deaddate;
	boolean checkType;
	ClientController controller = ClientController.getInstance();
	public Assigns(){
		Session session = controller.getSession();
		
		this.setTitle("과제제출");
		lecture = new JLabel("강의명 : "+lecname);
		lecturer = new JLabel("강사명 : "+lecrname);
		assignmentex = new JLabel("과제 설명");
		deadline = new JLabel("제출 기간 : "+deaddate);
		uploadfile = new JLabel("제출 파일 : ");
		explanation = new JLabel("부가 설명?");
		sebtn = new JButton("파일 제출");
		okbtn = new JButton("확인");
		canbtn = new JButton("취소");
		chbtn  = new JButton("제출 확인");
		
		//Jfilebtn.setPreferredSize(new Dimension(20, 20));
		
		Font font = new Font("sansSerif", 0, 12);
		lecture.setFont(font);
		lecturer.setFont(font);
		assignmentex.setFont(font);
		deadline.setFont(font);
		uploadfile.setFont(font);
		explanation.setFont(font);
		sebtn.setFont(font);
		okbtn.setFont(font);
		canbtn.setFont(font);
		chbtn.setFont(font);
		
		assignta = new JTextArea();
		assignta.setEditable(checkType);		
		explanta = new JTextArea();
		fileRo  = new JTextField(15)	;
		
		
		
		
		chbtn.addActionListener(this);
		setLayout(null);
		lecture.setBounds(0,0,400,30);
		chbtn.setBounds(300, 185, 90, 20);
		lecturer.setBounds(0,30,400,30);
		assignmentex.setBounds(0,60,400,30);
		assignta.setBounds(0, 90, 400, 90);
		deadline.setBounds(0,180,300,30);
		
		uploadfile.setBounds(0,210,80,40);
		fileRo.setBounds(80,220,200,20);
		fileRo.setEditable(false);
		fileRo.setBackground(Color.white);
		sebtn.setBounds(300,220,90,20);
		
		explanation.setBounds(0,250,400,30);
		explanta.setBounds(0, 280, 400, 90);
		okbtn.setBounds(100, 380, 70, 30);
		canbtn.setBounds(230, 380, 70, 30);
		
		add(lecture);
		add(chbtn);
		add(lecturer);
		add(assignmentex);
		add(assignta);
		add(deadline);
		add(uploadfile);
		add(fileRo);
		add(sebtn);
		add(explanation);
		add(explanta);
		add(okbtn);
		add(canbtn);
		
	
		
		setBounds(200, 200, 400, 450);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Assignckeck();
	}
}


public class Assign extends JFrame {

	public static void main(String[] args) {
		new Assigns();
	}

}
