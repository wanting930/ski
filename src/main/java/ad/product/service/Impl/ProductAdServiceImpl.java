package ad.product.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ad.product.dao.ProductAdDao;
import ad.product.dao.ProductAdHistoryDao;
import ad.product.dao.Impl.ProductAdDaoImpl;
import ad.product.dao.Impl.ProductAdHistoryDaoImpl;
import ad.product.service.ProductAdService;
import ad.product.vo.ProductAd;
import ad.product.vo.ProductAdHistory;
import ad.product.vo.ProductDTO;
import product.dao.ProductDao;
import product.dao.ProductDaoImpl;
import product.vo.Product;

public class ProductAdServiceImpl implements ProductAdService {
	private ProductAdDao dao = new ProductAdDaoImpl();
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
	public int delete(Integer Id) {
		
		ProductAd pAd = dao.selectAd(Id);
		
		ProductAdHistory productAdHistory = new ProductAdHistory();
		productAdHistory.setProductID(pAd.getProductID());
		productAdHistory.setHistoryDateTime(new Timestamp(System.currentTimeMillis()));
		Hdao.insert(productAdHistory);
		dao.delete(Id);
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
	
	//選取全廣告
	public List<ProductDTO> productAdList(){
		List<ProductAd> pAd = dao.selectAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for(ProductAd ad : pAd) {
			int productID = ad.getProductID();

			Product product = dao.selectProduct(productID);
			
			ProductDTO pDTO = new ProductDTO();
			pDTO.setProductAD(ad.getProductAD());
			pDTO.setProductID(productID);
			pDTO.setProductName(product.getProductName());
			pDTO.setProductClass(product.getProductClass());
			pDTO.setProductPrice(product.getProductPrice());
			
			productDTOs.add(pDTO);
				
		}
		return productDTOs;
	}
	
	public List<Product> Topten(){
		return dao.selectTopTenProducts();
	}
}
