package product.controller;

import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// 獲取要刪除的產品ID
		Integer productID = Integer.parseInt(request.getParameter("productID"));

		// 創建ProductDaoImpl對象
		ProductDao productDao = new ProductDaoImpl();

		// 調用ProductDaoImpl中的deleteByProductID方法刪除產品
		int rowsDeleted = productDao.deleteByProductID(productID);
		
		String successResponse = "{ \"message\": \"刪除成功\" }";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(successResponse);

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}





