<%@ page import="baleksab.pdsbattleship.bean.PlayerBean" %>
<%@ page import="baleksab.pdsbattleship.bean.LoginBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("playerBean") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    response.sendRedirect("menu.jsp");
    return;
%>