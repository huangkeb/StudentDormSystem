package com.servlet;

import com.dao.DormBuildDao;
import com.dao.RecordDao;
import com.model.Record;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class TestRecordDao {
    private DormBuildDao dormBuildDao;
    private RecordDao recordDao;
    private Connection connection;
    private PreparedStatement pstmt;
    @Before
    public void setup(){
        recordDao = new RecordDao();
        dormBuildDao = createMock(DormBuildDao.class);
        recordDao.dormBuildDao = dormBuildDao;
        connection = createMock(Connection.class);
        pstmt = createMock(PreparedStatement.class);
    }

    @After
    public void tearDown(){}

    @Test
    public void testRecordDelete() throws Exception {
        String sql = "delete from t_record where recordId=?";
        String recordId = "0";
        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        pstmt.setString(1, recordId);

        replay(connection);
        replay(pstmt);

        assertEquals(1,recordDao.recordDelete(connection,recordId));

        verify(connection);
        verify(pstmt);
    }

    @Test
    public void testRecordUpdate() throws Exception {
        String sql = "update t_record set studentNumber=?,studentName=?,dormBuildId=?,dormName=?,detail=? where recordId=?";
        Record record = createMock(Record.class);

        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        EasyMock.expect(record.getStudentNumber()).andReturn("1");
        EasyMock.expect(record.getStudentName()).andReturn("2");
        EasyMock.expect(record.getDormBuildId()).andReturn(3);
        EasyMock.expect(record.getDormName()).andReturn("4");
        EasyMock.expect(record.getDetail()).andReturn("5");
        EasyMock.expect(record.getRecordId()).andReturn(6);

        pstmt.setString(1,"1");
        pstmt.setString(2,"2");
        pstmt.setInt(3,3);
        pstmt.setString(4,"4");
        pstmt.setString(5,"5");
        pstmt.setInt(6,6);

        replay(connection);
        replay(record);
        replay(pstmt);

        assertEquals(1,recordDao.recordUpdate(connection,record));

        verify(connection);
        verify(record);
        verify(pstmt);
    }

    @Test
    public void testRecordAdd() throws Exception {
        String sql = "insert into t_record values(null,?,?,?,?,?,?)";
        Record record = createMock(Record.class);

        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeUpdate()).andReturn(1);
        EasyMock.expect(record.getStudentNumber()).andReturn("1");
        EasyMock.expect(record.getStudentName()).andReturn("2");
        EasyMock.expect(record.getDormBuildId()).andReturn(3);
        EasyMock.expect(record.getDormName()).andReturn("4");
        EasyMock.expect(record.getDate()).andReturn("5");
        EasyMock.expect(record.getDetail()).andReturn("6");


        pstmt.setString(1,"1");
        pstmt.setString(2,"2");
        pstmt.setInt(3,3);
        pstmt.setString(4,"4");
        pstmt.setString(5,"5");
        pstmt.setString(6,"6");

        replay(connection);
        replay(record);
        replay(pstmt);

        assertEquals(1,recordDao.recordAdd(connection,record));

        verify(connection);
        verify(record);
        verify(pstmt);
    }

    @Test
    public void testRecordShow() throws Exception {
        String sql = "select * from t_record t1 where t1.recordId=?";
        String recordId = "0";
        ResultSet rs = createMock(ResultSet.class);
        EasyMock.expect(connection.prepareStatement(sql)).andReturn(pstmt);
        EasyMock.expect(pstmt.executeQuery()).andReturn(rs);
        EasyMock.expect(rs.next()).andReturn(true);
        EasyMock.expect(rs.getInt("recordId")).andReturn(1);
        EasyMock.expect(rs.getString("studentNumber")).andReturn("12");
        EasyMock.expect(rs.getString("studentName")).andReturn("XXX");
        EasyMock.expect(rs.getInt("dormBuildId")).andReturn(7);
        EasyMock.expect(String.valueOf(1)).andReturn("1");
        //EasyMock.expect(DormBuildDao.dormBuildName(connection, 777)).andReturn("1234");
        EasyMock.expect(rs.getString("dormName")).andReturn("YYY");
        EasyMock.expect(rs.getString("date")).andReturn("aaa");
        EasyMock.expect(rs.getString("detail")).andReturn("bbb");

        pstmt.setString(1, recordId);

        replay(connection);
        replay(pstmt);
        replay(rs);
        replay(dormBuildDao);

        recordDao.recordShow(connection,recordId);

        verify(connection);
        verify(pstmt);
        verify(rs);
        verify(dormBuildDao);
    }
}
