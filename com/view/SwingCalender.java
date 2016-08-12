package com.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

import com.control.ClientController;
import com.model.Assignment;
import com.model.Lecture;
import com.model.Session;

public class SwingCalender extends JFrame implements ActionListener

{

	HashMap<Integer, Assignment> info;


	String[] days = { "월", "화", "수", "목", "금", "토", "일" };
	int year, month, day, todays, memoday = 0;
	// Font f;
	Color bc, fc;
	Calendar today;
	Calendar cal;
	JButton btnBefore, btnAfter;
	JButton[] calBtn = new JButton[49];
	JLabel id;
	JLabel type;
	JPanel panWest;
	JPanel panSouth;
	JPanel panNorth;
	JTextField txtMonth, txtYear;
	JTextField txtTime;
	BorderLayout bLayout = new BorderLayout();
	ClientController cc = ClientController.getInstance();
	static boolean chk = true;

	// 화면
	public SwingCalender(ArrayList<Lecture> lectures) {
		//
		Session ss = cc.getSession();
		info = new HashMap<>();
		//
		today = Calendar.getInstance();
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		panNorth = new JPanel();
		panNorth.add(btnBefore = new JButton("Before"));
		panNorth.add(txtYear = new JTextField(year + "년"));
		panNorth.add(txtMonth = new JTextField(month + "월", 3));
		txtYear.setEnabled(false);
		txtMonth.setEnabled(false);
		panNorth.add(btnAfter = new JButton("After"));
		//
		panNorth.add(type = new JLabel(ss.getType()));
		//
		// f = new Font("Sherif", Font.BOLD, 18);
		// txtYear.setFont(f);
		// txtMonth.setFont(f);
		add(panNorth, "North");

		panWest = new JPanel(new GridLayout(7, 7));//
		// f = new Font("Sherif", Font.BOLD, 12);
		gridInit();
		calSet();
		hideInit();
		add(panWest, "Center");

		btnBefore.addActionListener(this);
		btnAfter.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기
		setTitle("Login : " + ss.getId() + "/" + ss.getType() + "용");
		setBounds(200, 200, 400, 440); // 화면 크기 조절
		setResizable(false);
		setVisible(true);

		//Assignment를 캘린더에 출력한다.
		if(lectures != null){
			for (Lecture lecture : lectures) {
				for (Assignment assignment : lecture.getAssignmentList()) {
					String deadLine = assignment.getAssignmentDeadlline();
					String[] date = deadLine.split("-");
					String day = date[2];
					int index = Integer.parseInt(day);
					//버튼 색상 변경
					setButtonColor(index);
					//각 날짜에 과제들을 추가한다. 이후에 버튼을 눌렀을 때 info로부터 assignment의 정보를 가져온다.
					info.put(index, assignment);

				}



			}
		}



	}// end constuctor

	public void calSet() {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		cal.set(Calendar.DATE, 1);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		int j = 0;
		int hopping = 0;
		calBtn[0].setForeground(new Color(255, 0, 0));// 0번째 빨간색 - 일
		calBtn[6].setForeground(new Color(0, 0, 255));// 6번째 파란색 - 토

		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			j++;
		}
		hopping = j;

		for (int kk = 0; kk < hopping; kk++) {

			calBtn[kk + 7].setText("");
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			if (cal.get(Calendar.MONTH) != month - 1) {
				break;
			}

			todays = i;
			if (memoday == 1) {
				calBtn[i + 6 + hopping].setForeground(new Color(0, 255, 0));
			} else {
				calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				if ((i + hopping - 1) % 7 == 0) {
					calBtn[i + 6 + hopping].setForeground(new Color(255, 0, 0)); // 숫자색
				}
				if ((i + hopping) % 7 == 0) {
					calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 255)); // 숫자색
				}
			}
			calBtn[i + 6 + hopping].setText((i) + "");
		} // for
	}// end Calset()

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == btnBefore) {
			this.panWest.removeAll();
			calInput(-1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		} else if (ae.getSource() == btnAfter) {
			this.panWest.removeAll();
			calInput(1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		} else if (Integer.parseInt(ae.getActionCommand()) >= 1 && Integer.parseInt(ae.getActionCommand()) <= 31) {
			day = Integer.parseInt(ae.getActionCommand());

			if (chk == true) {
				Assign as = new Assign(day);
				chk = false;
				//info에서 assigment 정보를 가져온다.
				Assignment assignment = info.get(day);
				//정보를 가져와서 assign에 정보를 출력한다.
				if(assignment != null) {
					as.setAssignmentDeadline(assignment.getAssignmentDeadlline());
					as.setAssignmentDescription(assignment.getAssignmentDescription());
					as.setAssignmentName(assignment.getAssignmentName());
				}

			} else if (chk == false) {
				JOptionPane.showMessageDialog(this, "기존 창을 닫으신 후 다시 시도해주세요");
			}
			System.out.println(+year + "-" + month + "-" + day); // 클릭한 날짜 년월일



			calSet();
		}

	}// end actionperformed()

	public void hideInit() {
		for (int i = 0; i < calBtn.length; i++) {
			if ((calBtn[i].getText()).equals(""))
				calBtn[i].setEnabled(false);
		} // end for
	}// end hideInit()

	public void gridInit() {
		for (int i = 0; i < days.length; i++) {
			panWest.add(calBtn[i] = new JButton(days[i]));
			calBtn[i].setContentAreaFilled(false);
			calBtn[i].setBorderPainted(false);
		}
		for (int i = days.length; i < 49; i++) {
			panWest.add(calBtn[i] = new JButton(""));
			calBtn[i].addActionListener(this);
		}
	}// end gridInit()

	public void panelInit() {
		GridLayout gridLayout1 = new GridLayout(7, 7);
		panWest.setLayout(gridLayout1);
	}// end panelInit()

	public void calInput(int gap) {
		month += (gap);
		if (month <= 0) {
			month = 12;
			year = year - 1;
		} else if (month >= 13) {
			month = 1;
			year = year + 1;
		}
	}// end calInput()

	public void setButtonColor(int index) {
		// TODO Auto-generated method stub
		calBtn[index+7].setBackground(new Color(0,255,0));
		calBtn[index+7].setOpaque(true);
	}
}// end class

// public class Calender{
// public static void main(String[] args) {
// SwingCalender jdbc = new SwingCalender();
//
// }
//
// }