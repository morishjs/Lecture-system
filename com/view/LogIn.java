package com.view;

import com.control.*;
import com.model.Lecturer;
import com.model.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LogIn extends JFrame implements ActionListener{

	JRadioButton studentBtn, lecturerBtn;
	JTextField idTxt,passwordTxt;

	JButton loginBtn, signupBtn;
	public LogIn() {
		this.setTitle(" 로그인");

		studentBtn = new JRadioButton("학생",true);
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
		loginBtn.addActionListener(this);
		add(jp1,BorderLayout.NORTH);
		add(jp3,BorderLayout.SOUTH);
		add(jp2,BorderLayout.CENTER);

		////
		signupBtn.addActionListener(this);


		setSize(400,150);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}//end LogInWindow()

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		ClientController cc = ClientController.getInstance();
		if(obj==signupBtn && studentBtn.isSelected()){
			cc.showSignUpView();
		} else if (obj == loginBtn) {
			if (lecturerBtn.isSelected()) {
				Lecturer lecturer = cc.l_Login(idTxt.getText(), passwordTxt.getText());
				if(lecturer == null){
					JOptionPane.showMessageDialog(this, "정보를 확인해주세요");
				}else{
					//세션 설정
					cc.setSession(lecturer);
					//캘린더 출력해야함.
					cc.showCalender();
				}

			} else if (studentBtn.isSelected()) {
				Student student = cc.login(idTxt.getText(), passwordTxt.getText());
				if (student == null) {
					JOptionPane.showMessageDialog(this, "정보를 확인해주세요");
				} else {
					//세션 설정
					cc.setSession(student);
					//캘린더 출력해야함
					cc.showCalender();
				}
			}
		}

	}
}//end LogInWindow

//public class LogIn {
//

//	public static void main(String[] args) {
//		new LogInWindow();
//
//	}
//
//}




