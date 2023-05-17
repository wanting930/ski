package article.dao;

import java.util.List;

import article.vo.ArticleImage;

public interface ArticleImageDao {

	int insert(ArticleImage articleImage);

	int deleteByArticleImageID(Integer articleImageID);

	int updateByArticleImageID(ArticleImage articleImage);

	ArticleImage selectByArticleImageID(Integer articleImageID);

	List<ArticleImage> selectAll() throws ClassNotFoundException;

}