package com.studentcrud.service;

import com.studentcrud.config.DatabaseProp;
import com.studentcrud.user.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void addStudent(Student student) throws IllegalArgumentException {
        try {
            String sql = "insert into student(seq, id, pw, name, k_score, e_score, m_score) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            pstmt.setInt(1, student.getSeq());
            pstmt.setString(2, student.getId());
            pstmt.setString(3, student.getPw());
            pstmt.setString(4, student.getName());
            pstmt.setInt(5, student.getkScore());
            pstmt.setInt(6, student.geteScore());
            pstmt.setInt(7, student.getmScore()); //맵핑
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
    public Student findById(String id) {
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
            String sql = "select * from student where id = ?";
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        try {
            if (rs.next()) { //rs 존재하면 반환
                return getStudentByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(databaseProp.getUrl(), databaseProp.getUserName(), databaseProp.getPassword());
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
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        try {
            if (rs.next()) { //rs 존재하면 반환
                String sql = "delete from student where id = ?";
                pstmt.setString(1, id);
                rs = pstmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//        int cnt = 0;
//        for (Student student : userList) {
//            if (id.equals(student.getId())) {
//                userList.remove(cnt);
//                break;
//            }
//            cnt++;
//        }
//    }

    @Override
    public void replaceStudentName(String beforeName, String afterName) {
        findById(beforeName).setName(afterName);
    }

    @Override
    public void replaceStudentPassword(String name, String afterPassword) {
        findById(name).setPw(afterPassword);
    }
// ---------------------------------------------
    private Student getStudentByResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId((resultSet.getString("id")));
        student.setPw((resultSet.getString("pw")));
        student.setName(resultSet.getString("name"));
        student.setkScore(resultSet.getInt("k_score"));
        student.setkScore(resultSet.getInt("e_score"));
        student.setkScore(resultSet.getInt("m_score"));
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
