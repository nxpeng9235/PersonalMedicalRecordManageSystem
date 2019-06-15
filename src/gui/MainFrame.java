package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    /**
     * 定义用户属性
     * 1：患者
     * 2：医生
     * 3：管理员
     */
    protected int userType;
    
    /**
    * 构造方法
    * 
    * @param title
    */
    public MainFrame(String title) {
        super(title);
    }

}
