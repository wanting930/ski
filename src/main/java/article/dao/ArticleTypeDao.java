package article.dao;

import java.util.List;

import article.vo.ArticleType;

public interface ArticleTypeDao {

	int insert(ArticleType articleType);

	int deleteByArticleTypeID(Integer articleTypeID);

	int updateByArticleTypeID(ArticleType articleType);

	ArticleType selectByArticleTypeID(Integer articleTypeID);

	List<ArticleType> selectAll() throws ClassNotFoundException;

}