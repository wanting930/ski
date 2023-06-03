package ad.product.service;

import java.util.List;

import ad.product.vo.ProductAd;
import product.vo.Product;

public interface roductAdService {

	//新增
	ProductAd insert(Integer productId);

	//刪除
	int delete(Integer productId);

	//選取上架商品
	List<Product> getProducts();

	//模糊查詢商品
	List<Product> search(String keyword);

}