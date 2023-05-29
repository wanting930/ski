package qa.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import qa.Dao.qaDao;
import qa.Dao.Impl.qaDaoImpl;
import qa.service.qaService;
import qa.vo.qa;

public class qaServiceImpl implements qaService {

	private qaDao dao;
	public qaServiceImpl() {
		dao = new qaDaoImpl();
	}
	
	//new
	@Override
	public qa insert(Integer questionType, String questionTitle, String answerContent, Timestamp questionDate) {
		qa qa = new qa();
		qa.setQuestionType(questionType);
		qa.setQuestionTitle(questionTitle);
		qa.setAnswerContent(answerContent);
		qa.setQuestionDate(questionDate);
		return qa;
	}
	//delete
	@Override
	public boolean remove(Integer id) {
		return dao.deleteById(id) > 0;
	}
	//update
	@Override
	public qa edit(qa qz) {
		qa qa = dao.selectByID(qz.getQaID());
		qa.setQuestionType(qz.getQuestionType());
		qa.setQuestionTitle(qz.getQuestionTitle());
		qa.setAnswerContent(qz.getAnswerContent());
		qa.setQuestionDate(qz.getQuestionDate());
		dao.updata(qa);
		return qa;
	}
	
	//getAll
	public List<qa> qalist() {
		return dao.selectAll();
	}
}
