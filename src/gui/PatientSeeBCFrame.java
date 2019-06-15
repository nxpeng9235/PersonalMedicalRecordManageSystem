package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

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
import app.Application;

public class PatientSeeBCFrame extends JFrame {
	
	private JPanel jpSearch, jpTable, jpButton;
	private JLabel jlb1;
	private JTextField jtf1;
	private JButton jbtSearch, jbtDetail, jbtQuit;
	private JTable jt1;
    private JScrollPane jsp1; //滚动条，需要添加到表格中
    
    private List<BodyCheck> bodyChecks;
    private Vector<String> columns;
    private Vector<Vector<String>> rows;
    
	public PatientSeeBCFrame() {
		initGUI();
	}
	
    private void initGUI() {
        jpSearch = new JPanel();
        jpTable = new JPanel();
        jpButton = new JPanel();

        jlb1 = new JLabel("输入体检日期来搜索(YYYY-MM-DD)：");
        jtf1 = new JTextField(12);
        jbtSearch = new JButton("搜索");

        jbtDetail = new JButton("查看详细报告");
        jbtQuit = new JButton("退出");

        // 向表格中添加体检报告信息
        bodyChecks = BodyCheckDAO.getBodyCheckList(Application.user.getID());

        // 设置表头
        columns = new Vector<String>();
        columns.clear();
        columns.add("编号");
        columns.add("患者编号");
        columns.add("体检日期");

        rows = new Vector<Vector<String>>();
        
        // 填充表格
        fillTable();

        jsp1 = new JScrollPane(jt1);
        jpTable.add(jsp1);

        jpSearch.add(jlb1);
        jpSearch.add(jtf1);
        jpSearch.add(jbtSearch);

        jpButton.add(jbtDetail);
        jpButton.add(jbtQuit);

        this.setLayout(new BorderLayout());
        this.add(jpSearch, "North");
        this.add(jpTable, "Center");
        this.add(jpButton, "South");

        // 设置窗口属性
        this.setTitle("查看体检报告");
        ImageIcon icon = new ImageIcon("image/icon.jpg");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setSize(800, 500);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // 给【搜索】按钮注册监听器
        jbtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String strDate = jtf1.getText().trim();
                if (strDate.isEmpty()) {
                    // 重新获取体检报告列表
                    bodyChecks = BodyCheckDAO.getBodyCheckList(Application.user.getID());
                    // 更新数据
                    fillRows();
                    // 修改表格数据
                    ((DefaultTableModel) jt1.getModel()).fireTableDataChanged();
                    repaint();
                } else {
                    // 重新获取体检报告列表
                    bodyChecks = BodyCheckDAO.getBodyCheckListByDate(Application.user.getID(), strDate);
                    // 更新数据
                    fillRows();
                    // 修改表格数据
                    ((DefaultTableModel) jt1.getModel()).fireTableDataChanged();
                    repaint();

                    if (rows.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "没有符合条件的记录！", "错误提示", JOptionPane.WARNING_MESSAGE);
                        jtf1.setText("");
                    }
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

        // 给【查看详细报告】按钮注册监听器
        jbtDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = jt1.getSelectedRow();
                if (index > -1) {
                    String str = (String) jt1.getValueAt(index, 0);
                    BodyCheckFrame bcf1 = new BodyCheckFrame(false, str);
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
        thirdColumn.setPreferredWidth(100);
    }

    public void fillRows() {
        rows.clear();
        for (BodyCheck bc : bodyChecks) {
            Vector<String> currentRow = new Vector<String>();
            currentRow.addElement(bc.getID());
            currentRow.addElement(bc.getPID());
            currentRow.addElement(bc.getDate());
            // 将当前行添加到记录行集
            rows.add(currentRow);
        }
    }
}
