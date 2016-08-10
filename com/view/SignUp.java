package java0809_project;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class SignUpWindow extends JFrame{
	JTextField inputJumin1, inputJumin2,inputName,inputId,pwConfirm, sameConfirm;
	JPasswordField inputPw, reInputPw;
	JComboBox<String> lectBox;
	JButton signUpBtn, dupliConfirmBtn, confirmBtn1, confirmBtn2;
	
	
	public SignUpWindow() {
		this.setTitle(" 회원가입");
		
		inputName = new JTextField(8);
		
		JPanel jp0=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp0.add(new JLabel("이름 "));
		jp0.add(inputName);
		
		inputJumin1 = new JTextField(6);
		inputJumin2 = new JTextField(7);
		
		JPanel jp1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.add(new JLabel("주민등록번호"));
		jp1.add(inputJumin1);
		jp1.add(new JLabel("-"));
		jp1.add(inputJumin2);
		
		
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
		setLayout(new GridLayout(7, 1));
		add(jp0); add(jp1); add(jp2); add(jp3); add(jp4); add(jp5); add(jp6); 
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}//end SignUpWindow()
}
public class SignUp {

	public static void main(String[] args) {
		
		new SignUpWindow();
	}

}
