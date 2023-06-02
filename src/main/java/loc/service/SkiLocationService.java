package loc.service;

import java.util.List;

import loc.dao.SkiLocDao;
import loc.dao.SkiLocDaoImpl;
import loc.model.SkiLocation;

public class SkiLocationService {
	private SkiLocDao skiLocDao;
	
	public SkiLocationService() {
		skiLocDao =new SkiLocDaoImpl();
	}
	
	  
		//新增雪點
		public void addMap(SkiLocation skiLocation) {
		    skiLocDao.insert(skiLocation);
		    System.out.println("新增雪點成功");
		}
		
//	    public int deleteMap(int pointID) {
//	        return skiLocDao.deleteByPointID(pointID);
//	    }
//	    
//	    public int updateMap(SkiLocation skiLocation) {
//	        return skiLocDao.updateByPointID(skiLocation);
//	    }
		//狀態更新
		public void updateStatus(int pointID, String status) {
		    SkiLocation skiLocation = skiLocDao.selectByPointID(pointID);
		    if (skiLocation != null) {
		        skiLocation.setPointStatus(status);
		        skiLocDao.updateByPointID(skiLocation);
		        System.out.println("更新狀態成功");
		    } else {
		        System.out.println("找不到對應的雪点");
		    }
		}

		
		
	    public SkiLocation selectByIDMap(int pointID) {
	        return skiLocDao.selectByPointID(pointID);
	    }

	    public List<SkiLocation> selectAllMap() {
	        return skiLocDao.selectAll();
	    }
		
		
	}
	


