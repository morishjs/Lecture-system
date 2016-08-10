package com.view;

import com.control.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

<<<<<<< HEAD
public class LogIn extends JFrame implements ActionListener{
	
=======
import com.control.ClientController;
import com.model.Lecturer;
import com.model.Student;

public class LogIn extends JFrame implements ActionListener {

>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95
	JRadioButton studentBtn, lecturerBtn;
	JTextField idTxt, passwordTxt;

	JButton loginBtn, signupBtn;

	public LogIn() {
		this.setTitle(" 로그인");
<<<<<<< HEAD
		
		studentBtn = new JRadioButton("학생",true);
		lecturerBtn = new JRadioButton("강사",false);
		
		//라디오버튼 중복체크 방지
		ButtonGroup bg=new ButtonGroup();
=======

		studentBtn = new JRadioButton("학생", false);
		lecturerBtn = new JRadioButton("강사", false);

		// 라디오버튼 중복체크 방지
		ButtonGroup bg = new ButtonGroup();
>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95
		bg.add(studentBtn);
		bg.add(lecturerBtn);

		JPanel jp1 = new JPanel();
		jp1.add(studentBtn);
		jp1.add(lecturerBtn);

		idTxt = new JTextField("아이디", 15);
		passwordTxt = new JTextField("비밀번호", 15);

		JPanel jp2 = new JPanel();
		jp2.add(idTxt);
		jp2.add(passwordTxt);

		loginBtn = new JButton("로그인");
		signupBtn = new JButton("회원가입");
		JPanel jp3 = new JPanel();
		jp3.add(loginBtn);
		jp3.add(signupBtn);
<<<<<<< HEAD
	
		add(jp1,BorderLayout.NORTH); add(jp3,BorderLayout.SOUTH);
		add(jp2,BorderLayout.CENTER);
		
		////
		signupBtn.addActionListener(this);
		
		setSize(400,150);
=======
		loginBtn.addActionListener(this);

		add(jp1, BorderLayout.NORTH);
		add(jp3, BorderLayout.SOUTH);
		add(jp2, BorderLayout.CENTER);
		setSize(400, 150);
>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// end LogInWindow()

	@Override
	public void actionPerformed(ActionEvent e) {
		// Object obj = e.getSource();
		
		
<<<<<<< HEAD
	}//end LogInWindow()
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==signupBtn && studentBtn.isSelected()){
			ClientController cc=new ClientController();
			cc.showSignUpView();
			
		}
		
	}
}//end LogInWindow
=======
		ClientController cc = ClientController.getInstance();
		if (lecturerBtn.isSelected()) {
			Lecturer lecturer = cc.l_login(idTxt.getText(), passwordTxt.getText());
			if(lecturer == null){
				JOptionPane.showMessageDialog(this, "정보를 확인해주세요");
			}else{
				cc.setSession(lecturer);				
			}
		
		} else if (studentBtn.isSelected()) {
			Student student = cc.login(idTxt.getText(), passwordTxt.getText());

			if (student == null) {
				JOptionPane.showMessageDialog(this, "정보를 확인해주세요");
			} else {
				cc.setSession(student);
			}
		}
	}
}// end LogInWindow
>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95

// public class LogIn {
//
<<<<<<< HEAD

//	public static void main(String[] args) {
//		new LogInWindow();
=======
// public static void main(String[] args) {
// new LogInWindow();
>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95
//
// }
//
<<<<<<< HEAD
//}




=======
// }
>>>>>>> b9af3001f4f4e411f01f3f71d3b6d4a1962e4c95
