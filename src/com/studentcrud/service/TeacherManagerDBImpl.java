package com.studentcrud.service;

import com.studentcrud.config.DatabaseProp;
import com.studentcrud.user.Student;
import com.studentcrud.user.Subject;
import com.studentcrud.user.Teacher;
import com.studentcrud.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Teacher DAO
public class TeacherManagerDBImpl implements TeacherManager{
    private final DatabaseProp databaseProp = DatabaseProp.getInstance();
    Connection conn = null; //DBMS와 연결을 위한 객체
    PreparedStatement pstmt = null; //sql 실행을 위한 객체.
    ResultSet rs = null; //쿼리의 실행결과가 담겨있는 객체

    @Override
    public boolean findTeacherByIdAndPassword(String id, String pw) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from teacher where id = ? and pw = ?";
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
    public void addTeacher(Teacher teacher) throws IllegalArgumentException {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "insert into teacher(id, pw, name, subject) " +
                    "values (?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, teacher.getId());
            pstmt.setString(2, teacher.getPw());
            pstmt.setString(3, teacher.getName()); //맵핑
            pstmt.setString(4, teacher.getSubject().toString());
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
    public Teacher findById(String id) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from teacher where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { //rs 존재하면 반환
                return getTeacherByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword()); //DB 연결
            String sql = "select * from teacher";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                teachers.add(getTeacherByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return teachers;
    }

    @Override
    public boolean isExistenceTeacherById(String id) {
        return findById(id) != null;
    }

    @Override
    public void deleteTeacherById(String id) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from teacher where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) { // rs가 존재하면 삭제
                sql = "delete from teacher where id = ?";
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
    public void replaceTeacherName(String beforeName, String afterName) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "UPDATE teacher SET name = ? WHERE name = ?";
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
    public void replaceTeacherPassword(String beforePassword, String afterPassword) {

        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "UPDATE teacher SET pw = ? WHERE pw = ?";
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

    //--------------------------

    private Teacher getTeacherByResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId((resultSet.getString("id")));
        teacher.setPw((resultSet.getString("pw")));
        teacher.setName(resultSet.getString("name"));
        teacher.setSubject(Subject.valueOf(resultSet.getString("subject")));
        return teacher;
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
