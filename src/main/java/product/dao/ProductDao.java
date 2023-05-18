package product.dao;

import product.vo.Product;

import java.util.List;

import core.CoreDao;


public interface ProductDao extends CoreDao {
	int insert(Product product);

	int updateByProductID(Product product);
	
	int deleteByProductID(Integer productID);
	
	byte[] loadingImage(Integer productID);

	List<Product> selectByProductClass(String productClass);
	
	Product selectByProductID(Integer productID);

	List<Product> selectByProductName(String productName);

	List<Product> selectAll() throws ClassNotFoundException;
}
