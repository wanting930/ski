package mem.dao;

import java.util.List;

import mem.vo.Coach;

public interface CoachDao {

	int insert(Coach co);

	int deleteByCoachID(Integer coachID);

	int updateByCoachID(Coach co);

	Coach selectByCoachID(Integer coachID);

	List<Coach> selectAll();

}