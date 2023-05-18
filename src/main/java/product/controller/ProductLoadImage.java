package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import product.dao.ProductDaoImpl;
@WebServlet("/loadImage")
public class ProductLoadImage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productID = Integer.valueOf(request.getParameter("productID"));

        ProductDaoImpl productDaoImpl = new ProductDaoImpl();
        byte[] imageData = productDaoImpl.loadingImage(productID);

        // 將圖片轉換成 Base64 編碼的字串
        String base64Image = "";
        if (imageData != null) {
            base64Image = Base64.getEncoder().encodeToString(imageData);
        }

        // 建立 JSON 物件並設定圖片資料屬性
        JSONObject json = new JSONObject();
        json.put("imageData", base64Image);

        // 設定回應內容類型為 JSON
        response.setContentType("application/json");

        // 取得回應的字符輸出流
        PrintWriter out = response.getWriter();

        // 將 JSON 物件轉換成字串並輸出至回應
        out.print(json.toString());

        // 關閉輸出流
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
