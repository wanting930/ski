package article.dao;

import java.util.List;

import article.vo.Comment;

public interface CommentDao {

	int insert(Comment comment);

	int deleteByCommentID(Integer commentID);

	int updateByCommentID(Comment comment);

	Comment selectByCommentID(Integer commentID);

	List<Comment> selectAll() throws ClassNotFoundException;

}