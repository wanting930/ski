package ad.product.Dao.Impl;

import java.util.List;

import org.hibernate.query.Query;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import ad.product.vo.productAd;
import product.vo.Product;
import ad.product.Dao.productDao;

public class productAdDaoImpl implements productDao {

//	getSession() getSession() = HibernateUtil.getgetSession()Factory().getCurrentgetSession()();

	@Override
	public int insert(productAd productAd) {
		getSession().persist(productAd);
		return -1;
	}

	@Override
	public int delete(Integer id) {
		productAd pAd = getSession().get(productAd.class, id);
		getSession().remove(pAd);
		return 1;
	}

	//新增
	@Override
	public int update(productAd productAd) {
		getSession().update(productAd);
		return 1;
	}
	
	//找廣告
	@Override
	public productAd selectAd(Integer id) {
		return getSession().get(productAd.class, id);
	}

	//選全商品
	public List<productAd> selectAllProduct() {
		return getSession().createQuery("from productAd", productAd.class).list();
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
