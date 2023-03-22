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
            pstmt.setString(1, id);
            pstmt.setString(2, pw); // 맵핑
            pstmt = conn.prepareStatement(sql); // 맵핑 완료

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
            String sql = "insert into teacher(teacher.seq, id, pw, teacher.name, subject) " +
                    "values (?, ?, ?, ?, ?)";
            pstmt.setInt(1, teacher.getSeq());
            pstmt.setString(2, teacher.getId());
            pstmt.setString(3, teacher.getPw());
            pstmt.setString(4, teacher.getName());
            pstmt.setString(5, String.valueOf(teacher.getSubject())); //맵핑
            pstmt = conn.prepareStatement(sql); //맵핑 완료

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
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        try {
            if (rs.next()) { //rs 존재하면 반환
                return getTeacherByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
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
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        try {
            if (rs.next()) { //rs 존재하면 반환
                String sql = "delete from teacher where id = ?";
                pstmt.setString(1, id);
                rs = pstmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replaceTeacherName(String beforeName, String afterName) {
        findById(beforeName).setName(afterName);
    }

    @Override
    public void replaceTeacherPassword(String name, String afterPassword) {
        findById(name).setPw(afterPassword);
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
