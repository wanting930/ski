package article.dao;

import java.util.List;

import article.vo.ArticleLike;
import core.CoreDao;

public interface ArticleLikeDao extends CoreDao{

	int insert(ArticleLike articleLike);

	int deleteByArticleLikeID(Integer articleLikeID);

	int updateByArticleLikeID(ArticleLike articleLike);

	ArticleLike selectByArticleLikeID(Integer articleLikeID);
	
	ArticleLike selectByArticleIDuserID(Integer articleLikeID, Integer userID);
	
	ArticleLike selectByArticleID(Integer articleIDI,Integer userID);

	List<ArticleLike> selectAll() throws ClassNotFoundException;

}