package com.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.control.ClientController;

public class SignUp extends JFrame implements ActionListener{
	
	JTextField inputJumin1,inputJumin2,inputName,inputId,pwConfirm, sameConfirm;
	JPasswordField inputPw, reInputPw;
	JComboBox<String> lectBox;
	JButton signUpBtn, dupliConfirmBtn, confirmBtn1, confirmBtn2;
	
	
	public SignUp() {
		this.setTitle(" 회원가입");
		
		inputName = new JTextField(8);
		
		JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.add(new JLabel("이름 "));
		jp1.add(inputName);
		
		inputId = new JTextField(15);
		dupliConfirmBtn = new JButton("중복확인");
		
		JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp2.add(new JLabel("아이디 "));
		jp2.add(inputId);
		jp2.add(dupliConfirmBtn);
		jp2.add(new JLabel("안내받은 숫자 ID를 입력"));
		
		confirmBtn1 = new JButton("확인");
		inputPw = new JPasswordField(15);
		inputPw.setEchoChar('*');
		pwConfirm = new JTextField("6~12자리를 입력하세요.",20);	
		confirmBtn2 = new JButton("확인");
		
		JPanel jp3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3.add(new JLabel("비밀번호 "));
		jp3.add(inputPw);
		jp3.add(confirmBtn1);
		jp3.add(pwConfirm);
		
		reInputPw = new JPasswordField(15);
		reInputPw.setEchoChar('*');//비밀번호를 *로 입력되게
		sameConfirm = new JTextField("일치하지 않습니다.",20);
		
		JPanel jp4= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp4.add(new JLabel("비밀번호 확인 "));
		jp4.add(reInputPw);
		jp4.add(confirmBtn2);
		jp4.add(sameConfirm);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("강좌1");
		model.addElement("강좌2");
		model.addElement("강좌3");
		lectBox = new JComboBox<String>(model);
		
		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp5.add(new JLabel("강좌명: "));
		jp5.add(lectBox);
		
		signUpBtn=new JButton("회원가입");
		JPanel jp6 =new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp6.add(signUpBtn);
		setLayout(new GridLayout(6, 1));
		add(jp1); add(jp2); add(jp3); add(jp4); add(jp5); add(jp6); 
		
		
		signUpBtn.addActionListener(this);
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void setMessage(String msg) {
		JLabel label = new JLabel(msg);
		JOptionPane.showMessageDialog(this, label);
	}// end setMessage

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==signUpBtn){
			String nameT = inputName.getText().trim();	
			String idT = inputId.getText().trim();
			String pwT = inputPw.getText().trim();
			String lectureT =(String)lectBox.getSelectedItem();
			
			// 모든 항목을 입력하지 않았을때 경고.
			if (nameT.length()<1 || idT.length()<1 || pwT.length()<1 || lectureT.length()<1) {
				setMessage("모든 항목을 입력하십시오");
				return;
			}
			
			new SignUpCheck(nameT,idT,pwT,lectureT);
			
		}
		
	}//end SignUpWindow()
}
//public class SignUp {
//
//	public static void main(String[] args) {
//
//		new SignUpWindow();
//	}
//
//}
