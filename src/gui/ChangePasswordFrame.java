package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actor.*;
import app.Application;
import dao.DoctorDAO;
import dao.PatientDAO;
 
public class ChangePasswordFrame extends JFrame {
    /**
     * 面板
     */
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;
 
    /**
    * 标签
    */
    private JLabel jlbUsername;
    private JLabel jlbOldPassword;
    private JLabel jlbNewPassword1;
    private JLabel jlbNewPassword2;
 
    /**
    * 文本框
    */
    private JTextField jtfUsername;
    private JPasswordField jpfOldPassword;
    private JPasswordField jpfNewPassword1;
    private JPasswordField jpfNewPassword2;
 
    /**
    * 按钮
    */
    private JButton jbtOK;
    private JButton jbtCancel;

    private int type = 0;
 
    public ChangePasswordFrame(int userType) {
        super(Application.user.getID());
        /**
         * userType: 1:Patient 2:Doctor 3:Admin
         */
        type = userType;
        // 初始化用户界面
        initGUI();
    }
 
    /**
    * 初始化用户界面
    */
    private void initGUI() {
        // 创建对象
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
 
        jlbUsername = new JLabel("用户名：");
        jlbOldPassword = new JLabel("旧密码：");
        jlbNewPassword1 = new JLabel("新密码：");
        jlbNewPassword2 = new JLabel("确    认：");
 
        jtfUsername = new JTextField(20);
        jtfUsername.setText(Application.user.getName());
        jtfUsername.setEditable(false);
        jpfOldPassword = new JPasswordField(20);
        jpfNewPassword1 = new JPasswordField(20);
        jpfNewPassword2 = new JPasswordField(20);
 
        jbtOK = new JButton("确定");
        jbtCancel = new JButton("取消");
 
        // 添加组件
        this.setLayout(new GridLayout(5, 1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
 
        jp1.add(jlbUsername);
        jp1.add(jtfUsername);
        jp2.add(jlbOldPassword);
        jp2.add(jpfOldPassword);
        jp3.add(jlbNewPassword1);
        jp3.add(jpfNewPassword1);
        jp4.add(jlbNewPassword2);
        jp4.add(jpfNewPassword2);
        jp5.add(jbtOK);
        jp5.add(jbtCancel);
 
        // 设置窗口属性
        this.pack();
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // 注册监听器，实现监听器接口，编写事件处理代码
        // 取消按钮单击事件
        jbtCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
 
        // 确定按钮单击事件
        jbtOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
 
        jtfUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jpfOldPassword.requestFocus();
                }
            }
        });
 
        jpfOldPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jpfNewPassword1.requestFocus();
                }
            }
        });
 
        jpfNewPassword1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jpfNewPassword2.requestFocus();
                }
            }
        });
 
        jpfNewPassword2.addKeyListener(new KeyAdapter() {
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
                    changePassword();
                }
            }
        });
    }
 
    /**
    * 修改密码的方法
    */
    private void changePassword() {
        String password = new String(jpfOldPassword.getPassword());
        String newPassword1 = new String(jpfNewPassword1.getPassword());
        String newPassword2 = new String(jpfNewPassword2.getPassword());
 
        if (!password.equals(Application.user.getPassword())) { 
            JOptionPane.showMessageDialog(null, "旧密码错误，请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
            jpfOldPassword.requestFocus();
            jpfOldPassword.selectAll();
        } else if (newPassword1.equals("")) {
            JOptionPane.showMessageDialog(null, "新密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
            jpfNewPassword1.requestFocus();
        } else if (newPassword2.equals("")) {
            JOptionPane.showMessageDialog(null, "确认密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
            jpfNewPassword2.requestFocus();
        } else if (!newPassword1.equals(newPassword2)) {
            JOptionPane.showMessageDialog(null, "两次密码不一致，请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
            jpfNewPassword1.setText("");
            jpfNewPassword2.setText("");
            jpfNewPassword1.requestFocus();
        } 
        else {
            int r = 0;
            // 在数据库中更新用户信息
            if (type == 1) {
                Patient p = (Patient) Application.user;
                p.setPassword(newPassword1);
                r = PatientDAO.update(p);
            } 
            else if (type == 2) {
                Doctor d = (Doctor) Application.user;
                d.setPassword(newPassword1);
                r = DoctorDAO.update(d);
            }
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "密码修改成功！", "设置密码", JOptionPane.INFORMATION_MESSAGE);
                // 如果修改成功，在本地也修改密码
                Application.user.setPassword(newPassword1);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "密码修改失败！", "设置密码", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}