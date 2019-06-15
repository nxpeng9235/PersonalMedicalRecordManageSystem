package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import actor.*;
import database.ConnectionManager;

public class DoctorDAO {

    public static Doctor getDoctor(String name, String password) {
        // 声明医生
        Doctor doctor = null;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM doctor WHERE dname = ? AND password = ?";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 判断是否有记录
            if (rs.next()) {
                doctor = new Doctor();
                // 设置doctor属性
                doctor.setID(rs.getInt("did"));
                doctor.setName(rs.getString("dname"));
                doctor.setPassword(rs.getString("password"));
                doctor.setSex(rs.getString("dsex"));
                doctor.setDept(rs.getString("dept"));
                doctor.setHospital(rs.getString("hospital"));
                doctor.setTitle(rs.getString("title"));
                doctor.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return doctor;
    }
    
    public static int register(String name, String password) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "INSERT INTO doctor VALUES (null,?,?,null,null,null,null,0);";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, name);
            pstmt.setString(2, password);

            // 执行更新操作，插入新记录
            re = pstmt.executeUpdate();
            // 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT did FROM doctor WHERE dname = ? AND password = ?";
        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, name);
            pstmt.setString(2, password);

            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 返回结果
            if (rs.next()) {
                // 设置id属性
                re = rs.getInt("did");
            }
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

    public static int update(Doctor doctor) {
        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "UPDATE doctor SET dname=?, password=?, dsex=?, dept=?, hospital=?, title=?, status=? WHERE did=?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getPassword());
            pstmt.setString(3, doctor.getSex());
            pstmt.setString(4, doctor.getDept());
            pstmt.setString(5, doctor.getHospital());
            pstmt.setString(6, doctor.getTitle());
            pstmt.setInt(7, doctor.getStatus());
            pstmt.setInt(8, Integer.valueOf(doctor.getID()));


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

    public static List<Doctor> getDoctorList() {
        // 声明医生列表变量
        List<Doctor> doctors = new ArrayList<Doctor>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义query字符串
        String query = "SELECT * FROM doctor;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            while (rs.next()) {
                // 创建医生对象
                Doctor d = new Doctor();
                // 设置对象属性
                d.setID(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                d.setPassword(rs.getString("password"));
                d.setSex(rs.getString("dsex"));
                d.setDept(rs.getString("dept"));
                d.setHospital(rs.getString("hospital"));
                d.setTitle(rs.getString("title"));
                d.setStatus(rs.getInt("status"));

                // 将该对象添加到列表
                doctors.add(d);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return doctors;
    }

    public static int activateDoctor(String did) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义query字符串
        String query = "UPDATE doctor SET status = 1 WHERE did = ?";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(did));
            // 执行更新操作
            re = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

    public static int deleteDoctor(String did) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义query字符串
        String query = "DELETE FROM doctor WHERE did = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(did));
            // 执行更新操作
            re = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }
}
