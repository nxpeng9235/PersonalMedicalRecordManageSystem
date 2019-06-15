package app;

import actor.*;
import gui.LoginFrame;
import gui.MainFrame;
import gui.RegisterFrame;
 
public class Application {
    /**
    * 登录用户
    */
    public static User user;
    /**
    * 登录窗口
    */
    public static LoginFrame loginFrame;
    /**
    * 主窗口
    */
    public static MainFrame mainFrame;
    /**
    * 注册窗口
    */
    public static RegisterFrame registerFrame;

    public static void init() {
        loginFrame = new LoginFrame();
    }
}