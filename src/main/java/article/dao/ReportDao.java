package article.dao;

import java.util.List;

import article.vo.Report;

public interface ReportDao {

	int insert(Report report);

	int deleteByReportID(Integer reportID);

	int updateByReportID(Report report);

	Report selectByReportID(Integer reportID);

	List<Report> selectAll() throws ClassNotFoundException;

}