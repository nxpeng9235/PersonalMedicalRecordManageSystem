package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionManager;
import actor.DiseaseHistory;

public class DiseaseHistoryDAO {

    public static DiseaseHistory getDiseaseHistoryByPid(String pid) {
        // 声明既往病史变量
        DiseaseHistory dh = new DiseaseHistory();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM disease_history WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            if (rs.next()) {
                // 设置对象属性
                dh.setPID(rs.getInt("pid"));
                dh.setHBP(rs.getString("hbp"));
                dh.setDIAB(rs.getString("diab"));
                dh.setCHD(rs.getString("chd"));
                dh.setCI(rs.getString("ci"));
                dh.setLC(rs.getString("lc"));
                dh.setFamilyHistory(rs.getString("family_history"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return dh;
    }

    public static int update(DiseaseHistory dh) {

        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "UPDATE disease_history SET hbp = ?, diab = ?, chd = ?, ci = ?, "
                + "lc = ?, family_history = ? WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, dh.getHBP());
            pstmt.setString(2, dh.getDIAB());
            pstmt.setString(3, dh.getCHD());
            pstmt.setString(4, dh.getCI());
            pstmt.setString(5, dh.getLC());
            pstmt.setString(6, dh.getFamilyHistory());
            pstmt.setInt(7, Integer.valueOf(dh.getPID()));

            // 执行更新操作，插入新记录
            re = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

    public static int createDiseaseHistory(int pid) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "INSERT INTO disease_history VALUES (?, null, null, null, null, null, null);";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, pid);

            // 执行更新操作，插入新记录
            re = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

}
