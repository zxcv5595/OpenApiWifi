package com.zerobase01.zerobase01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WifiSaveResult")
public class WifiSaveResult extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Wifi Save Result</title></head>");
        out.println("<body>");
        out.println("<h1 style='text-align:center;font-weight:bold;'>" + request.getAttribute("message") + "</h1>");
        out.println("<div style='text-align:center'><a href='/index.jsp'>홈으로 가기</a></div>");
        out.println("</body>");
        out.println("</html>");
    }
}
