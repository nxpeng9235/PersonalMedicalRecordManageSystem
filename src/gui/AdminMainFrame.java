package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import actor.*;
import app.Application;
import dao.*;

public class AdminMainFrame extends MainFrame {

    // 定义控件
    private JPanel jpMain, jpQuit, jpPatient, jpDoctor, jpButton1, jpButton2, jpTable1, jpTable2;
    private JButton jbtActivate1, jbtActivate2, jbtDelete1, jbtDelete2, jbtQuit;
    private JTable jtPatient, jtDoctor;
    private JScrollPane jsp1, jsp2;
    private JTabbedPane jtp1; // 选项卡窗格

    private List<Patient> patients;
    private List<Doctor> doctors;
    private Vector<String> columns1;
    private Vector<String> columns2;
    private Vector<Vector<String>> rows1;
    private Vector<Vector<String>> rows2;

    /**
    * 构造方法
    * 
    * @param title
    */
    public AdminMainFrame() {
        super("医院信息管理系统 -- 管理员端");
        userType = 3;
        initGUI();
    }

    public void initGUI() {
        jpMain = new JPanel();
        jpButton1 = new JPanel();
        jpButton2 = new JPanel();
        jpPatient = new JPanel();
        jpDoctor = new JPanel();
        jpQuit = new JPanel();
        jpTable1 = new JPanel();
        jpTable2 = new JPanel();

        jbtActivate1 = new JButton("激活患者账号");
        jbtActivate2 = new JButton("激活医生账号");
        jbtDelete1 = new JButton("删除患者账号");
        jbtDelete2 = new JButton("删除医生账号");
        jbtQuit = new JButton("退出系统");

        jpButton1.add(jbtActivate1);
        jpButton1.add(jbtDelete1);
        jpButton2.add(jbtActivate2);
        jpButton2.add(jbtDelete2);
        jpQuit.add(jbtQuit);

        patients = PatientDAO.getPatientList();
        doctors = DoctorDAO.getDoctorList();

        columns1 = new Vector<String>();
        columns1.clear();
        columns1.add("患者编号");
        columns1.add("患者姓名");
        columns1.add("用户密码");
        columns1.add("激活状态");

        rows1 = new Vector<Vector<String>>();
        // 填充表格
        fillTable1();

        columns2 = new Vector<String>();
        columns2.clear();
        columns2.add("医生编号");
        columns2.add("医生姓名");
        columns2.add("用户密码");
        columns2.add("激活状态");

        rows2 = new Vector<Vector<String>>();
        // 填充表格
        fillTable2();

        jsp1 = new JScrollPane(jtPatient);
        jpTable1.add(jsp1);
        jsp2 = new JScrollPane(jtDoctor);
        jpTable2.add(jsp2);

        jpPatient.setLayout(new BorderLayout());
        jpPatient.add(jpTable1, "Center");
        jpPatient.add(jpButton1, "South");
        jpDoctor.setLayout(new BorderLayout());
        jpDoctor.add(jpTable2, "Center");
        jpDoctor.add(jpButton2, "South");

        // 设置选项卡控件
        jtp1 = new JTabbedPane();
        jtp1.add("患者用户列表", jpPatient);
        jtp1.add("医生用户列表", jpDoctor);
        jpMain.add(jtp1);
        
        this.setLayout(new BorderLayout());
        this.add(jpMain, "Center");
        this.add(jpQuit, "South");

        // 设置窗口属性
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setSize(480, 420);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // 给【激活患者账号】按钮注册监听器
        jbtActivate1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jtPatient.getSelectedRow();
                if (index > -1) {
                    String str = (String) jtPatient.getValueAt(index, 0);
                    int result = PatientDAO.activatePatient(str.trim());
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "患者账号激活成功！");
                        // 重新获取患者列表
                        patients = PatientDAO.getPatientList();
                        // 更新数据
                        fillRows1();
                        // 修改表格数据
                        ((DefaultTableModel) jtPatient.getModel()).fireTableDataChanged();
                        repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "账号激活失败，请重试！", "失败", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // 给【激活医生账号】按钮注册监听器
        jbtActivate2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jtDoctor.getSelectedRow();
                if (index > -1) {
                    String str = (String) jtDoctor.getValueAt(index, 0);
                    int result = DoctorDAO.activateDoctor(str.trim());
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "医生账号激活成功！");
                        // 重新获取患者列表
                        doctors = DoctorDAO.getDoctorList();
                        // 更新数据
                        fillRows2();
                        // 修改表格数据
                        ((DefaultTableModel) jtDoctor.getModel()).fireTableDataChanged();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "账号激活失败，请重试！", "失败", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // 给【删除患者账号】按钮注册监听器
        jbtDelete1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "你是否要删除该患者账号？此操作将不可被恢复", "医院信息管理系统", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    int index = jtPatient.getSelectedRow();
                    if (index > -1) {
                        String str = (String) jtPatient.getValueAt(index, 0);
                        int result = PatientDAO.deletePatient(str.trim());
                        if (result > 0) {
                            JOptionPane.showMessageDialog(null, "患者账号已删除！");
                            // 重新获取患者列表
                            patients = PatientDAO.getPatientList();
                            // 更新数据
                            fillRows1();
                            // 修改表格数据
                            ((DefaultTableModel) jtPatient.getModel()).fireTableDataChanged();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "账号删除失败，请重试！", "失败", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

        // 给【删除医生账号】按钮注册监听器
        jbtDelete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int choice = JOptionPane.showConfirmDialog(null, "你是否要删除该医生账号？此操作将不可被恢复", "医院信息管理系统", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    int index = jtDoctor.getSelectedRow();
                    if (index > -1) {
                        String str = (String) jtDoctor.getValueAt(index, 0);
                        int result = DoctorDAO.deleteDoctor(str.trim());
                        if (result > 0) {
                            JOptionPane.showMessageDialog(null, "医生账号已删除！");
                            // 重新获取患者列表
                            doctors = DoctorDAO.getDoctorList();
                            // 更新数据
                            fillRows2();
                            // 修改表格数据
                            ((DefaultTableModel) jtDoctor.getModel()).fireTableDataChanged();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "账号删除失败，请重试！", "失败", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

        // 给【退出系统】按钮注册监听器
        jbtQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });
    }
    
    public void fillTable1() {
        fillRows1();

        // 设置内容不可修改
        DefaultTableModel tableModel1 = new DefaultTableModel(rows1, columns1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jtPatient = new JTable(tableModel1);
        jtPatient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtPatient.getTableHeader().setReorderingAllowed(false);
        jtPatient.getTableHeader().setResizingAllowed(false);

        // 用渲染器实现居中显示
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jtPatient.setDefaultRenderer(Object.class, cr);

        // 设置表项宽度
        jtPatient.setRowHeight(50);
        TableColumn firsetColumn = jtPatient.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(80);
        TableColumn secondColumn = jtPatient.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(80);
        TableColumn thirdColumn = jtPatient.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(80);
        TableColumn fourthColumn = jtPatient.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(80);
    }

    public void fillRows1() {
        rows1.clear();
        for (Patient p : patients) {
            Vector<String> currentRow1 = new Vector<String>();
            currentRow1.addElement(p.getID());
            currentRow1.addElement(p.getName());
            currentRow1.addElement(p.getPassword());
            currentRow1.addElement((p.getStatus() == 1) ? "已激活" : "未激活");
            // 将当前行添加到记录行集1
            rows1.add(currentRow1);
        }
    }

    public void fillTable2() {
        fillRows2();

        // 设置内容不可修改
        DefaultTableModel tableModel2 = new DefaultTableModel(rows2, columns2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jtDoctor = new JTable(tableModel2);
        jtDoctor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtDoctor.getTableHeader().setReorderingAllowed(false);
        jtDoctor.getTableHeader().setResizingAllowed(false);

        // 用渲染器实现居中显示
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jtDoctor.setDefaultRenderer(Object.class, cr);

        // 设置表项宽度
        jtDoctor.setRowHeight(50);
        TableColumn firsetColumn = jtDoctor.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(80);
        TableColumn secondColumn = jtDoctor.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(80);
        TableColumn thirdColumn = jtDoctor.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(80);
        TableColumn fourthColumn = jtDoctor.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(80);
    }

    public void fillRows2() {
        rows2.clear();
        for (Doctor d : doctors) {
            Vector<String> currentRow2 = new Vector<String>();
            currentRow2.addElement(d.getID());
            currentRow2.addElement(d.getName());
            currentRow2.addElement(d.getPassword());
            currentRow2.addElement((d.getStatus() == 1) ? "已激活" : "未激活");
            // 将当前行添加到记录行集1
            rows2.add(currentRow2);
        }
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
}
