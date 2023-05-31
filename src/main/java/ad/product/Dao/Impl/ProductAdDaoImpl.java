package ad.product.Dao.Impl;

import java.util.List;

import org.hibernate.query.Query;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import ad.product.vo.ProductAd;
import product.vo.Product;
import ad.product.Dao.ProductDao;

public class ProductAdDaoImpl implements ProductDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(ProductAd ProductAd) {
		getSession().persist(ProductAd);
		return -1;
	}

	@Override
	public int delete(Integer id) {
		ProductAd pAd = getSession().get(ProductAd.class, id);
		getSession().remove(pAd);
		return 1;
	}

	//新增
	@Override
	public int update(ProductAd ProductAd) {
		getSession().update(ProductAd);
		return 1;
	}
	
	//找廣告
	@Override
	public ProductAd selectAd(Integer id) {
		return getSession().get(ProductAd.class, id);
	}

	//選全商品
	public List<ProductAd> selectAllProduct() {
		return getSession().createQuery("from ProductAd", ProductAd.class).list();
	}
	
	public List<Product> searchProduct(String keyword){
		String hql = "FROM Product WHERE "
				+ "productName LIKE :keyword "
				+ "OR productDetail LIKE :keyword "
				+ "OR productClass LIKE :keyword";
		Query<Product> query = getSession().createQuery(hql,Product.class);
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}
	
	public List<Product> selectTopTenProducts(){
		String hql = "FROM Product ORDER BY productBuyPerson DESC";
		Query<Product> query = getSession().createQuery(hql,Product.class);
		return query.getResultList();
	}
	
	public List<Product> selectSaleProducts() {
		String hql = "FROM Product WHERE productStatus=0";
		Query<Product> query = getSession().createQuery(hql,Product.class);
		return query.getResultList();
	}
}
