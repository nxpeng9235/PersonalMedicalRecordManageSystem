package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.*;
import actor.*;

public class DoctorSeePatientInfoFrame extends JFrame {
	
    private JPanel jpSearch, jpTable, jpButton1, jpButton2;
    private JLabel jlbSearch;
    private JTextField jtfSearch;
	private JButton jbtSearch, jbtInfo, jbtHR, jbtDH, jbtBC;
	private JButton jbtQuit;
	private JTable jt1;//表格
    private JScrollPane jsp1; //滚动条，需要添加到表格中
    
    private List<Patient> patients;
    private Vector<String> columns;
    private Vector<Vector<String>> rows;
	
	public DoctorSeePatientInfoFrame() {
		initGUI();
	}
	
    private void initGUI() {
        jpSearch = new JPanel();
        jpTable = new JPanel();
        jpButton1 = new JPanel();
        jpButton2 = new JPanel();

        jlbSearch = new JLabel("请输入被查询患者的ID：");
        jtfSearch = new JTextField(10);

        jbtSearch = new JButton("搜索患者");
        jbtInfo = new JButton("详细信息");
        jbtInfo.setPreferredSize(new Dimension(100, 50));
        jbtHR = new JButton("病历信息");
        jbtHR.setPreferredSize(new Dimension(100, 50));
        jbtDH = new JButton("既往病史");
        jbtDH.setPreferredSize(new Dimension(100, 50));
        jbtBC = new JButton("体检报告");
        jbtBC.setPreferredSize(new Dimension(100, 50));
        jbtQuit = new JButton("退出");

        // 往表格中添加患者信息
        patients = PatientDAO.getPatientList();

        columns = new Vector<String>();
        columns.clear();
        columns.add("患者编号");
        columns.add("患者姓名");
        columns.add("性别");
        columns.add("身份证号");

        rows = new Vector<Vector<String>>();
        // 填充表格
        fillTable();

        jsp1 = new JScrollPane(jt1);
        jpTable.add(jsp1);

        jpSearch.add(jlbSearch);
        jpSearch.add(jtfSearch);
        jpSearch.add(jbtSearch);

        jpButton1.setLayout(new GridLayout(4, 1));
        jpButton1.add(jbtInfo);
        jpButton1.add(jbtHR);
        jpButton1.add(jbtDH);
        jpButton1.add(jbtBC);

        jpButton2.add(jbtQuit);

        this.setLayout(new BorderLayout());
        this.add(jpSearch, "North");
        this.add(jpTable, "Center");
        this.add(jpButton1, "East");
        this.add(jpButton2, "South");

        // 设置窗口属性
        this.setTitle("查看患者信息");
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setSize(800, 500);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // 给【搜索患者】按钮注册监听器
        jbtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String strid = jtfSearch.getText().trim();
                if (strid.isEmpty()) {
                    // 重新获取患者列表
                    patients = PatientDAO.getPatientList();
                    // 更新数据
                    fillRows();
                    // 修改表格数据
                    ((DefaultTableModel) jt1.getModel()).fireTableDataChanged();
                    repaint();
                } else if (!isInteger(strid)) {
                    JOptionPane.showMessageDialog(null, "请输入正确的id号！", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 重新获取患者列表
                    patients = PatientDAO.getPatientListByID(strid);
                    // 更新数据
                    fillRows();
                    // 修改表格数据
                    ((DefaultTableModel) jt1.getModel()).fireTableDataChanged();
                    repaint();
                }
            }
        });

        // 给【详细信息】按钮注册监听器
        jbtInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jt1.getSelectedRow();
                if (index > -1) {
                    String str = (String) jt1.getValueAt(index, 0);
                    PatientInfoFrame pif1 = new PatientInfoFrame(str);
                }
            }
        });

        // 给【病历信息】按钮注册监听器
        jbtHR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jt1.getSelectedRow();
                if (index > -1) {
                    String str = (String) jt1.getValueAt(index, 0);
                    DoctorSeeHRFrame hrf1 = new DoctorSeeHRFrame(str);
                }
            }
        });

        // 给【既往病史】按钮注册监听器
        jbtDH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jt1.getSelectedRow();
                if (index > -1) {
                    String str = (String) jt1.getValueAt(index, 0);
                    DiseaseHistoryFrame dhf1 = new DiseaseHistoryFrame(true, str);
                }
            }
        });

        // 给【体检报告】按钮注册监听器
        jbtBC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jt1.getSelectedRow();
                if (index > -1) {
                    String str = (String) jt1.getValueAt(index, 0);
                    DoctorSeeBCFrame bcf1 = new DoctorSeeBCFrame(str);
                }
            }
        });

        // 给【退出】按钮注册监听器
        jbtQuit.addActionListener(new ActionListener() {
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
    
    public void fillTable() {
        fillRows();
        
        // 设置内容不可修改
        DefaultTableModel tableModel1 = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jt1 = new JTable(tableModel1);
        jt1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt1.getTableHeader().setReorderingAllowed(false);
        jt1.getTableHeader().setResizingAllowed(false);

        // 用渲染器实现居中显示
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jt1.setDefaultRenderer(Object.class, cr);

        // 设置表项宽度
        jt1.setRowHeight(50);
        TableColumn firsetColumn = jt1.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(100);
        TableColumn secondColumn = jt1.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(100);
        TableColumn thirdColumn = jt1.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(60);
        TableColumn fourthColumn = jt1.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(200);

        if (rows.isEmpty()) {
            JOptionPane.showMessageDialog(this, "没有符合条件的记录！", "错误提示", JOptionPane.WARNING_MESSAGE);
            jtfSearch.setText("");
        }

    }

    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private void fillRows() {
        rows.clear();
        for (Patient patient : patients) {
            Vector<String> currentRow = new Vector<String>();
            currentRow.addElement(patient.getID());
            currentRow.addElement(patient.getName());
            currentRow.addElement(patient.getSex());
            currentRow.addElement(patient.getIDNum());
            // 将当前行添加到记录行集
            rows.add(currentRow);
        }
    }	
}
