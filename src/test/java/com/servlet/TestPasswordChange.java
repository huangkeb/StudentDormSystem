package com.servlet;

import com.dao.UserDao;
import com.model.Admin;
import com.model.DormManager;
import com.model.Student;
import com.util.DbUtil;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.easymock.EasyMock.*;

public class TestPasswordChange {
    private PasswordServlet passwordServlet;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private UserDao userDao;
    private DbUtil dbUtil;
    @Before
    public void setup(){
        userDao = createMock(UserDao.class);
        dbUtil = createMock(DbUtil.class);
        passwordServlet = new PasswordServlet();
        passwordServlet.userDao = userDao;
        passwordServlet.dbUtil = dbUtil;
        request = createMock(HttpServletRequest.class);
        session = createMock(HttpSession.class);
        dispatcher = createMock(RequestDispatcher.class);
    }

    @Test
    public void testPasswordChangeAdminSuccess() throws Exception {
        Admin admin = createMock(Admin.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("login.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("admin");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(admin);
        EasyMock.expect(admin.getPassword()).andReturn("123456");
        EasyMock.expect(admin.getAdminId()).andReturn(0);
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);
        EasyMock.expect(userDao.adminUpdate(null,0,"12345678")).andReturn(1);

        dispatcher.forward(request,null);
        admin.setPassword("12345678");
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "修改成功 ");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(admin);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(admin);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeAdminFail() throws Exception {
        Admin admin = createMock(Admin.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("mainAdmin.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("admin");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(admin);
        EasyMock.expect(admin.getPassword()).andReturn("1234567");
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);

        dispatcher.forward(request,null);
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "原密码错误");
        request.setAttribute("mainPage", "admin/passwordChange.jsp");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(admin);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(admin);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeDormManagerSuccess() throws Exception {
        DormManager manager = createMock(DormManager.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("login.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("dormManager");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(manager);
        EasyMock.expect(manager.getPassword()).andReturn("123456");
        EasyMock.expect(manager.getDormManagerId()).andReturn(0);
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);
        EasyMock.expect(userDao.managerUpdate(null,0,"12345678")).andReturn(1);

        dispatcher.forward(request,null);
        manager.setPassword("12345678");
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "修改成功 ");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(manager);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(manager);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeDormManagerFail() throws Exception {
        DormManager manager = createMock(DormManager.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("mainManager.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("dormManager");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(manager);
        EasyMock.expect(manager.getPassword()).andReturn("1234567");
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);

        dispatcher.forward(request,null);
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "原密码错误");
        request.setAttribute("mainPage", "dormManager/passwordChange.jsp");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(manager);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(manager);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeStudentSuccess() throws Exception {
        Student student = createMock(Student.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("login.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("student");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(student);
        EasyMock.expect(student.getPassword()).andReturn("123456");
        EasyMock.expect(student.getStudentId()).andReturn(0);
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);
        EasyMock.expect(userDao.studentUpdate(null,0,"12345678")).andReturn(1);

        dispatcher.forward(request,null);
        student.setPassword("12345678");
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "修改成功 ");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(student);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(student);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeStudentFail() throws Exception {
        Student student = createMock(Student.class);
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(request.getRequestDispatcher("mainStudent.jsp")).andReturn(dispatcher);
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("student");
        EasyMock.expect(session.getAttribute("currentUser")).andReturn(student);
        EasyMock.expect(student.getPassword()).andReturn("1234567");
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);

        dispatcher.forward(request,null);
        request.setCharacterEncoding("utf-8");
        request.setAttribute("oldPassword", "123456");
        request.setAttribute("newPassword", "12345678");
        request.setAttribute("rPassword", "12345678");
        request.setAttribute("error", "原密码错误");
        request.setAttribute("mainPage", "student/passwordChange.jsp");
        dbUtil.closeCon(null);
        replay(request);
        replay(dispatcher);
        replay(session);
        replay(student);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
        verify(student);
        verify(dbUtil);
        verify(userDao);
    }

    @Test
    public void testPasswordChangeError() throws Exception {
        EasyMock.expect(request.getParameter("action")).andReturn("change");
        EasyMock.expect(request.getParameter("oldPassword")).andReturn("123456");
        EasyMock.expect(request.getParameter("newPassword")).andReturn("12345678");
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("error");
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(dbUtil.getCon()).andReturn(null);

        request.setCharacterEncoding("utf-8");
        dbUtil.closeCon(null);
        replay(request);
        replay(session);
        replay(dbUtil);
        replay(userDao);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dbUtil);
        verify(userDao);
    }
}
