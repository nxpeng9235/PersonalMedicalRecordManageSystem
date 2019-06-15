package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actor.*;
import dao.PatientDAO;

public class PatientInfoFrame extends JFrame {

    JPanel jpCenter, jp1, jp2, jp3, jp4, jp5;
    JPanel jpSouth;

    JLabel jlbPID, jlbName, jlbSex;
    JLabel jlbAge, jlbID;
    JLabel jlbBirthplace, jlbJob, jlbMarriage;
    JLabel jlbAddress;
    JLabel jlbAllergy;

    JTextField jtfPID, jtfName, jtfSex;
    JTextField jtfAge, jtfID;
    JTextField jtfBirthplace, jtfJob, jtfMarriage;
    JTextField jtfAddress;
    JTextField jtfAllergy;
    JButton jbtExit;

    /**
    * 构造方法
    * 
    * @param title
    */
    public PatientInfoFrame(String id) {
        initGUI(id);
    }

    /**
    * 初始化用户界面
    */
    private void initGUI(String id) {
        // 创建组件
        jpCenter = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jpSouth = new JPanel();

        jlbPID = new JLabel("编号：");
        jlbName = new JLabel(" 姓名：");
        jlbSex = new JLabel(" 性别：");
        jlbAge = new JLabel("年龄：");
        jlbID = new JLabel("身份证号：");
        jlbBirthplace = new JLabel("出生地：");
        jlbJob = new JLabel("职业：");
        jlbMarriage = new JLabel("婚姻状况:");
        jlbAddress = new JLabel("通讯地址：");
        jlbAllergy = new JLabel("药物过敏史：");

        jtfPID = new JTextField(8);
        jtfName = new JTextField(10);
        jtfSex = new JTextField(6);
        jtfAge = new JTextField(7);
        jtfID = new JTextField(20);
        jtfBirthplace = new JTextField(9);
        jtfJob = new JTextField(8);
        jtfMarriage = new JTextField(5);
        jtfAddress = new JTextField(32);
        jtfAllergy = new JTextField(31);

        Patient p = PatientDAO.getPatientByID(id);
        jtfPID.setText(p.getID());
        jtfName.setText(p.getName());
        jtfSex.setText(p.getSex());
        jtfAge.setText(p.getAge());
        jtfID.setText(p.getIDNum());
        jtfBirthplace.setText(p.getBirthPlace());
        jtfJob.setText(p.getJob());
        jtfMarriage.setText(p.getHYS());
        jtfAddress.setText(p.getAddress());
        jtfAllergy.setText(p.getAllergy());

        jtfPID.setEditable(false);
        jtfName.setEditable(false);
        jtfSex.setEditable(false);
        jtfID.setEditable(false);
        jtfAge.setEditable(false);
        jtfBirthplace.setEditable(false);
        jtfJob.setEditable(false);
        jtfMarriage.setEditable(false);
        jtfAddress.setEditable(false);
        jtfAllergy.setEditable(false);

        jbtExit = new JButton("退出");

        // 添加组件
        jp1.add(jlbPID);
        jp1.add(jtfPID);
        jp1.add(jlbName);
        jp1.add(jtfName);
        jp1.add(jlbSex);
        jp1.add(jtfSex);
        jp2.add(jlbAge);
        jp2.add(jtfAge);
        jp2.add(jlbID);
        jp2.add(jtfID);
        jp3.add(jlbBirthplace);
        jp3.add(jtfBirthplace);
        jp3.add(jlbJob);
        jp3.add(jtfJob);
        jp3.add(jlbMarriage);
        jp3.add(jtfMarriage);
        jp4.add(jlbAddress);
        jp4.add(jtfAddress);
        jp5.add(jlbAllergy);
        jp5.add(jtfAllergy);

        jpCenter.setLayout(new GridLayout(5, 1));
        jpCenter.add(jp1);
        jpCenter.add(jp2);
        jpCenter.add(jp3);
        jpCenter.add(jp4);
        jpCenter.add(jp5);

        jpSouth.add(jbtExit);

        this.setLayout(new BorderLayout());
        this.add(jpCenter, "Center");
        this.add(jpSouth, "South");

        // 设置窗口属性
        this.setTitle("患者个人信息");
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setSize(450, 350);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // 给【关闭】按钮注册监听器
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // 关闭窗口
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 关闭窗口
                dispose();
            }
        });
    }
}