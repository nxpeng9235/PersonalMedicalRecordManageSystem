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

public class HealthRecordDAO {

    public static List<HealthRecord> getHealthRecordList(String pid) {
        // 声明体检报告列表变量
        List<HealthRecord> healthRecords = new ArrayList<HealthRecord>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM health_record WHERE pid = ?;";

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
                HealthRecord hr = new HealthRecord();
                // 设置对象属性
                hr.setID(rs.getInt("id"));
                hr.setPID(rs.getInt("pid"));
                hr.setDID(rs.getInt("did"));
                hr.setDate(rs.getDate("time"));
                hr.setFeature(rs.getString("feature"));
                hr.setYX(rs.getString("yx_report"));
                hr.setJY(rs.getString("jy_report"));
                hr.setDiagnosis(rs.getString("diagnosis"));
                hr.setMeasures(rs.getString("measures"));
                hr.setAdvice(rs.getString("advice"));
                // 将该对象添加到列表
                healthRecords.add(hr);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return healthRecords;
    }

    public static HealthRecord getHealthRecordByID(String id) {
        // 声明体检报告变量
        HealthRecord hr = new HealthRecord();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM health_record WHERE id = ?;";

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
                hr.setID(rs.getInt("id"));
                hr.setPID(rs.getInt("pid"));
                hr.setDID(rs.getInt("did"));
                hr.setDate(rs.getDate("time"));
                hr.setFeature(rs.getString("feature"));
                hr.setYX(rs.getString("yx_report"));
                hr.setJY(rs.getString("jy_report"));
                hr.setDiagnosis(rs.getString("diagnosis"));
                hr.setMeasures(rs.getString("measures"));
                hr.setAdvice(rs.getString("advice"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return hr;
    }

    public static List<HealthRecord> getHealthRecordListByDate(String pid, String date) {
        // 声明体检报告列表变量
        List<HealthRecord> healthRecords = new ArrayList<HealthRecord>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM health_record WHERE pid = ? AND time = ?;";

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
                HealthRecord hr = new HealthRecord();
                // 设置对象属性
                hr.setID(rs.getInt("id"));
                hr.setPID(rs.getInt("pid"));
                hr.setDID(rs.getInt("did"));
                hr.setDate(rs.getDate("time"));
                hr.setFeature(rs.getString("feature"));
                hr.setYX(rs.getString("yx_report"));
                hr.setJY(rs.getString("jy_report"));
                hr.setDiagnosis(rs.getString("diagnosis"));
                hr.setMeasures(rs.getString("measures"));
                hr.setAdvice(rs.getString("advice"));
                // 将该对象添加到列表
                healthRecords.add(hr);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return healthRecords;
    }

    public static int newHealthRecord(HealthRecord hr) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "INSERT INTO health_record VALUES (null,?,?,?,?,?,?,?,?,?);";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(hr.getPID()));
            pstmt.setInt(2, Integer.valueOf(hr.getDID()));
            pstmt.setDate(3, Date.valueOf(hr.getDate()));
            pstmt.setString(4, hr.getFeature());
            pstmt.setString(5, hr.getYX());
            pstmt.setString(6, hr.getJY());
            pstmt.setString(7, hr.getDiagnosis());
            pstmt.setString(8, hr.getMeasures());
            pstmt.setString(9, hr.getAdvice());

            // 执行更新操作，插入新记录
            re = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT id FROM health_record WHERE pid = ? AND did = ? AND time = ? AND feature = ? AND yx_report = ? "
                + "AND jy_report = ? AND diagnosis = ? AND measures = ? AND advice = ?;";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(hr.getPID()));
            pstmt.setInt(2, Integer.valueOf(hr.getDID()));
            pstmt.setDate(3, Date.valueOf(hr.getDate()));
            pstmt.setString(4, hr.getFeature());
            pstmt.setString(5, hr.getYX());
            pstmt.setString(6, hr.getJY());
            pstmt.setString(7, hr.getDiagnosis());
            pstmt.setString(8, hr.getMeasures());
            pstmt.setString(9, hr.getAdvice());

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回结果
            if (rs.next()) {
                // 设置id属性
                hr.setID(rs.getInt("id"));
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

    public static int update(HealthRecord hr) {
        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "UPDATE health_record SET time = ?, feature = ?, yx_report = ?, jy_report = ?, "
                + "diagnosis = ?, measures = ?, advice = ? WHERE id = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setDate(1, Date.valueOf(hr.getDate()));
            pstmt.setString(2, hr.getFeature());
            pstmt.setString(3, hr.getYX());
            pstmt.setString(4, hr.getJY());
            pstmt.setString(5, hr.getDiagnosis());
            pstmt.setString(6, hr.getMeasures());
            pstmt.setString(7, hr.getAdvice());
            pstmt.setInt(8, Integer.valueOf(hr.getID()));

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
        String query = "DELETE FROM health_record WHERE id = ?";

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
