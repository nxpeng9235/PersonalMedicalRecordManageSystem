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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actor.*;
import app.Application;
import dao.PatientDAO;
 
public class PatientChangeInfoFrame extends JFrame {

    private JPanel jpCenter, jp1, jp2, jp3, jp4, jp5;
    private JPanel jpSouth;
 
    private JLabel jlbPID, jlbName, jlbSex;
    private JLabel jlbAge, jlbID;
    private JLabel jlbBirthplace, jlbJob, jlbMarriage;
    private JLabel jlbAddress;
    private JLabel jlbAllergy;
 
    private JTextField jtfPID, jtfName, jtfSex;
    private JTextField jtfAge, jtfID;
    private JTextField jtfBirthplace, jtfJob, jtfMarriage;
    private JTextField jtfAddress;
    private JTextField jtfAllergy;
    private JButton jbtSave;
    private JButton jbtExit;

    private Patient p;
 
    /**
    * 构造方法
    * 
    * @param title
    */
    public PatientChangeInfoFrame() {
        initGUI();
    }
 
    /**
    * 初始化用户界面
    */
    private void initGUI() {
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
        jbtSave = new JButton("保存");
        jbtExit = new JButton("退出");

        p = (Patient) Application.user;
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

        jpSouth.add(jbtSave);
        jpSouth.add(jbtExit);

        this.setLayout(new BorderLayout());
        this.add(jpCenter, "Center");
        this.add(jpSouth, "South");

        // 设置窗口属性
        this.setTitle("修改个人信息");
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

        // 给【保存】按钮注册监听器
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // 修改信息
                changeInfo();
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
    
    public void changeInfo() {
        int r = 0;
        // 获取文本框数据
        String sex = jtfSex.getText();
        String age = jtfAge.getText();
        String id_number = jtfID.getText();
        String bp = jtfBirthplace.getText();
        String job = jtfJob.getText();
        String marriage = jtfMarriage.getText();
        String addr = jtfAddress.getText();
        String alle = jtfAllergy.getText();
        p.setSex(sex);
        p.setAge(Integer.valueOf(age));
        p.setIDNum(id_number);
        p.setBirthPlace(bp);
        p.setJob(job);
        p.setHYS(marriage);
        p.setAddress(addr);
        p.setAllergy(alle);
        p.setStatus(1);

        // 在数据库中修改
        r = PatientDAO.update(p);
        if (r > 0) {
            JOptionPane.showMessageDialog(null, "信息修改成功！");
            Application.user = p;
            ((PatientMainFrame) Application.mainFrame).loadText();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "信息修改失败，请重试！", "修改个人信息", JOptionPane.WARNING_MESSAGE);
        }
    }
}
