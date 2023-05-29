package ad.product.Dao;

import java.util.List;

import ad.product.vo.productAd;
import core.CoreDao;
import product.vo.Product;

public interface productDao extends CoreDao {

	int insert(productAd productAd);

	int delete(Integer id);

	int update(productAd productAd);

	productAd selectAd(Integer id);

	List<productAd> selectAllProduct();

	List<Product> searchProduct(String keyword);
	List<Product> selectSaleProducts();
	List<Product> selectTopTenProducts();
}