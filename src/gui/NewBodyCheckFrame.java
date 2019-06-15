package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actor.BodyCheck;
import dao.BodyCheckDAO;

public class NewBodyCheckFrame extends JFrame {

    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8;
    private JLabel jlbID, jlbPid, jlbDate, jlbHeight, jlbWeight, jlbWaistline, jlbPulse, jlbSBP, jlbDBP, jlbTC, jlbTri,
            jlbLDL, jlbHDL, jlbFBG, jlbPBG;
    private JTextField jtfID, jtfPid, jtfDate, jtfHeight, jtfWeight, jtfWaistline, jtfPulse, jtfSBP, jtfDBP, jtfTC,
            jtfTri, jtfLDL, jtfHDL, jtfFBG, jtfPBG;
    private JButton jbtSave, jbtQuit;

    public NewBodyCheckFrame(String pid) {
        initGUI(pid);
    }

    private void initGUI(String pid) {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();

        jlbID = new JLabel("编号：");
        jlbPid = new JLabel("患者编号：");
        jlbDate = new JLabel("体检日期：");
        jlbHeight = new JLabel("身高：");
        jlbWeight = new JLabel("体重：");
        jlbWaistline = new JLabel("腰围：");
        jlbPulse = new JLabel("脉搏：");
        jlbSBP = new JLabel("收缩压：");
        jlbDBP = new JLabel("舒张压：");
        jlbTC = new JLabel("总胆固醇：");
        jlbTri = new JLabel("甘油三酯：");
        jlbLDL = new JLabel("低密度脂蛋白胆固醇：");
        jlbHDL = new JLabel("高密度脂蛋白胆固醇：");
        jlbFBG = new JLabel("空腹血糖：");
        jlbPBG = new JLabel("餐后血糖：");

        jtfID = new JTextField(7);
        jtfPid = new JTextField(7);
        jtfDate = new JTextField(7);
        jtfHeight = new JTextField(15);
        jtfWeight = new JTextField(15);
        jtfWaistline = new JTextField(15);
        jtfPulse = new JTextField(15);
        jtfSBP = new JTextField(14);
        jtfDBP = new JTextField(14);
        jtfTC = new JTextField(13);
        jtfTri = new JTextField(13);
        jtfLDL = new JTextField(7);
        jtfHDL = new JTextField(7);
        jtfFBG = new JTextField(13);
        jtfPBG = new JTextField(13);

        jbtSave = new JButton("新建");
        jbtQuit = new JButton("退出");

        jtfID.setText(" *新建后可见*");
        jtfPid.setText(pid);

        jtfID.setEditable(false);
        jtfPid.setEditable(false);

        jp1.add(jlbID);
        jp1.add(jtfID);
        jp1.add(jlbPid);
        jp1.add(jtfPid);
        jp1.add(jlbDate);
        jp1.add(jtfDate);
        jp2.add(jlbHeight);
        jp2.add(jtfHeight);
        jp2.add(jlbWeight);
        jp2.add(jtfWeight);
        jp3.add(jlbWaistline);
        jp3.add(jtfWaistline);
        jp3.add(jlbPulse);
        jp3.add(jtfPulse);
        jp4.add(jlbSBP);
        jp4.add(jtfSBP);
        jp4.add(jlbDBP);
        jp4.add(jtfDBP);
        jp5.add(jlbTC);
        jp5.add(jtfTC);
        jp5.add(jlbTri);
        jp5.add(jtfTri);
        jp6.add(jlbLDL);
        jp6.add(jtfLDL);
        jp6.add(jlbHDL);
        jp6.add(jtfHDL);
        jp7.add(jlbFBG);
        jp7.add(jtfFBG);
        jp7.add(jlbPBG);
        jp7.add(jtfPBG);
        jp8.add(jbtSave);
        jp8.add(jbtQuit);

        this.setLayout(new GridLayout(8, 1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);

        this.setTitle("新建体检报告");
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setSize(800, 350);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // 给【退出】按钮注册监听器
        jbtQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // 关闭窗口
                dispose();
            }
        });

        // 给【新建】按钮注册监听器
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (jtfDate.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入日期！", "提示", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    newBC();
                }
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

    private void newBC() {
        BodyCheck bc1 = new BodyCheck();
        bc1.setPID(Integer.valueOf(jtfPid.getText().trim()));
        bc1.setDate(Date.valueOf(jtfDate.getText().trim()));
        bc1.setHeight(jtfHeight.getText().trim());
        bc1.setWeight(jtfWeight.getText().trim());
        bc1.setWaistline(Integer.valueOf(jtfWaistline.getText().trim()));
        bc1.setPulse(Integer.valueOf(jtfPulse.getText().trim()));
        bc1.setSBP(jtfSBP.getText().trim());
        bc1.setDBP(jtfDBP.getText().trim());
        bc1.setTC(jtfTC.getText().trim());
        bc1.setTriglyceride(jtfTri.getText().trim());
        bc1.setLDL(jtfLDL.getText().trim());
        bc1.setHDL(jtfHDL.getText().trim());
        bc1.setFBG(jtfFBG.getText().trim());
        bc1.setPBG(jtfPBG.getText().trim());

        int result = BodyCheckDAO.newBodyCheck(bc1);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "体检报告新建成功！");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "体检报告新建失败，请重试！", "创建失败", JOptionPane.WARNING_MESSAGE);
        }
    }
}
