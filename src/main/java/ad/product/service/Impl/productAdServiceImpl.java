package ad.product.service.Impl;

import java.util.List;

import ad.product.Dao.productAdHistoryDao;
import ad.product.Dao.productDao;
import ad.product.Dao.Impl.productADHistoryDaoImpl;
import ad.product.Dao.Impl.productAdDaoImpl;
import ad.product.service.productAdService;
import ad.product.vo.productADHistory;
import ad.product.vo.productAd;
import product.vo.Product;

public class productAdServiceImpl implements productAdService {
	private productDao dao = new productAdDaoImpl();
	private productAdHistoryDao Hdao = new productADHistoryDaoImpl();
	
	//新增
	@Override
	public productAd insert(Integer productId) {
		productAd pAd = new productAd();
		pAd.setProductID(productId);
		dao.insert(pAd);
		return pAd;
	}
	//刪除
	@Override
	public int delete(Integer productId) {
		productADHistory pAd = new productADHistory();
		pAd.setProductID(productId);
		Hdao.insert(pAd);
		dao.delete(productId);
		return -1;
	}
	
	//選取上架商品
	@Override
	public List<Product> getProducts() {
		return dao.selectSaleProducts();
	}
	
	//模糊查詢商品
	@Override
	public List<Product> search(String keyword) {
		return dao.searchProduct(keyword);
	}
}
