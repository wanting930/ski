package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.dao.ProductDaoImpl;
import product.vo.Product;

@WebServlet("/productSelectByClass")
public class ProductSelectByProductClass extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        // 获取要查询的商品类别
        String productClass = request.getParameter("productClass");

        // 创建 ProductDaoImpl 对象
        ProductDaoImpl productDaoImpl = new ProductDaoImpl();

        // 调用 ProductDaoImpl 中的 selectByProductClass 方法查询产品
        List<Product> productList = productDaoImpl.selectByProductClass(productClass);

        // 将结果转换为 JSON 字符串
        Gson gson = new Gson();
        String json = gson.toJson(productList);

        // 设置响应的内容类型和字符编码
        response.setContentType("application/json;charset=utf-8");

        // 将 JSON 字符串写入响应
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}






