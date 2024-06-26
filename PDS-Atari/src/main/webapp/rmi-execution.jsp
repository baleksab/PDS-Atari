<%@ page import="baleksab.pdsatari.bean.RegisterBean" %>
<%@ page import="java.rmi.registry.Registry" %>
<%@ page import="java.rmi.registry.LocateRegistry" %>
<%@ page import="baleksab.pdsatari.rmi.IDeleteChatHistory" %>
<%@ page import="java.rmi.Naming" %>
<%@ page import="java.rmi.NotBoundException" %><%--
  Created by IntelliJ IDEA.
  User: c425
  Date: 27.6.2024.
  Time: 00:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        IDeleteChatHistory deleteChatHistory = (IDeleteChatHistory) Naming.lookup("rmi://localhost:8081/DeleteChatHistory");
        deleteChatHistory.deleteChatHistory();
    } catch (Exception ignored) {

    }

    response.sendRedirect("chat.jsp");
%>
