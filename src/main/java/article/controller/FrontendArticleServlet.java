package article.controller;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import article.dao.ArticleDao;
import article.dao.ArticleLikeDao;
import article.dao.ArticleTypeDao;
import article.dao.impl.ArticleDaoImpl;
import article.dao.impl.ArticleLikeDaoImpl;
import article.dao.impl.ArticleTypeDaoImpl;
import article.vo.Article;
import article.vo.ArticleLike;
import article.vo.ArticleType;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.vo.Member;

//http://localhost:8080/ski/BackendArticle
@WebServlet("/FrontendArticle")
public class FrontendArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action=req.getParameter("action");
        String type=req.getParameter("type");
        String artitcleStatus=req.getParameter("artitcleStatus");
//        System.out.println(artitcleStatus);
        String fowardPath="";
        switch(action) {
        	case "showAll":
        		System.out.println("showAll");
        		fowardPath=showAll(req, res);
        		break;
        	case "searchTitle":
        		fowardPath=searchTitle(req,res,"2");
        		break;
        	case "searchArticle":
        		fowardPath=searchArticle(req,res);
        		break;
        	case "getMemberUserName":
        		fowardPath=getMemberUserName(req,res);
        		break;
        	case "searchID":
        		fowardPath=searchID(req,res);
        		break;
        	case "searchArticleLikeID":
        		fowardPath=searchArticleLikeID(req,res);
        		break;
        	case "updateStatus":
        		fowardPath=updateStatus(req,res);
        		break;
        	default:{
        			System.out.println("收到未知請求");
        			break;
            }

        }
	}

	private String showAll (HttpServletRequest req,HttpServletResponse res) throws IOException{
    		res.setContentType("text/html; charset=utf-8");
    		ArticleDao dao = new ArticleDaoImpl();
    		List<Article> list1 = new ArrayList<Article>();
    		System.out.println("顯示成功");
    		try {
    			list1 = dao.selectAll();
    			System.out.println(list1);
    		} catch (ClassNotFoundException e) {
                System.out.println("顯示錯誤!");
                e.printStackTrace();
    		}
            Gson gson = new Gson();
            String jsonStr = gson.toJson(list1);
            res.getWriter().write(jsonStr);
//            System.out.println("success");
            return jsonStr;
	}
	
    // 搜尋內容
    private String searchTitle(HttpServletRequest req,HttpServletResponse res ,String type) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list3 = new ArrayList<Article>();
        try {
            String article5 = req.getParameter("articleTitle");
            list3 = dao.selectByArticleTitle(article5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list3);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
    //抓會員的名稱
    private String getMemberUserName(HttpServletRequest req,HttpServletResponse res) throws IOException {
    	res.setContentType("text/html; charset=utf-8");
        MemberDao mDao = new MemberDaoImpl();
        Member member = null; // 新增物件
        try {
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            member = mDao.selectById(userID); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(member); // 將儲存到的物件轉成json
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
    //抓文章分類的名稱
	private String searchID(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		ArticleType articleType5 = null; // 新增物件
		try {
			Integer articleType4 = Integer.parseInt(req.getParameter("articleTypeID"));
			articleType5 = dao.selectByArticleTypeID(articleType4); // 將結果存到物件
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(articleType5); // 將儲存到的物件轉成json
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
		
	}
	
	// 搜尋文章
    private String searchArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        Article article5 = null; // 新增物件
        try {
            Integer article4 = Integer.parseInt(req.getParameter("articleID"));
            article5 = dao.selectByArticleID(article4); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(article5); // 將儲存到的物件轉成json
//		res.setContentType("application/json");
//		res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;

    }
    
    //用文章ID找like的ID
    private String searchArticleLikeID(HttpServletRequest req,HttpServletResponse res) throws IOException {
    	res.setContentType("text/html; charset=utf-8");
        ArticleLikeDao dao = new ArticleLikeDaoImpl();
        ArticleLike articlelike = null; // 新增物件
//        List<ArticleLike> list = new ArrayList<ArticleLike>();
        try {
            Integer articleID = Integer.parseInt(req.getParameter("articleID"));
            articlelike = dao.selectByArticleID(articleID); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
    	 Gson gson = new Gson();
         String jsonStr = gson.toJson(articlelike); // 將儲存到的物件轉成json
         res.getWriter().write(jsonStr);
//         System.out.println("success");
         return jsonStr;
    }
    
    // 修改狀態
    private String updateStatus(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleLikeDao lDao = new ArticleLikeDaoImpl();
//        List<ArticleLike> list1 = new ArrayList<ArticleLike>();
        try {

        	ArticleLike articleLike2 = new ArticleLike(Integer.parseInt(req.getParameter("articleLikeID")),Integer.parseInt(req.getParameter("articleID")), Integer.parseInt(req.getParameter("userID")),req.getParameter("articleLikeStatus"));
            lDao.updateByArticleLikeID(articleLike2);
//            list1 = lDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(lDao);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
    
	
}
