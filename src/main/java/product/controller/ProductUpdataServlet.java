package product.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.HibernateUtil;
import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;

@WebServlet("/productUpdate")
@MultipartConfig
public class ProductUpdataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

//        Integer productID = Integer.parseInt(readValue(request.getPart("productID")));
        String productClass = readValue(request.getPart("productClass"));
        String productName = readValue(request.getPart("productName"));
        Integer productPrice = Integer.parseInt(readValue(request.getPart("productPrice")));
        Integer productQuantity = Integer.parseInt(readValue(request.getPart("productQuantity")));
        String productDetail = readValue(request.getPart("productDetail"));
        String productStatus = readValue(request.getPart("productStatus"));

        // 處理圖片上傳
        Part imagePart = request.getPart("productImage");
        byte[] productImage = null;
        if (imagePart != null) {
            InputStream imageInputStream = imagePart.getInputStream();
            productImage = convertInputStreamToByteArray(imageInputStream);
        }

        Product product = new Product();
//        product.setProductID(productID);
        product.setProductClass(productClass);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductQuantity(productQuantity);
        product.setProductImage(productImage);
        product.setProductDetail(productDetail);
        product.setProductStatus(productStatus);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (productDao.updateByProductID(product) > 0) {
            // 商品成功更新
            builder.add("message", "更新成功");
        } else {
            // 更新商品失敗
            builder.add("message", "更新失敗");
        }
        response.getWriter().write(builder.build().toString());
    }

    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private String readValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
