package com.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpCheck extends JFrame{

	
	JLabel name, id, pw, lecture;

	public SignUpCheck(String nameT, String idT, String pwT, String lectureT) {
	
		this.setTitle(" 회원가입 확인");
		
		name=new JLabel(nameT);
	
		id=new JLabel(idT);
		pw=new JLabel(pwT);
		lecture = new JLabel(lectureT);
		
		JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.add(new JLabel("이름: "));
		jp1.add(name);
		
		JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp2.add(new JLabel("아이디: "));
		jp2.add(id);
	
		JPanel jp3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3.add(new JLabel("비밀번호: "));
		jp3.add(pw);
	
		JPanel jp4= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp4.add(new JLabel("강좌: "));
		jp4.add(lecture);
	
		setLayout(new GridLayout(4, 1));
		add(jp1); add(jp2); add(jp3); add(jp4); 
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}//end SignUpCheck(String nameT, String jumin1T, String jumin2T, String idT, String pwT, String lectureT)
}