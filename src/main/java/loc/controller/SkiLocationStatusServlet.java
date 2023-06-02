package loc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loc.service.SkiLocationService;


@WebServlet("/loc/backend_statusmap")
public class SkiLocationStatusServlet extends HttpServlet{
	private SkiLocationService skiLocationService;

    public void init() {
        skiLocationService = new SkiLocationService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pointID = Integer.parseInt(request.getParameter("pointID"));
        String status = request.getParameter("status");

        if (status.equals("上架")) {
            skiLocationService.updateStatus(pointID, "上架中");
        } else if (status.equals("下架")) {
            skiLocationService.updateStatus(pointID, "下架中");
        } else {
            System.out.println("无效的状态");
        }

        response.sendRedirect(request.getContextPath() + "/list"); // 重定向到列表页面
    }
}

