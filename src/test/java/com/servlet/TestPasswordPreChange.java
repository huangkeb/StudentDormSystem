package com.servlet;

import org.easymock.EasyMock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.easymock.EasyMock.*;

public class TestPasswordPreChange {
    private PasswordServlet passwordServlet;
    private HttpServletRequest request;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    @Before
    public void setUp(){
        request = createMock(HttpServletRequest.class);
        session = createMock(HttpSession.class);
        dispatcher = createMock(RequestDispatcher.class);
        passwordServlet = new PasswordServlet();
    }

    @After
    public void tearDown() {}

    @Test
    public void textPasswordPreChangeAdmin() throws ServletException, IOException {
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(request.getParameter("action")).andReturn("preChange");
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("admin");
        EasyMock.expect(request.getRequestDispatcher("mainAdmin.jsp")).andReturn(dispatcher);
        request.setAttribute("mainPage", "admin/passwordChange.jsp");
        request.setCharacterEncoding("utf-8");
        dispatcher.forward(request,null);

        replay(request);
        replay(session);
        replay(dispatcher);

        passwordServlet.doPost(request,null);
        verify(request);
        verify(session);
        verify(dispatcher);
    }

    @Test
    public void textPasswordPreChangeDormManager() throws ServletException, IOException {
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(request.getParameter("action")).andReturn("preChange");
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("dormManager");
        EasyMock.expect(request.getRequestDispatcher("mainManager.jsp")).andReturn(dispatcher);
        request.setAttribute("mainPage", "dormManager/passwordChange.jsp");
        request.setCharacterEncoding("utf-8");
        dispatcher.forward(request,null);

        replay(request);
        replay(session);
        replay(dispatcher);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
    }

    @Test
    public void textPasswordPreChangeStudent() throws ServletException, IOException {
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(request.getParameter("action")).andReturn("preChange");
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("student");
        EasyMock.expect(request.getRequestDispatcher("mainStudent.jsp")).andReturn(dispatcher);
        request.setAttribute("mainPage", "student/passwordChange.jsp");
        request.setCharacterEncoding("utf-8");
        dispatcher.forward(request,null);

        replay(request);
        replay(session);
        replay(dispatcher);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
        verify(dispatcher);
    }

    @Test
    public void textPasswordPreChangeError() throws ServletException, IOException {
        EasyMock.expect(request.getSession()).andReturn(session);
        EasyMock.expect(request.getParameter("action")).andReturn("preChange");
        EasyMock.expect(session.getAttribute("currentUserType")).andReturn("error");
        request.setCharacterEncoding("utf-8");

        replay(request);
        replay(session);

        passwordServlet.doPost(request,null);

        verify(request);
        verify(session);
    }
}
