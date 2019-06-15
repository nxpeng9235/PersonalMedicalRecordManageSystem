package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import app.Application;
import dao.*;

public class RegisterFrame extends JFrame {
	/**
	 * 面板
	 */
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JLabel jlbType, jlbName, jlbPassword1, jlbPassword2;
	private ButtonGroup group;
	private JRadioButton jrbPatient, jrbDoctor;
	private JTextField jtfName;
	private JPasswordField jpfPassword1, jpfPassword2;
	private JButton jbtOK, jbtCancel;
	
	public RegisterFrame() {
        super("注册用户");
        // 初始化用户界面
        initGUI();
	}
	
	private void initGUI() {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		
		jlbType = new JLabel("选择身份：");
		jlbName = new JLabel("输入姓名：");
		jlbPassword1 = new JLabel("输入密码：");
		jlbPassword2 = new JLabel("确认密码：");
		
		jrbPatient = new JRadioButton("患者");
		jrbDoctor = new JRadioButton("医生");
		jrbPatient.setSelected(true);
		group = new ButtonGroup();
		group.add(jrbPatient);
		group.add(jrbDoctor);
		
		jtfName = new JTextField(20);
		jpfPassword1 = new JPasswordField(20);
		jpfPassword2 = new JPasswordField(20);
		
		jbtOK = new JButton("确认");
		jbtCancel = new JButton("取消");
		
		jp1.add(jlbType);
		jp1.add(jrbPatient);
		jp1.add(jrbDoctor);
		jp2.add(jlbName);
		jp2.add(jtfName);
		jp3.add(jlbPassword1);
		jp3.add(jpfPassword1);
		jp4.add(jlbPassword2);
		jp4.add(jpfPassword2);
		jp5.add(jbtOK);
		jp5.add(jbtCancel);
		
		//添加组件
		this.setLayout(new GridLayout(5, 1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);

        // 设置窗口属性
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // 注册监听器，实现监听器接口，编写事件处理代码
        // 取消按钮单击事件
        jbtCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Application.loginFrame = new LoginFrame();
                dispose();
            }
        });
 
        // 确定按钮单击事件
        jbtOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pw1 = new String(jpfPassword1.getPassword());
                String pw2 = new String(jpfPassword2.getPassword());
                if (pw1.equals(pw2)) {
                    register();
                }
                else {
                    JOptionPane.showMessageDialog(null, "两次输入密码不相等！", "注册失败", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Application.loginFrame = new LoginFrame();
                dispose();
            }
        });
		
        jtfName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jpfPassword1.requestFocus();
                }
            }
        });

        jpfPassword1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jpfPassword2.requestFocus();
                }
            }
        });
 
        jpfPassword2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jbtOK.requestFocus();
                }
            }
        });
 
        jbtOK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    register();
                }
            }
        });
        
        jbtCancel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                	Application.loginFrame = new LoginFrame();
                    dispose();
                }
            }
        });
	}
	
    private void register() {
        int r;
        String un = jtfName.getText().trim();
        String pw = new String(jpfPassword1.getPassword());
        if (jrbPatient.isSelected()) {
            r = PatientDAO.register(un, pw);
        }
        else {
            r = DoctorDAO.register(un, pw);
        }
        //注册成功则返回登录界面
        if (r > 0) {
            String msg = "注册成功！您的ID是：" + r + "\n请登陆后及时完善个人信息！";
            JOptionPane.showMessageDialog(null, msg);
            int n = DiseaseHistoryDAO.createDiseaseHistory(r);
            Application.loginFrame = new LoginFrame();
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null, "账号已经存在！！", "注册失败", JOptionPane.WARNING_MESSAGE);
        }
	}
}
