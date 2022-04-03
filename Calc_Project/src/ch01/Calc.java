package ch01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Calc extends JFrame{
	
	JTextField Result; // ������� ����� ���� �����ִ� ��
	
	JPanel buttonsPanel; // ���� �� ���� ��ȣ�� ���� �г�
	BorderLayout borderLayout;
	String num = ""; // ����ڰ� �Է��� ���� ���� (ex : 123 �Է� num = 123)

	// �迭���� �� Ű �迭�� ����
	String button_Calc[] = {"C", "��","��","=","7","8","9","+","4","5","6","-","1","2","3","��"," ","0"," "," "};
	JButton button[] = new JButton[button_Calc.length]; // ��ư�� button_calc �ȿ� ����ִ� ������ �ϳ��� ����
	
	private ArrayList<String> Num_Str = new ArrayList<String>(); // ����ڰ� ������ ���� arraylist �� ���� [1],[+],[1];
	
	
	public Calc() {
		
		initData();
		setInitLayout();
	
	}
	
	private void initData() {
		
		setTitle("����");
		setSize(300,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		// GUI ��ܿ� �簢�� �׵θ� ����
		Result = new JTextField();
		Result.setEditable(false); // ����ڰ� text�� �������� ���ϰ�
		Result.setBackground(Color.white);
		Result.setHorizontalAlignment(JTextField.RIGHT);
		Result.setFont(new Font("Arial", Font.BOLD,50));
		Result.setBounds(10,18,265,100); // x,y,w,h
		Result.setText(""); // ������ �ʱ�ȭ
		//-----------------------------------------------
		
		buttonsPanel = new JPanel();
		
		for(int i = 0; i < button_Calc.length; i++) {
			button[i] = new JButton(button_Calc[i]); // button ��ư�� ���� �� �����ȣ�� ����
			button[i].setFont(new Font("Arial", Font.BOLD,30)); 
			
			buttonsPanel.add(button[i]); // �гο� �߰�
			button[i].addActionListener(new NumberPad()); // ����ڰ� �Է��� ��ư�� ����ǵ���
			if(button_Calc[i] == "��") { // ���ڸ� ũ���ϴϱ� ������ �ʾƼ� ���� ũ�⸸ ����
				button[i].setFont(new Font("Arial", Font.BOLD, 20));

			}
			
		}
		
	
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		

		buttonsPanel.setLayout(new GridLayout(5,4,10,10)); // ��ư��ġ 5�� 4���� ��ġ
		buttonsPanel.setBounds(7,140,270,290);             // x,y,w,h �簢�׵θ� ����� ������ x,y������ ��ġ�� ��ġ
														   
		//buttonsPanel.setBackground(Color.black);
		

		add(buttonsPanel);
		add(Result);
	
	}
	
	// ����� Ŭ�� Class
	class NumberPad implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		
			String ClickNum = e.getActionCommand();
			if(ClickNum.equals("C")) {
				Result.setText("");
			}else if(ClickNum.equals("=")) {
					String result = Double.toString(Calculate(Result.getText()));
					Result.setText("" + result); // ȭ�鿡 ���� ������� �ѷ���
					num = "";  // ������ ����ϰ� �ִ� num ���� �ʱ�ȭ
			}else if(ClickNum.equals("��")) { // �齺���̽� 
					String temp = Result.getText();
					temp = temp.substring(0,temp.length()-1);
					Result.setText(temp);		
					
			}else {
				// �� �� ���ǽĿ� �ش���� ������ ���� ���� ȭ�鿡 �ִ� ���̶� ����ڰ� Ŭ���Ѱ��� ��� ����
				Result.setText(Result.getText() + e.getActionCommand()); 
			}
			
		}
	}
	
	public void SymbolConver(String inputText) {
		
		// �迭�ȿ� ���� clear ���� ������ ����ڰ� ���� ���ϰ� ���� �ٽ� ���ο� ���� ���Ҷ� ������ ���� ���� ��� ����ϰ��־ clear �������
		Num_Str.clear(); 
	
		
		for(int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);
			if(ch == '-' | ch == '+' | ch == '��' | ch == '��') { 
				Num_Str.add(num);  // �����ȣ�� ������ ���� ������ ����ؼ� �迭�� �߰�
				num = ""; // num �� �ʱ�ȭ 
				Num_Str.add(ch + ""); // ������ �����ȣ ����
			}else {
				num = num + ch; // �����ȣ�� �������� ���ڸ� Ŭ���� ������ ����ؼ� num �� �߰�

			}
			
		}
		Num_Str.add(num); // �����ȣ�� ���� �� ���ڵ��� �迭�� �߰� 
							// 123 + 45�� �Է����� �� [123,+,45] ��°�� 

	}
	
	// ��� ��� �޼ҵ�
	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// ����ڰ� �Է��� ���� �� �����ڵ��� ������ (���ڿ�)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("��")) {
				mod = "mul";
			}else if(s.contentEquals("��")) {
				mod = "div";
			}else {
				if(!s.isEmpty()) {
					 current = Double.parseDouble(s);
				}
	
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Calc();
	}
	
	
	
	

}
