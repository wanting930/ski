package qa.service;

import java.sql.Timestamp;
import java.util.List;

import qa.vo.Qa;

public interface aService {

	//new
	Qa insert(Integer questionType, String questionTitle, String answerContent, Timestamp questionDate);

	//delete
	boolean remove(Integer id);

	//update
	Qa edit(Qa qz);

	List<Qa> qalist();
}