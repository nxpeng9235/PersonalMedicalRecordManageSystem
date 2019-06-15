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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import actor.DiseaseHistory;
import dao.*;

public class DiseaseHistoryFrame extends JFrame {
	
	private JPanel jp1, jp2;
	private JPanel jpArea, jpa1, jpa2, jpa3, jpa4, jpa5, jpa6;
	private JLabel jlbPid, jlbName, jlbHbp, jlbDiab, jlbChd, jlbCI, jlbLC, jlbFamHistory;
	private JTextField jtfPid, jtfName;
	private JTextArea jtaHbp, jtaDiab, jtaChd, jtaCI, jtaLC, jtaFamHistory;
	private JScrollPane jsp1, jsp2, jsp3, jsp4, jsp5, jsp6;
	private JButton jbtSave, jbtQuit;
	
	public DiseaseHistoryFrame(boolean editable, String pid) {
		initGUI(editable, pid);
	}
	
	private void initGUI(boolean editable, String pid) {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jpArea = new JPanel();
		jpa1 = new JPanel();
		jpa2 = new JPanel();
		jpa3 = new JPanel();
		jpa4 = new JPanel();
		jpa5 = new JPanel();
		jpa6 = new JPanel();
		
		jlbPid = new JLabel("患者编号：");
		jlbName = new JLabel("患者姓名：");
		jlbHbp = new JLabel("    高血压：");
		jlbDiab = new JLabel("    糖尿病：");
		jlbChd = new JLabel("    冠心病：");
		jlbCI = new JLabel("    脑梗塞：");
		jlbLC = new JLabel("    肝硬化：");
		jlbFamHistory = new JLabel("家族病史：");
		
		jtfPid = new JTextField(10);
		jtfName = new JTextField(10);
		
		/**
		 * 创建多行文本框
		 */
		jtaHbp = new JTextArea(5, 30);		
		jtaDiab = new JTextArea(5, 30);
		jtaChd = new JTextArea(5, 30);
		jtaCI = new JTextArea(5, 30);
		jtaLC = new JTextArea(5, 30);
		jtaFamHistory = new JTextArea(5, 30);
		
		/**
		 * 设置多行文本框自动换行
		 */
		jtaHbp.setLineWrap(true);
		jtaDiab.setLineWrap(true);
		jtaChd.setLineWrap(true);
		jtaCI.setLineWrap(true);
		jtaLC.setLineWrap(true);
		jtaFamHistory.setLineWrap(true);

		// 获取既往病史信息
		DiseaseHistory dh = DiseaseHistoryDAO.getDiseaseHistoryByPid(pid);
		jtfPid.setText(dh.getPID());
		jtfName.setText(PatientDAO.getPatientNameByID(pid));
		jtaHbp.setText(dh.getHBP());
		jtaDiab.setText(dh.getDIAB());
		jtaChd.setText(dh.getCHD());
		jtaCI.setText(dh.getCI());
		jtaLC.setText(dh.getLC());
		jtaFamHistory.setText(dh.getFamilyHistory());
		
		/**
		 * 设置可修改性
		 * 患者无法修改病历信息
		 * 医生可以修改病历信息
		 */
		jtfPid.setEditable(false);
		jtfName.setEditable(false);
		jtaHbp.setEditable(editable);
		jtaDiab.setEditable(editable);
		jtaChd.setEditable(editable);
		jtaCI.setEditable(editable);
		jtaLC.setEditable(editable);
		jtaFamHistory.setEditable(editable);
		
		jbtSave = new JButton("保存");
		jbtQuit = new JButton("退出");
		
		jp1.add(jlbPid);
		jp1.add(jtfPid);
		jp1.add(jlbName);
		jp1.add(jtfName);
		
		jsp1 = new JScrollPane(jtaHbp);
		jsp2 = new JScrollPane(jtaDiab);
		jsp3 = new JScrollPane(jtaChd);
		jsp4 = new JScrollPane(jtaCI);
		jsp5 = new JScrollPane(jtaLC);
		jsp6 = new JScrollPane(jtaFamHistory);
		
		jpa1.add(jlbHbp);
		jpa1.add(jsp1);
		jpa2.add(jlbDiab);
		jpa2.add(jsp2);
		jpa3.add(jlbChd);
		jpa3.add(jsp3);
		jpa4.add(jlbCI);
		jpa4.add(jsp4);
		jpa5.add(jlbLC);
		jpa5.add(jsp5);
		jpa6.add(jlbFamHistory);
		jpa6.add(jsp6);
		jpArea.setLayout(new GridLayout(6, 1));
		jpArea.add(jpa1);
		jpArea.add(jpa2);
		jpArea.add(jpa3);
		jpArea.add(jpa4);
		jpArea.add(jpa5);
		jpArea.add(jpa6);
		
		if (editable) {
			jp2.add(jbtSave);
		}
		jp2.add(jbtQuit);
		
		this.setLayout(new BorderLayout());
		this.add(jp1, "North");
		this.add(jpArea, "Center");
		this.add(jp2, "South");
		
		this.setTitle("既往病史");
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
        
        // 给【保存】按钮注册监听器
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	saveDH();
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
	
	private void saveDH() {
		DiseaseHistory diseaseHistory = new DiseaseHistory();
		diseaseHistory.setPID(Integer.valueOf(jtfPid.getText().trim()));
		diseaseHistory.setHBP(jtaHbp.getText());
		diseaseHistory.setDIAB(jtaDiab.getText());
		diseaseHistory.setCHD(jtaChd.getText());
		diseaseHistory.setCI(jtaCI.getText());
		diseaseHistory.setLC(jtaLC.getText());
		diseaseHistory.setFamilyHistory(jtaFamHistory.getText());

		int result = DiseaseHistoryDAO.update(diseaseHistory);
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "修改既往病史信息成功！");
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "既往病史信息修改失败，请重试", "失败", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
}
