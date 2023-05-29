package qa.service;

import java.sql.Timestamp;
import java.util.List;

import qa.vo.qa;

public interface qaService {

	//new
	qa insert(Integer questionType, String questionTitle, String answerContent, Timestamp questionDate);

	//delete
	boolean remove(Integer id);

	//update
	qa edit(qa qz);

	List<qa> qalist();
}