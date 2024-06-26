<%@ page import="baleksab.pdsatari.bean.UserBean" %>
<%@ page import="baleksab.pdsatari.bean.LoginBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("userBean") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    response.sendRedirect("menu.jsp");
    return;
%>