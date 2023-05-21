package product.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        System.out.println(request.toString());
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Integer productID = Integer.parseInt(readValue(request.getPart("productID")));
        String productClass = readValue(request.getPart("productClass"));
        String productName = readValue(request.getPart("productName"));
        Integer productPrice = Integer.parseInt(readValue(request.getPart("productPrice")));
        Integer productQuantity = Integer.parseInt(readValue(request.getPart("productQuantity")));
        String productDetail = readValue(request.getPart("productDetail"));
        String productStatus = readValue(request.getPart("productStatus"));

        // 處理圖片上傳
        Part imagePart = request.getPart("productImage");
        byte[] productImage = null;
        if (imagePart != null && imagePart.getSize() > 0) { // check if the imagePart has data
            InputStream imageInputStream = imagePart.getInputStream();
            productImage = convertInputStreamToByteArray(imageInputStream);
        }

        Product product = new Product();
        product.setProductID(productID);
        product.setProductClass(productClass);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductQuantity(productQuantity);
        if (productImage != null) { // only set the image if it's not null
            product.setProductImage(productImage);
        }
        product.setProductDetail(productDetail);
        product.setProductStatus(productStatus);
        JsonObject jsonResponse = new JsonObject();
        if (productDao.updateByProductID(product) > 0) {
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "成功");
        } else {
            jsonResponse.addProperty("status", "failure");
            jsonResponse.addProperty("message", "失敗");
        }
        System.out.println(productImage);
        Gson gson = new Gson();
        jsonResponse.addProperty("message", "新增成功");
        String jsonString = gson.toJson(jsonResponse);
        response.getWriter().write(jsonString);

	}
    
    

    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[500000];
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
