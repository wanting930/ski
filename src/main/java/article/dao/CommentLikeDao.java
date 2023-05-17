package article.dao;

import java.util.List;

import article.vo.CommentLike;

public interface CommentLikeDao {

	int insert(CommentLike commentLike);

	int deleteByCommentLikeID(Integer commentLikeID);

	int updateByCommentLikeID(CommentLike commentLike);

	CommentLike selectByCommentLikeID(Integer commentLikeID);

	List<CommentLike> selectAll() throws ClassNotFoundException;

}