package com.studentcrud.service;

import com.studentcrud.config.DatabaseProp;
import com.studentcrud.user.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Student DAO
public class StudentManagerDBImpl implements StudentManager {
    private final DatabaseProp databaseProp = DatabaseProp.getInstance();
    Connection conn = null; //DBMS와 연결을 위한 객체
    PreparedStatement pstmt = null; //sql 실행을 위한 객체.
    ResultSet rs = null; //쿼리의 실행결과가 담겨있는 객체

    @Override
    public boolean findStudentByIdAndPassword(String id, String pw) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from student where id = ? and pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw); // 맵핑
            rs = pstmt.executeQuery(); //실행
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public void addStudent(Student student) throws IllegalArgumentException { // Done
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "insert into student(id, pw, student.name, k_score, e_score, m_score) " +
                    "values (?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getPw());
            pstmt.setString(3, student.getName());
            pstmt.setInt(4, student.getkScore());
            pstmt.setInt(5, student.geteScore());
            pstmt.setInt(6, student.getmScore()); //맵핑


            int res = pstmt.executeUpdate(); //실행
            if(res > 0) {
                System.out.println("입력 성공");
            }else {
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace(); //오류 내용 출력
        } finally {
            closeConnection();
        }
    }

    @Override
    public Student findById(String id) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from student where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { //rs 존재하면 반환
                return getStudentByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Student> findAll() { // Done
        List<Student> students = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword()); //DB 연결
            String sql = "select * from student";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                students.add(getStudentByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return students;
    }


    @Override
    public boolean isExistenceStudentById(String id) {
        return findById(id) != null;
    } //안 건드려도 됨

    @Override
    public void deleteStudentById(String id) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from student where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { // rs가 존재하면 삭제
                sql = "delete from student where id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    @Override
    public void replaceStudentName(String beforeName, String afterName) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "UPDATE student SET name = ? WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, afterName);
            pstmt.setString(2, beforeName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void replaceStudentPassword(String beforePassword, String afterPassword) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "UPDATE student SET pw = ? WHERE pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, afterPassword);
            pstmt.setString(2, beforePassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "UPDATE student SET k_score =?, e_score=?, m_score=? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.getkScore());
            pstmt.setInt(2, student.geteScore());
            pstmt.setInt(3, student.getmScore());
            pstmt.setString(4, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // ---------------------------------------------
    private Student getStudentByResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setName(resultSet.getString("name"));
        student.setId(resultSet.getString("id"));
        student.setkScore(resultSet.getInt("k_score"));
        student.seteScore(resultSet.getInt("e_score"));
        student.setmScore(resultSet.getInt("m_score"));
        student.setPw(resultSet.getString("pw"));
        return student;
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
