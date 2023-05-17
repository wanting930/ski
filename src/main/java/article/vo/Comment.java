package article.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentID;
	private Integer articleID;
	private Integer userID;
	private String commentContent;
	private java.sql.Timestamp commentDateTime;
	private java.sql.Timestamp commentModified;
	private Integer commentLike;
	//
	public Integer getCommentID() {
		return commentID;
	}
	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}
	public Integer getArticleID() {
		return articleID;
	}
	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public java.sql.Timestamp getCommentDateTime() {
		return commentDateTime;
	}
	public void setCommentDateTime(java.sql.Timestamp commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
	public java.sql.Timestamp getCommentModified() {
		return commentModified;
	}
	public void setCommentModified(java.sql.Timestamp commentModified) {
		this.commentModified = commentModified;
	}
	public Integer getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(Integer commentLike) {
		this.commentLike = commentLike;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	public Comment(Integer commentID, Integer articleID, Integer userID, String commentContent,
			java.sql.Timestamp commentDateTime, java.sql.Timestamp commentModified, Integer commentLike) {
		super();
		this.commentID = commentID;
		this.articleID = articleID;
		this.userID = userID;
		this.commentContent = commentContent;
		this.commentDateTime = commentDateTime;
		this.commentModified = commentModified;
		this.commentLike = commentLike;
	}
	//
	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", articleID=" + articleID + ", userID=" + userID
				+ ", commentContent=" + commentContent + ", commentDateTime=" + commentDateTime + ", commentModified="
				+ commentModified + ", commentLike=" + commentLike + "]";
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	

}
