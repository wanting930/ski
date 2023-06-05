package ad.product.service.Impl;

import java.util.List;

import ad.product.dao.ProductAdHistoryDao;
import ad.product.dao.ProductDao;
import ad.product.dao.Impl.ProductAdDaoImpl;
import ad.product.dao.Impl.ProductAdHistoryDaoImpl;
//>>>>>>> e6638a84f6a961976edf1a7acf72d24bfb983847:src/main/java/ad/product/service/Impl/productAdServiceImpl.java
import ad.product.service.ProductAdService;
import ad.product.vo.ProductAd;
import ad.product.vo.ProductAdHistory;
import product.vo.Product;

public class ProductAdServiceImpl implements ProductAdService {
	private ProductDao dao = new ProductAdDaoImpl();
	private ProductAdHistoryDao Hdao = new ProductAdHistoryDaoImpl();
	
	//新增
	@Override
	public ProductAd insert(Integer productId) {
		ProductAd pAd = new ProductAd();
		pAd.setProductID(productId);
		dao.insert(pAd);
		return pAd;
	}
	//刪除
	@Override
	public int delete(Integer productId) {
		ProductAdHistory pAd = new ProductAdHistory();
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
