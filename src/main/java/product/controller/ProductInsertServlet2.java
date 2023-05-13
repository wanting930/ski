package product.controller;

import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/productAdd")
@MultipartConfig
public class ProductInsertServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String productClass = request.getParameter("productClass");
        String productName = request.getParameter("productName");
        Integer productPrice = parseIntegerParameter(request.getParameter("productPrice"));
        Integer productQuantity = parseIntegerParameter(request.getParameter("productQuantity"));
        String productDetail = request.getParameter("productDetail");
        Integer productBuyPerson = parseIntegerParameter(request.getParameter("productBuyPerson"));
        String productDateString = request.getParameter("productDate");
        String productStatus = request.getParameter("productStatus");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date productDateSql = null;
        try {
            java.util.Date productDateUtil = sdf.parse(productDateString);
            productDateSql = new java.sql.Date(productDateUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            response.getWriter().write("失敗，日期解析錯誤");
            return;
        }

        // 處理圖片上傳
        Part imagePart = request.getPart("productImage");
        byte[] productImage = null;
        if (imagePart != null) {
            InputStream imageInputStream = imagePart.getInputStream();
            productImage = convertInputStreamToByteArray(imageInputStream);
        }

        Product product = new Product();
        product.setProductClass(productClass);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductQuantity(productQuantity);
        product.setProductImage(productImage);
        product.setProductDetail(productDetail);
        product.setProductBuyPerson(productBuyPerson);
        product.setProductDate(productDateString);
        product.setProductStatus(productStatus);

        if (productDao.insert(product) > 0) {
            // 商品成功插入
            response.getWriter().write("成功");
        } else {
            // 插入商品失敗
            response.getWriter().write("失敗");
        }
    }

    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[5000000];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private Integer parseIntegerParameter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            try {
                return Integer.parseInt(parameter);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
	
