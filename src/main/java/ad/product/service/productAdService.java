package ad.product.service;

import java.util.List;

import ad.product.vo.productAd;
import product.vo.Product;

public interface productAdService {

	//新增
	productAd insert(Integer productId);

	//刪除
	int delete(Integer productId);

	//選取上架商品
	List<Product> getProducts();

	//模糊查詢商品
	List<Product> search(String keyword);

}