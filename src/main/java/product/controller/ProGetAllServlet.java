package product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import core.HibernateUtil;
import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;



@WebServlet("/getAll")
public class ProGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();resp.setContentType("text/html; charset=utf-8");
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = new ArrayList<Product>();
		try {
			list = dao.selectAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		resp.getWriter().write(jsonStr);
		System.out.println("success");
		
	}

}
