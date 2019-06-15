package gui;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import actor.HealthRecord;
import app.Application;
import dao.HealthRecordDAO;

public class NewHealthRecordFrame extends JFrame {

    private JPanel jp1, jp2;
    private JPanel jpArea, jpa1, jpa2, jpa3, jpa4, jpa5, jpa6;
    private JLabel jlbID, jlbPid, jlbDate, jlbDid, jlbFeature, jlbYX, jlbJY, jlbDiagnosis, jlbMeasures, jlbAdvice;
    private JTextField jtfID, jtfPid, jtfDate, jtfDid;
    private JTextArea jtaFeature, jtaYX, jtaJY, jtaDiagnosis, jtaMeasures, jtaAdvice;
    private JButton jbtSave, jbtQuit;
    private JScrollPane jsp1, jsp2, jsp3, jsp4, jsp5, jsp6;

    public NewHealthRecordFrame(String pid) {
        initGUI(pid);
    }

    private void initGUI(String pid) {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jpArea = new JPanel();
        jpa1 = new JPanel();
        jpa2 = new JPanel();
        jpa3 = new JPanel();
        jpa4 = new JPanel();
        jpa5 = new JPanel();
        jpa6 = new JPanel();

        jlbID = new JLabel("病历编号：");
        jlbPid = new JLabel("患者编号：");
        jlbDid = new JLabel("医生编号：");
        jlbDate = new JLabel("就诊日期：");
        jlbFeature = new JLabel("    临 床 表 现： ");
        jlbYX = new JLabel("影像检查报告：");
        jlbJY = new JLabel("    检 验 报 告： ");
        jlbDiagnosis = new JLabel("    诊 断 结 果： ");
        jlbMeasures = new JLabel("    治 疗 措 施： ");
        jlbAdvice = new JLabel("     医          嘱：  ");

        jtfID = new JTextField(7);
        jtfPid = new JTextField(7);
        jtfDid = new JTextField(7);
        jtfDate = new JTextField(7);

        /**
         * 创建多行文本框
         */
        jtaFeature = new JTextArea(5, 48);
        jtaYX = new JTextArea(5, 48);
        jtaJY = new JTextArea(5, 48);
        jtaDiagnosis = new JTextArea(5, 48);
        jtaMeasures = new JTextArea(5, 48);
        jtaAdvice = new JTextArea(5, 48);

        /**
         * 设置多行文本框自动换行
         */
        jtaFeature.setLineWrap(true);
        jtaYX.setLineWrap(true);
        jtaJY.setLineWrap(true);
        jtaDiagnosis.setLineWrap(true);
        jtaMeasures.setLineWrap(true);
        jtaAdvice.setLineWrap(true);

        /**
         * 创建滚动窗格
         */
        jsp1 = new JScrollPane(jtaFeature);
        jsp2 = new JScrollPane(jtaYX);
        jsp3 = new JScrollPane(jtaJY);
        jsp4 = new JScrollPane(jtaDiagnosis);
        jsp5 = new JScrollPane(jtaMeasures);
        jsp6 = new JScrollPane(jtaAdvice);

        jbtSave = new JButton("新建");
        jbtQuit = new JButton("退出");

        jtfID.setText(" *新建后可见*");
        jtfPid.setText(pid);
        jtfDid.setText(Application.user.getID());

        jtfID.setEditable(false);
        jtfPid.setEditable(false);
        jtfDid.setEditable(false);

        jp1.add(jlbID);
        jp1.add(jtfID);
        jp1.add(jlbPid);
        jp1.add(jtfPid);
        jp1.add(jlbDid);
        jp1.add(jtfDid);
        jp1.add(jlbDate);
        jp1.add(jtfDate);
        jpa1.add(jlbFeature);
        jpa1.add(jsp1);
        jpa2.add(jlbYX);
        jpa2.add(jsp2);
        jpa3.add(jlbJY);
        jpa3.add(jsp3);
        jpa4.add(jlbDiagnosis);
        jpa4.add(jsp4);
        jpa5.add(jlbMeasures);
        jpa5.add(jsp5);
        jpa6.add(jlbAdvice);
        jpa6.add(jsp6);
        jpArea.setLayout(new GridLayout(6, 1));
        jpArea.add(jpa1);
        jpArea.add(jpa2);
        jpArea.add(jpa3);
        jpArea.add(jpa4);
        jpArea.add(jpa5);
        jpArea.add(jpa6);
        jp2.add(jbtSave);
        jp2.add(jbtQuit);

        this.setLayout(new BorderLayout());
        this.add(jp1, "North");
        this.add(jpArea, "Center");
        this.add(jp2, "South");

        this.setTitle("新建病历信息");
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
                } else {
                    newHR();
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

    private void newHR() {
        HealthRecord hr1 = new HealthRecord();
        hr1.setPID(Integer.valueOf(jtfPid.getText().trim()));
        hr1.setDID(Integer.valueOf(jtfDid.getText().trim()));
        hr1.setDate(Date.valueOf(jtfDate.getText().trim()));
        hr1.setFeature(jtaFeature.getText());
        hr1.setYX(jtaYX.getText());
        hr1.setJY(jtaJY.getText());
        hr1.setDiagnosis(jtaDiagnosis.getText());
        hr1.setMeasures(jtaMeasures.getText());
        hr1.setAdvice(jtaAdvice.getText());

        int result = HealthRecordDAO.newHealthRecord(hr1);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "体检报告保存成功！");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "体检报告保存失败，请重试！", "创建失败", JOptionPane.WARNING_MESSAGE);
        }
    }
}
