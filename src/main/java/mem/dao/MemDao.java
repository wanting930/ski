package mem.dao;

import java.util.List;

import mem.vo.Mem;

public interface MemDao {

	int insert(Mem mem);

	int deleteByUserID(Integer userID);

	int updateByUserID(Mem mem);

	Mem selectByUserID(Integer userID);

	List<Mem> selectAll();

}