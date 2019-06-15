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
import dao.DoctorDAO;
 
public class DoctorChangeInfoFrame extends JFrame {

    private JPanel jpCenter, jp1, jp2, jp3, jp4, jp5, jp6;
    private JPanel jpSouth;
 
    private JLabel jlbDID, jlbName, jlbSex;
    private JLabel jlbHospital, jlbDept, jlbTitle;
 
    private JTextField jtfDID, jtfName, jtfSex;
    private JTextField jtfHospital, jtfDept, jtfTitle;
    private JButton jbtSave;
    private JButton jbtExit;
 
    private Doctor d;

    /**
    * 构造方法
    * 
    * @param title
    */
    public DoctorChangeInfoFrame() {
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
        jp6 = new JPanel();
        jpSouth = new JPanel();

        jlbDID = new JLabel("编        号：");
        jlbName = new JLabel("姓        名：");
        jlbSex = new JLabel("性        别：");
        jlbHospital = new JLabel("所属医院：");
        jlbDept = new JLabel("所属科室：");
        jlbTitle = new JLabel("职        称：");

        jtfDID = new JTextField(10);
        jtfName = new JTextField(10);
        jtfSex = new JTextField(10);
        jtfHospital = new JTextField(10);
        jtfDept = new JTextField(10);
        jtfTitle = new JTextField(10);
        jbtSave = new JButton("保存");
        jbtExit = new JButton("退出");

        d = (Doctor) Application.user;
        jtfDID.setText(d.getID());
        jtfName.setText(d.getName());
        jtfSex.setText(d.getSex());
        jtfHospital.setText(d.getHospital());
        jtfDept.setText(d.getDept());
        jtfTitle.setText(d.getTitle());

        jtfDID.setEditable(false);
        jtfName.setEditable(false);

        // 添加组件
        jp1.add(jlbDID);
        jp1.add(jtfDID);
        jp2.add(jlbName);
        jp2.add(jtfName);
        jp3.add(jlbSex);
        jp3.add(jtfSex);
        jp4.add(jlbHospital);
        jp4.add(jtfHospital);
        jp5.add(jlbDept);
        jp5.add(jtfDept);
        jp6.add(jlbTitle);
        jp6.add(jtfTitle);

        jpCenter.setLayout(new GridLayout(2, 3));
        jpCenter.add(jp1);
        jpCenter.add(jp2);
        jpCenter.add(jp3);
        jpCenter.add(jp4);
        jpCenter.add(jp5);
        jpCenter.add(jp6);

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
                //修改信息
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
        String hospital = jtfHospital.getText();
        String dept = jtfDept.getText();
        String title = jtfTitle.getText();
        d.setSex(sex);
        d.setHospital(hospital);
        d.setDept(dept);
        d.setTitle(title);

        // 在数据库中修改
        r = DoctorDAO.update(d);
        if (r > 0) {
            JOptionPane.showMessageDialog(null, "信息修改成功！");
            Application.user = d;
            ((DoctorMainFrame) Application.mainFrame).loadText();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "信息修改失败，请重试！", "修改个人信息", JOptionPane.WARNING_MESSAGE);
        }
    }
}