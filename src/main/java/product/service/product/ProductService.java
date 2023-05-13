package product.service.product;

import java.sql.Date;
import java.util.List;

import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;



public class ProductService {
	private ProductDao dao ;
	
	public ProductService() {
		dao =new ProductDaoImpl();
	}
	//新增
	public Product insert(String ProductClass,String ProductName,Integer ProductPrice, byte[] ProductImage,
			String ProductDetail,Integer ProductBuyPerson,String ProductDate, String ProductStatus) {
		Product product = new Product();
		product.setProductClass(ProductClass);
		product.setProductName(ProductName);
		product.setProductPrice(ProductPrice);
		product.setProductImage(ProductImage);
		product.setProductDetail(ProductDetail);
		product.setProductBuyPerson(ProductBuyPerson);
		product.setProductDate(ProductDate);
		product.setProductStatus(ProductStatus);
		return product;
		
	}
	
	//更新
	public Product update(String ProductClass,String ProductName,Integer ProductPrice, byte[] ProductImage,
			String ProductDetail,Integer ProductBuyPerson,String ProductDate, String ProductStatus) {
	Product product = new Product();
	product.setProductClass(ProductClass);
	product.setProductName(ProductName);
	product.setProductPrice(ProductPrice);
	product.setProductImage(ProductImage);
	product.setProductDetail(ProductDetail);
	product.setProductBuyPerson(ProductBuyPerson);
	product.setProductDate(ProductDate);
	product.setProductStatus(ProductStatus);
	dao.updateByProductID(product);
	return product;
	}
	
	//刪除
	public void delete(Integer productID) {
		dao.deleteByProductID(productID);
	}
	
	//用商品ID搜尋
	public List<Product> selectByProductID(Integer productID){
		return dao.selectByProductID(productID);
	}
	
	
	//用商品類別搜尋
	public List<Product> selectByProductClass(String productClass){
		return dao.selectByProductClass(productClass);
	}
	
	
	
	//用商品名稱搜尋
	public List<Product> selectByProductName(String productName ) {
		return dao.selectByProductName(productName);
	}
	
	//全商品
	public List<Product> selectAll() throws Exception{
		return dao.selectAll();
	}
}
