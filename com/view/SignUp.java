package com.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class SignUp extends JFrame implements ActionListener, MouseListener{
	
	JTextField inputName,inputId, sameConfirm;
	JPasswordField inputPw, reInputPw;
	JComboBox<String> lectBox;
	JButton signUpBtn, confirmBtn;
	String nameT;
	String idT;
	String pwT;
	String lectureT;

	public SignUp() {
		this.setTitle(" 회원가입");
		
		inputName = new JTextField(8);
		
		JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.add(new JLabel("이름 "));
		jp1.add(inputName);
		
		inputId = new JTextField(15);
		
		
		JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp2.add(new JLabel("아이디 "));
		jp2.add(inputId);

		inputPw = new JPasswordField(15);
		inputPw.setEchoChar('*');
	
		JPanel jp3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3.add(new JLabel("비밀번호 "));
		jp3.add(inputPw);
	
		jp3.add(new JLabel("6~12자리를 입력하세요."));
		
		reInputPw = new JPasswordField(15);
		reInputPw.setEchoChar('*');//비밀번호를 *로 입력되게
		sameConfirm = new JTextField("일치하지 않습니다.",20);
		confirmBtn = new JButton("확인");
		JPanel jp4= new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp4.add(new JLabel("비밀번호 확인 "));
		jp4.add(reInputPw);
		jp4.add(confirmBtn);
		jp4.add(sameConfirm);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("java");
		model.addElement("spring");
		model.addElement("oracle");
		lectBox = new JComboBox<String>(model);
		
		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp5.add(new JLabel("강좌명: "));
		jp5.add(lectBox);
		
		signUpBtn=new JButton("회원가입");
		JPanel jp6 =new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp6.add(signUpBtn);
		setLayout(new GridLayout(6, 1));
		add(jp1); add(jp2); add(jp3); add(jp4); add(jp5); add(jp6); 
		
		//inputPw.addMouseListener(this);
		confirmBtn.addMouseListener(this);
		
		//////
		signUpBtn.addActionListener(this);
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	public void setMessage(String msg) {
		JLabel label = new JLabel(msg);
		JOptionPane.showMessageDialog(this, label);
		
	}// end setMessage
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		ClientController clientController = ClientController.getInstance();
		if(obj==signUpBtn){
			nameT = inputName.getText().trim();
			idT = inputId.getText().trim();
			pwT = inputPw.getText().trim();
			lectureT =(String)lectBox.getSelectedItem();
			
			// 모든 항목을 입력하지 않았을때 경고.
			if (nameT.length()<1 || idT.length()<1 || pwT.length()<1 || lectureT.length()<1) {
				setMessage("모든 항목을 입력하십시오");
				return;
			}
			// 모든 항목을 입력했을때 
			if (nameT.length()>=1 && idT.length()>=1 && pwT.length()>=1 && lectureT.length()>=1) {

				switch (clientController.signup(idT,nameT,pwT,lectureT)){
					case ClientController.RESULT_OK:
						setMessage("등록완료");
						break;
					case ClientController.LECTURE_NO_EXIST:
						setMessage("강좌가 존재하지 않습니다.");
						break;
					case ClientController.STUDENT_REG_ERROR:
						setMessage("회원가입을 할 수 없습니다.");
				}
				return;

			}



			
		}
		
	}//end SignUpWindow()

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj==confirmBtn){
			/*String pwd = inputPw.getText().trim();
			String rpwd = reInputPw.getText().trim();
			ClientController clientController = ClientController.getInstance();
			String me=clientController.PWCheck(pwd, rpwd);
			sameConfirm.setText(me);*/
			
			if(reInputPw.getText().equals(inputPw.getText()))
				sameConfirm.setText("일치합니다.");
			else
				sameConfirm.setText("일치하지않습니다..");
		}
			
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
//public class SignUp {
//
//	public static void main(String[] args) {
//
//		new SignUpWindow();
//	}
//
//}
