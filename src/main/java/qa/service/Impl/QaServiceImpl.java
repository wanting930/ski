package qa.service.Impl;

import java.sql.Timestamp;
import java.util.List;

<<<<<<< HEAD
import qa.Dao.Impl.QaDaoImpl;
=======
import qa.dao.Impl.QaDaoImpl;
>>>>>>> e6638a84f6a961976edf1a7acf72d24bfb983847
import qa.service.QaService;
import qa.vo.Qa;

public class QaServiceImpl implements QaService {

	private QaDaoImpl dao;

	public QaServiceImpl() {
		dao = new QaDaoImpl();
	}

	// new
	@Override
	public Qa insert(Integer questionType, String questionTitle, String answerContent, Timestamp questionDate) {
		Qa qa = new Qa();
		qa.setQuestionType(questionType);
		qa.setQuestionTitle(questionTitle);
		qa.setAnswerContent(answerContent);
		qa.setQuestionDate(questionDate);
		return qa;
	}

	// delete
	@Override
	public boolean remove(Integer id) {
		return dao.deleteById(id) > 0;
	}

	// update
	@Override
	public Qa edit(Qa qz) {
		Qa qa = dao.selectByID(qz.getQaID());
		qa.setQuestionType(qz.getQuestionType());
		qa.setQuestionTitle(qz.getQuestionTitle());
		qa.setAnswerContent(qz.getAnswerContent());
		qa.setQuestionDate(qz.getQuestionDate());
		dao.updata(qa);
		return qa;
	}

	// getAll
	public List<Qa> qalist() {
		return dao.selectAll();
	}
}
