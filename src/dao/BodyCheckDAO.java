package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import actor.*;
import database.ConnectionManager;

public class BodyCheckDAO {

    public static List<BodyCheck> getBodyCheckList(String pid) {
        // 声明体检报告列表变量
        List<BodyCheck> bodyChecks = new ArrayList<BodyCheck>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM body_check WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            while (rs.next()) {
                // 创建体检报告对象
                BodyCheck bc = new BodyCheck();
                // 设置对象属性
                bc.setID(rs.getInt("id"));
                bc.setPID(rs.getInt("pid"));
                bc.setDate(rs.getDate("check_time"));
                bc.setHeight(rs.getString("height"));
                bc.setWeight(rs.getString("weight"));
                bc.setWaistline(rs.getInt("waistline"));
                bc.setPulse(rs.getInt("pulse"));
                bc.setSBP(rs.getString("sbp"));
                bc.setDBP(rs.getString("dbp"));
                bc.setTC(rs.getString("tc"));
                bc.setTriglyceride(rs.getString("triglyceride"));
                bc.setLDL(rs.getString("ldl_c"));
                bc.setHDL(rs.getString("hdl_c"));
                bc.setFBG(rs.getString("fbg"));
                bc.setPBG(rs.getString("pbg"));
                // 将该对象添加到列表
                bodyChecks.add(bc);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return bodyChecks;
    }

    public static BodyCheck getBodyCheckByID(String id) {
        // 声明体检报告变量
        BodyCheck bc = new BodyCheck();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM body_check WHERE id = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(id));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            if (rs.next()) {
                // 设置对象属性
                bc.setID(rs.getInt("id"));
                bc.setPID(rs.getInt("pid"));
                bc.setDate(rs.getDate("check_time"));
                bc.setHeight(rs.getString("height"));
                bc.setWeight(rs.getString("weight"));
                bc.setWaistline(rs.getInt("waistline"));
                bc.setPulse(rs.getInt("pulse"));
                bc.setSBP(rs.getString("sbp"));
                bc.setDBP(rs.getString("dbp"));
                bc.setTC(rs.getString("tc"));
                bc.setTriglyceride(rs.getString("triglyceride"));
                bc.setLDL(rs.getString("ldl_c"));
                bc.setHDL(rs.getString("hdl_c"));
                bc.setFBG(rs.getString("fbg"));
                bc.setPBG(rs.getString("pbg"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return bc;
    }

    public static List<BodyCheck> getBodyCheckListByDate(String pid, String date) {
        // 声明体检报告列表变量
        List<BodyCheck> bodyChecks = new ArrayList<BodyCheck>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM body_check WHERE pid = ? AND check_time = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
            pstmt.setDate(2, Date.valueOf(date));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            while (rs.next()) {
                // 创建体检报告对象
                BodyCheck bc = new BodyCheck();
                // 设置对象属性
                bc.setID(rs.getInt("id"));
                bc.setPID(rs.getInt("pid"));
                bc.setDate(rs.getDate("check_time"));
                bc.setHeight(rs.getString("height"));
                bc.setWeight(rs.getString("weight"));
                bc.setWaistline(rs.getInt("waistline"));
                bc.setPulse(rs.getInt("pulse"));
                bc.setSBP(rs.getString("sbp"));
                bc.setDBP(rs.getString("dbp"));
                bc.setTC(rs.getString("tc"));
                bc.setTriglyceride(rs.getString("triglyceride"));
                bc.setLDL(rs.getString("ldl_c"));
                bc.setHDL(rs.getString("hdl_c"));
                bc.setFBG(rs.getString("fbg"));
                bc.setPBG(rs.getString("pbg"));
                // 将该对象添加到列表
                bodyChecks.add(bc);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return bodyChecks;
    }

    public static int newBodyCheck(BodyCheck bc) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "INSERT INTO body_check VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(bc.getPID()));
            pstmt.setDate(2, Date.valueOf(bc.getDate()));
            pstmt.setString(3, bc.getHeight());
            pstmt.setString(4, bc.getWeight());
            pstmt.setInt(5, Integer.valueOf(bc.getWaistline()));
            pstmt.setInt(6, Integer.valueOf(bc.getPulse()));
            pstmt.setString(7, bc.getSBP());
            pstmt.setString(8, bc.getDBP());
            pstmt.setString(9, bc.getTC());
            pstmt.setString(10, bc.getTriglyceride());
            pstmt.setString(11, bc.getLDL());
            pstmt.setString(12, bc.getHDL());
            pstmt.setString(13, bc.getFBG());
            pstmt.setString(14, bc.getPBG());

            // 执行更新操作，插入新记录
            re = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT id FROM body_check WHERE pid = ? AND check_time = ? AND height = ? AND weight = ? "
            + "AND waistline = ? AND pulse = ? AND sbp = ? AND dbp = ? AND tc = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(bc.getPID()));
            pstmt.setDate(2, Date.valueOf(bc.getDate()));
            pstmt.setString(3, bc.getHeight());
            pstmt.setString(4, bc.getWeight());
            pstmt.setInt(5, Integer.valueOf(bc.getWaistline()));
            pstmt.setInt(6, Integer.valueOf(bc.getPulse()));
            pstmt.setString(7, bc.getSBP());
            pstmt.setString(8, bc.getDBP());
            pstmt.setString(9, bc.getTC());

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回结果
            if (rs.next()) {
                // 设置id属性
                bc.setID(rs.getInt("id"));
            }

            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

    public static int update(BodyCheck bc) {
        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "UPDATE body_check SET check_time=?, height=?, weight=?, waistline=?, pulse=?, "
                + "sbp=?, dbp=?, tc=?, triglyceride=?, ldl_c=?, hdl_c=?, fbg=?, pbg=? WHERE id =?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setDate(1, Date.valueOf(bc.getDate()));
            pstmt.setString(2, bc.getHeight());
            pstmt.setString(3, bc.getWeight());
            pstmt.setInt(4, Integer.valueOf(bc.getWaistline()));
            pstmt.setInt(5, Integer.valueOf(bc.getPulse()));
            pstmt.setString(6, bc.getSBP());
            pstmt.setString(7, bc.getDBP());
            pstmt.setString(8, bc.getTC());
            pstmt.setString(9, bc.getTriglyceride());
            pstmt.setString(10, bc.getLDL());
            pstmt.setString(11, bc.getHDL());
            pstmt.setString(12, bc.getFBG());
            pstmt.setString(13, bc.getPBG());
            pstmt.setInt(14, Integer.valueOf(bc.getID()));

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

    public static int delete(String id) {
        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "DELETE FROM body_check WHERE id = ?";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(id));

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
