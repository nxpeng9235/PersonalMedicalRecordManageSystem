package gui;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actor.*;
import app.Application;
import dao.*;

import javax.swing.JComboBox;

public class LoginFrame extends JFrame {
	//创建标签、文本框、密码框对象
	private JPanel jp1;
	private JPanel jp2;
	private JPanel jp3;
	private JPanel jp4;
	private JLabel jlbUserName, jlbPassword, jlbIdentity;
	private JTextField jtfUserName;
	private JPasswordField jpfPassword;
		
	//创建按钮对象
	private JButton jbtLogin, jbtRegister, jbtQuit;
	//创建下拉列表框对象
	private JComboBox<String> jcb1;
	
	public LoginFrame() {
		initGUI();
	}
	
	private void initGUI() {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jlbUserName = new JLabel("用户名： ");
		jlbPassword = new JLabel("密    码： ");
		jlbIdentity = new JLabel("身    份：");
		jtfUserName = new JTextField(15);
		jpfPassword = new JPasswordField(15);
		
		jbtLogin = new JButton("登录");
		jbtRegister = new JButton("注册");
		jbtQuit = new JButton("退出");
		
		jcb1 = new JComboBox<String>();
		jcb1.addItem(new String("患者"));
		jcb1.addItem(new String("医生"));
		jcb1.addItem(new String("管理员"));
		
		this.setLayout(new GridLayout(4, 1));
		
		jp1.add(jlbUserName);
		jp1.add(jtfUserName);
		
		jp2.add(jlbPassword);
		jp2.add(jpfPassword);
		
		jp3.add(jlbIdentity);
		jp3.add(jcb1);
		
		jp4.add(jbtLogin);
		jp4.add(jbtRegister);
		jp4.add(jbtQuit);		
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		this.setTitle("登录系统");
		ImageIcon icon = new ImageIcon("image/icon.jpg");
		this.setIconImage(icon.getImage());
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);    //禁止改变窗口大小
		
		this.setVisible(true);
		
		jbtLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		jbtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					login();
				}
			}
		});
		
		jbtRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Application.registerFrame = new RegisterFrame();
				dispose();
			}
		});
		
		jbtRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Application.registerFrame = new RegisterFrame();
					dispose();
				}
			}
		});
		
		jbtQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}

	private void login() {
		try {
			String un = jtfUserName.getText().trim();
			String pw = new String(jpfPassword.getPassword());
			String boxValue = (String) jcb1.getSelectedItem();

			if (boxValue.equals("患者")) {
				Patient patient = PatientDAO.getPatient(un, pw);
				if (patient.getStatus() == 1) {
					Application.user = patient;
					Application.mainFrame = new PatientMainFrame();
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "此账号尚未激活，请等待管理员审核！", "登录失败", JOptionPane.WARNING_MESSAGE);
				}
			} else if (boxValue.equals("医生")) {
				Doctor doctor = DoctorDAO.getDoctor(un, pw);
				if (doctor.getStatus() == 1) {
					Application.user = doctor;
					Application.mainFrame = new DoctorMainFrame();
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "此账号尚未激活，请等待管理员审核！", "登录失败", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if (boxValue.equals("管理员")) {
				if (un.equals("root") && pw.equals("root")) {
					Application.user = null;
					Application.mainFrame = new AdminMainFrame();
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码！", "登录失败", JOptionPane.WARNING_MESSAGE);
					jpfPassword.setText("");
				}
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码！", "登录失败", JOptionPane.WARNING_MESSAGE);
			jpfPassword.setText("");
		}
	}
}
