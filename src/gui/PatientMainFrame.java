package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import app.Application;

public class PatientMainFrame extends MainFrame {

    private JMenuBar jmnb1;
    /**
    * 设置菜单
    */
    private JMenu jmnuSet;
    private JMenuItem jmniChangePassword;
    private JMenuItem jmniExit;
    /**
    * 个人信息菜单
    */
    private JMenu jmnuPersonalInfo;
    private JMenuItem jmniChangeInfo;
    private JMenuItem jmniHealthRecord;
    private JMenuItem jmniDiseaseHistory;
    private JMenuItem jmniBodyCheck;
    /**
    * 面板
    */
    private JPanel jpMain;
    private JLabel jlbImg;
    private JTextArea jtaInfo;

    /**
    * 构造方法
    * 
    * @param title
    */
    public PatientMainFrame() {
        super(Application.user.getName() + " -- 信息管理系统（患者端）");
        userType = 1;
        initGUI();
    }

    /**
    * 初始化用户界面
    */
    private void initGUI() {
        // 创建菜单
        jmnb1 = new JMenuBar();

        // 创建【系统设置】菜单及其菜单项
        jmnuSet = new JMenu("系统设置");
        jmniChangePassword = new JMenuItem("修改密码");
        jmniExit = new JMenuItem("退出系统");

        // 创建【个人信息】菜单及其菜单项
        jmnuPersonalInfo = new JMenu("个人信息");
        jmniChangeInfo = new JMenuItem("修改个人信息");
        jmniHealthRecord = new JMenuItem("查看病历信息");
        jmniDiseaseHistory = new JMenuItem("查看既往病史");
        jmniBodyCheck = new JMenuItem("查看体检报告");

        // 设置菜单栏
        this.setJMenuBar(jmnb1);

        // 添加【系统设置】菜单
        jmnb1.add(jmnuSet);
        jmnuSet.add(jmniChangePassword);
        jmnuSet.addSeparator();
        jmnuSet.add(jmniExit);

        // 添加【个人信息】菜单
        jmnb1.add(jmnuPersonalInfo);
        jmnuPersonalInfo.add(jmniChangeInfo);
        jmnuPersonalInfo.addSeparator();
        jmnuPersonalInfo.add(jmniHealthRecord);
        jmnuPersonalInfo.add(jmniDiseaseHistory);
        jmnuPersonalInfo.add(jmniBodyCheck);

        // 创建面板
        jpMain = new JPanel();
        jpMain.setBounds(100, 118, 230, 170);
        jpMain.setLayout(null);
        jtaInfo = new JTextArea();
        jtaInfo.setBounds(0, 0, 230, 170);
        // 加载文本框内容
        loadText();
        jtaInfo.setEditable(false);
        jtaInfo.setOpaque(false);
        jtaInfo.setLineWrap(true);
        jpMain.add(jtaInfo);
        jpMain.setOpaque(false);

        // 设置背景图片
        ImageIcon img = new ImageIcon("image/bg2.png");
        jlbImg = new JLabel(img);
        jlbImg.setBounds(0, 21, img.getIconWidth(), img.getIconHeight());
        
        // 把分层面板的布局置空
        this.getLayeredPane().setLayout(null);
        // 添加背景图片和文本框
        this.getLayeredPane().add(jlbImg, -1);
        this.getLayeredPane().add(jpMain, 0);
        
        // 设置窗口属性
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setSize(480, 420);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // 关闭窗口单击事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitSystem();
            }
        });

        // 设置菜单 
        // 【修改密码】菜单项单击事件
        jmniChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestFocus(false);
                new ChangePasswordFrame(userType);
            }
        });

        // 【退出系统】菜单项单击事件
        jmniExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        // 个人信息菜单
        // 【修改个人信息】菜单项单击事件
        jmniChangeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientChangeInfoFrame infof1 = new PatientChangeInfoFrame();
            }
        });

        // 【查看病历信息】菜单项单击事件
        jmniHealthRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientSeeHRFrame hrf1 = new PatientSeeHRFrame();
            }
        });

        // 【查看既往病史】菜单项单击事件
        jmniDiseaseHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiseaseHistoryFrame dh1 = new DiseaseHistoryFrame(false, Application.user.getID());
            }
        });

        // 【查看体检报告】菜单项单击事件
        jmniBodyCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientSeeBCFrame bcf1 = new PatientSeeBCFrame();
            }
        });

        /**
         * 此处JPanel jpMain内应显示病人个人信息
         */

    }

    /**
    * 退出系统
    */
    protected void exitSystem() {
        int choice = JOptionPane.showConfirmDialog(null, "你是否要退出系统？", "医院信息管理系统", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // 关闭当前窗口
            this.dispose();
            Application.loginFrame = new LoginFrame();
        }
    }

    public void loadText() {
        jtaInfo.setText(Application.user.toString());;
    }

}