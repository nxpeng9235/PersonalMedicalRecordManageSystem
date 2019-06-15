package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import actor.*;
import database.ConnectionManager;

public class PatientDAO {

    public static Patient getPatient(String name, String password) {
        // 声明患者
        Patient patient = null;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM patient WHERE pname = ? AND password = ?";

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
                patient = new Patient();
                // 设置patient属性
                patient.setID(rs.getInt("pid"));
                patient.setName(rs.getString("pname"));
                patient.setPassword(rs.getString("password"));
                patient.setSex(rs.getString("psex"));
                patient.setAge(rs.getInt("page"));
                patient.setIDNum(rs.getString("id_num"));
                patient.setBirthPlace(rs.getString("birth_place"));
                patient.setAddress(rs.getString("address"));
                patient.setJob(rs.getString("profession"));
                patient.setHYS(rs.getString("hys"));
                patient.setAllergy(rs.getString("allergy"));
                patient.setStatus(rs.getInt("status"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return patient;
    }

    public static Patient getPatientByID(String id) {
        // 声明患者列表变量
        Patient patient = new Patient();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM patient WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(id));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 判断是否有记录
            if (rs.next()) {
                // 设置对象属性
                patient.setID(rs.getInt("pid"));
                patient.setName(rs.getString("pname"));
                patient.setPassword(rs.getString("password"));
                patient.setSex(rs.getString("psex"));
                patient.setAge(rs.getInt("page"));
                patient.setIDNum(rs.getString("id_num"));
                patient.setBirthPlace(rs.getString("birth_place"));
                patient.setAddress(rs.getString("address"));
                patient.setJob(rs.getString("profession"));
                patient.setHYS(rs.getString("hys"));
                patient.setAllergy(rs.getString("allergy"));
                patient.setStatus(rs.getInt("status"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return patient;
    }
    
    public static int register(String name, String password) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "INSERT INTO patient VALUES (null,?,?,null,null,null,null,null,null,null,null,0);";

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
        
        query = "SELECT pid FROM patient WHERE pname = ? AND password = ?";
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
                re = rs.getInt("pid");
            }
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return re;
    }

    public static int update(Patient patient) {
        int re = 0;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "UPDATE patient SET pname=?, password=?, psex=?, page=?, id_num=?, "
                + "birth_place=?, address=?, profession=?, hys=?, allergy=? WHERE pid=?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setString(1, patient.getName());
            pstmt.setString(2, patient.getPassword());
            pstmt.setString(3, patient.getSex());
            pstmt.setInt(4, Integer.valueOf(patient.getAge()));
            pstmt.setString(5, patient.getIDNum());
            pstmt.setString(6, patient.getBirthPlace());
            pstmt.setString(7, patient.getAddress());
            pstmt.setString(8, patient.getJob());
            pstmt.setString(9, patient.getHYS());
            pstmt.setString(10, patient.getAllergy());
            pstmt.setInt(11, Integer.valueOf(patient.getID()));

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

    public static List<Patient> getPatientList() {
        // 声明患者列表变量
        List<Patient> patients = new ArrayList<Patient>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM patient;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            while (rs.next()) {
                // 创建患者对象
                Patient p = new Patient();
                // 设置对象属性
                p.setID(rs.getInt("pid"));
                p.setName(rs.getString("pname"));
                p.setPassword(rs.getString("password"));
                p.setSex(rs.getString("psex"));
                p.setAge(rs.getInt("page"));
                p.setIDNum(rs.getString("id_num"));
                p.setBirthPlace(rs.getString("birth_place"));
                p.setAddress(rs.getString("address"));
                p.setJob(rs.getString("profession"));
                p.setHYS(rs.getString("hys"));
                p.setAllergy(rs.getString("allergy"));
                p.setStatus(rs.getInt("status"));

                // 将该对象添加到列表
                patients.add(p);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return patients;
    }

    public static List<Patient> getPatientListByID(String pid) {
        // 声明患者列表变量
        List<Patient> patients = new ArrayList<Patient>();
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT * FROM patient WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            while (rs.next()) {
                // 创建患者对象
                Patient p = new Patient();
                // 设置对象属性
                p.setID(rs.getInt("pid"));
                p.setName(rs.getString("pname"));
                p.setPassword(rs.getString("password"));
                p.setSex(rs.getString("psex"));
                p.setAge(rs.getInt("page"));
                p.setIDNum(rs.getString("id_num"));
                p.setBirthPlace(rs.getString("birth_place"));
                p.setAddress(rs.getString("address"));
                p.setJob(rs.getString("profession"));
                p.setHYS(rs.getString("hys"));
                p.setAllergy(rs.getString("allergy"));
                p.setStatus(rs.getInt("status"));

                // 将该对象添加到列表
                patients.add(p);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return patients;
    }

    public static String getPatientNameByID(String pid) {
        String result = "";
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义Query字符串
        String query = "SELECT pname FROM patient WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            // 返回结果
            if (rs.next()) {
                result = String.valueOf(rs.getString("pname"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConn(conn);
        }

        return result;
    }
    
    public static int activatePatient(String pid) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义query字符串
        String query = "UPDATE patient SET status = 1 WHERE pid = ?";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
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

    public static int deletePatient(String pid) {
        int re = 0;
        // 获取数据库连接
        Connection conn = ConnectionManager.getConn();
        // 定义query字符串
        String query = "DELETE FROM patient WHERE pid = ?;";

        try {
            // 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(query);
            // 设置占位符
            pstmt.setInt(1, Integer.valueOf(pid));
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
