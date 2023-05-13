package product.controller;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/ProductUpdata")
@MultipartConfig
public class ProductUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        Integer productId = parseIntegerParameter(request.getParameter("productId"));
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
            response.getWriter().write("failed due to date parsing error");
            return;
        }

        // Handling image upload
        Part imagePart = request.getPart("productImage");
        byte[] productImage = null;
        if (imagePart != null) {
            InputStream imageInputStream = imagePart.getInputStream();
            productImage = convertInputStreamToByteArray(imageInputStream);
        }

        if (updateProduct(productId, productClass, productName, productPrice, productQuantity, productImage, productDetail,
                productBuyPerson, productDateSql, productStatus) > 0) {
            // Product updated successfully
            response.getWriter().write("suc");
        } else {
            // Failed to update product
            response.getWriter().write("failed");
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

    private int updateProduct(Integer productId, String productClass, String productName, Integer productPrice, Integer productQuantity,
            byte[] productImage, String productDetail, Integer productBuyPerson, java.sql.Date productDate,
            String productStatus) {
        String sql = "UPDATE Product SET ProductClass=?, ProductName=?, ProductPrice=?, ProductQuantity=?, ProductImage=?, ProductDetail=?, ProductBuyPerson=?, ProductDate=?, ProductStatus=? WHERE ProductId=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6?useUnicode=true&characterEncoding=utf8", "root","password");
        		PreparedStatement pstmt = conn.prepareStatement(sql);) {
        	pstmt.setString(1, productClass);
        	pstmt.setString(2, productName);
        	pstmt.setInt(3, productPrice);
        	pstmt.setInt(4, productQuantity);
        	pstmt.setBytes(5, productImage);
        	pstmt.setString(6, productDetail);
        	pstmt.setInt(7, productBuyPerson);
        	pstmt.setDate(8, productDate);
        	pstmt.setString(9, productStatus);
        	pstmt.setInt(10, productId);
        	pstmt.executeUpdate();
        	return 1;
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	return -1;
        	}


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
