package com.servlet;

import com.dao.UserDao;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class TestUserDao {
    private UserDao userDao;
    private Connection connection;
    private PreparedStatement pstmt;
    @Before
    public void setup(){
        connection = createMock(Connection.class);
        pstmt = createMock(PreparedStatement.class);
        userDao = new UserDao();
    }

    @After
    public void tearDown() {}

    @Test
    public void testAdminUpdate() throws Exception {
        String sql = "update t_admin set password=? where adminId=?";
        String password = "123456";
        int adminId = 0;

        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        pstmt.setString(1, password);
        pstmt.setInt(2, adminId);

        replay(connection);
        replay(pstmt);

        assertEquals(1,userDao.adminUpdate(connection,adminId,password));

        verify(connection);
        verify(pstmt);
    }

    @Test
    public void testManagerUpdate() throws Exception {
        String sql = "update t_dormmanager set password=? where dormManId=?";
        String password = "123456";
        int managerId = 0;

        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        pstmt.setString(1, password);
        pstmt.setInt(2, managerId);

        replay(connection);
        replay(pstmt);

        assertEquals(1,userDao.managerUpdate(connection,managerId,password));

        verify(connection);
        verify(pstmt);
    }

    @Test
    public void testStudentUpdate() throws Exception {
        String sql = "update t_student set password=? where studentId=?";
        String password = "123456";
        int studentId = 0;

        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        pstmt.setString(1, password);
        pstmt.setInt(2, studentId);

        replay(connection);
        replay(pstmt);

        assertEquals(1,userDao.studentUpdate(connection,studentId,password));

        verify(connection);
        verify(pstmt);
    }
}
