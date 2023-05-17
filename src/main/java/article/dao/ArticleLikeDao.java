package article.dao;

import java.util.List;

import article.vo.ArticleLike;

public interface ArticleLikeDao {

	int insert(ArticleLike articleLike);

	int deleteByArticleLikeID(Integer articleLikeID);

	int updateByArticleLikeID(ArticleLike articleLike);

	ArticleLike selectByArticleLikeID(Integer articleLikeID);

	List<ArticleLike> selectAll() throws ClassNotFoundException;

}