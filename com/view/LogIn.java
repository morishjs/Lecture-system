package com.view;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LogIn extends JFrame{
	
	JRadioButton studentBtn, lecturerBtn;
	JTextField idTxt,passwordTxt;
	
	JButton loginBtn, signupBtn;
	public LogIn() {
		this.setTitle(" 로그인");
		
		studentBtn = new JRadioButton("학생",false);
		lecturerBtn = new JRadioButton("강사",false);
		
		//라디오버튼 중복체크 방지
		ButtonGroup bg=new ButtonGroup();
		bg.add(studentBtn);
		bg.add(lecturerBtn);
	
		
		JPanel jp1= new JPanel();
		jp1.add(studentBtn);
		jp1.add(lecturerBtn);
		
		idTxt = new JTextField("아이디", 15);
		passwordTxt = new JTextField("비밀번호",15);
		
		
		JPanel jp2= new JPanel();
		jp2.add(idTxt);
		jp2.add(passwordTxt);
		
		loginBtn = new JButton("로그인");
		signupBtn = new JButton("회원가입");
		JPanel jp3 =new JPanel();
		jp3.add(loginBtn);
		jp3.add(signupBtn);
	
		add(jp1,BorderLayout.NORTH); add(jp3,BorderLayout.SOUTH);
		add(jp2,BorderLayout.CENTER);
		setSize(400,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//end LogInWindow()
}//end LogInWindow

//public class LogIn {
//
//	public static void main(String[] args) {
//		new LogInWindow();
//
//	}
//
//}
