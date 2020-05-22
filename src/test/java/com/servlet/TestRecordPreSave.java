package com.servlet;

import com.dao.RecordDao;
import com.model.Record;
import com.util.DbUtil;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import static org.easymock.EasyMock.createMock;

public class TestRecordPreSave {
    private RecordServlet recordServlet;
    private RecordDao recordDao;
    private DbUtil dbUtil;
    private HttpServletRequest request;
    private HttpSession session;
    @Before
    public void setup(){
        recordServlet = new RecordServlet();
        recordServlet.dbUtil = dbUtil;
        recordServlet.recordDao = recordDao;

        recordDao = createMock(RecordDao.class);
        dbUtil = createMock(DbUtil.class);
        request = createMock(HttpServletRequest.class);
        session = createMock(HttpSession.class);
    }

    @After
    public void tearDown() {}

    @Test
    public void testRecordPreSave() throws Exception {
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn(null);
        EasyMock.expect(request.getParameter("s_studentText")).andReturn(null);
        EasyMock.expect(request.getParameter("buildToSelect")).andReturn(null);
        EasyMock.expect(request.getParameter("searchType")).andReturn(null);
        EasyMock.expect(request.getParameter("action")).andReturn("preSave");
        EasyMock.expect(request.getParameter("page")).andReturn(null);
        EasyMock.expect(request.getParameter("startDate")).andReturn(null);
        EasyMock.expect(request.getParameter("endDate")).andReturn(null);
        EasyMock.expect(request.getParameter("recordId")).andReturn(null);
        EasyMock.expect(request.getParameter("studentNumber")).andReturn(null);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);


        request.setCharacterEncoding("utf-8");
    }
}
