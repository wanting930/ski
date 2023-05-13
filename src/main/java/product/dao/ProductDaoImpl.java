package product.dao;

import product.vo.Product;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

	@Override
	public int insert(Product product) {
		String sql = "INSERT INTO Product (ProductClass, ProductName, ProductPrice, ProductQuantity, ProductImage, ProductDetail, ProductBuyPerson, ProductDate, ProductStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/ski");

			try (Connection conn = dataSource.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, product.getProductId());
				pstmt.setString(1, product.getProductClass());
				pstmt.setString(2, product.getProductName());
				pstmt.setInt(3, product.getProductPrice());
				pstmt.setInt(4, product.getProductQuantity());
				pstmt.setBytes(5, product.getProductImage());
				pstmt.setString(6, product.getProductDetail());
				pstmt.setInt(7, product.getProductBuyPerson());
				String productDateString = product.getProductDate();
				if (productDateString != null) {
					java.sql.Date productDate = java.sql.Date.valueOf(productDateString);
					pstmt.setDate(8, productDate);
				} else {
					pstmt.setNull(8, Types.DATE);
				}
				pstmt.setString(9, product.getProductStatus());
				return pstmt.executeUpdate();
			}    } catch (Exception e) {
			e.printStackTrace();

		}
		return -1;
	}

	public int deleteByProductID(Integer productID) {
		String sql = "delete from Product where productID = ?";
		try {
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/ski");

			try (Connection conn = dataSource.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, productID);
				return pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int updateByProductID(Product product) {
		String sql = "update Product " + " set" + " productClass = ?," + "productName=?," + "productPrice=?,"
				+ "productQuantity=?," + "productImage=?," + "productDetail=?, " + "productBuyPerson=?,"
				+ "productDate = ?," + "productStatus = ?" + " where productId =?";
		try {
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/ski");

			try (Connection conn = dataSource.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(10, product.getProductId());
				pstmt.setString(1, product.getProductClass());
				pstmt.setString(2, product.getProductName());
				pstmt.setInt(3, product.getProductPrice() );
				pstmt.setInt(4, product.getProductQuantity());
				pstmt.setBytes(5, product.getProductImage());
				pstmt.setString(6, product.getProductDetail());
				pstmt.setInt(7, product.getProductBuyPerson());
				String productDateString = product.getProductDate();
				if (productDateString != null) {
					java.sql.Date productDate = java.sql.Date.valueOf(productDateString);
					pstmt.setDate(8, productDate);
				} else {
					pstmt.setNull(8, Types.DATE);
				}
				pstmt.setString(9, product.getProductStatus());
				return pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Product> selectByProductID(Integer productID) {
		   String sql = "select * from Product where productID = ?";
		    List<Product> list = new ArrayList<Product>();
		    try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
		            PreparedStatement pstmt = conn.prepareStatement(sql);) {
		        pstmt.setInt(1, productID);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                Product product = new Product();
		                product.setProductId(rs.getInt("productId"));
		                product.setProductClass(rs.getString("productClass"));
		                product.setProductName(rs.getString("productName"));
		                product.setProductPrice(rs.getInt("productPrice"));
		                product.setProductQuantity(rs.getInt("productQuantity"));
		                product.setProductImage(rs.getBytes("productImage"));
		                product.setProductDetail(rs.getString("productDetail"));
		                product.setProductBuyPerson(rs.getInt("productBuyPerson"));
		                java.sql.Date productDate = rs.getDate("productDate");
		                if (productDate != null) {
		                    product.setProductDate(productDate.toString());
		                }
		                product.setProductStatus(rs.getString("productStatus"));
		                list.add(product);
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}
	

	@Override
	public List<Product> selectByProductClass(String productClass) {
	    String sql = "select * from Product where productClass = ?";
	    List<Product> list = new ArrayList<Product>();
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
	            PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        pstmt.setString(1, productClass);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("productId"));
	                product.setProductClass(rs.getString("productClass"));
	                product.setProductName(rs.getString("productName"));
	                product.setProductPrice(rs.getInt("productPrice"));
	                product.setProductQuantity(rs.getInt("productQuantity"));
	                product.setProductImage(rs.getBytes("productImage"));
	                product.setProductDetail(rs.getString("productDetail"));
	                product.setProductBuyPerson(rs.getInt("productBuyPerson"));
	                java.sql.Date productDate = rs.getDate("productDate");
	                if (productDate != null) {
	                    product.setProductDate(productDate.toString());
	                }
	                product.setProductStatus(rs.getString("productStatus"));
	                list.add(product);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public List<Product> selectByProductName(String productName) {
	    String sql = "SELECT * FROM Product WHERE productName LIKE ?";
	    List<Product> list = new ArrayList<Product>();
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, "%" + productName + "%");
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("productId"));
	                product.setProductClass(rs.getString("productClass"));
	                product.setProductName(rs.getString("productName"));
	                product.setProductPrice(rs.getInt("productPrice"));
	                product.setProductQuantity(rs.getInt("productQuantity"));
	                product.setProductImage(rs.getBytes("productImage"));
	                product.setProductDetail(rs.getString("productDetail"));
	                product.setProductBuyPerson(rs.getInt("productBuyPerson"));
	                java.sql.Date productDate = rs.getDate("productDate");
	                if (productDate != null) {
	                    product.setProductDate(productDate.toString());
	                }
	                product.setProductStatus(rs.getString("productStatus"));
	                list.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public List<Product> selectAll() throws ClassNotFoundException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    String sql = "select * from Product ";
	    List<Product> list = new ArrayList<Product>();
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql:///team6", "root", "password");
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery();) {
	        while (rs.next()) {
	            Product product = new Product();
	            product.setProductId(rs.getInt("productId"));
	            product.setProductClass(rs.getString("productClass"));
	            product.setProductName(rs.getString("productName"));
	            product.setProductPrice(rs.getInt("productPrice"));
	            product.setProductQuantity(rs.getInt("productQuantity"));
	            product.setProductImage(rs.getBytes("productImage"));
	            product.setProductDetail(rs.getString("productDetail"));
	            product.setProductBuyPerson(rs.getInt("productBuyPerson"));
	            java.sql.Date productDate = rs.getDate("productDate");
	            if (productDate != null) {
	                product.setProductDate(productDate.toString());
	            }
	            product.setProductStatus(rs.getString("productStatus"));
	            list.add(product);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
//=================main方法測試=================
	public static void main(String[] args) {
	    ProductDao productDao = new ProductDaoImpl();
	    Product product = new Product();
	    
	    product.setProductStatus("上架中");
	    product.setProductDetail("安安");
	    product.setProductQuantity(1);
	    product.setProductDate("2023-05-05");
	    product.setProductBuyPerson(2);
	    product.setProductPrice(4);
	    product.setProductClass("機能服飾");
	    product.setProductName("發熱衣");

	    // 讀取圖片檔案並將其轉換為 byte array
	    File file = new File("/Users/tomo/Downloads/istockphoto-878524608-612x612.jpg"); 
	    byte[] productImage = new byte[(int) file.length()];
	    try {
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(productImage);
	        fileInputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // 將圖片資料設定到 product 物件
	    product.setProductImage(productImage);

	    try {
	        productDao.insert(product);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDaoImpl();
//		Product product = new Product();
//		byte[] productImage = new byte[1];
//		product.setProductId(3);
//		product.setProductClass("安安");
//		product.setProductName("安安3");
//		product.setProductImage(productImage);
//		try {
//			productDao.updateByProductID(product);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDaoImpl();
//		Product product = new Product();
//		byte[] productImage = new byte[1];
//
//		try {
//			productDao.deleteByProductID(2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductID(3)) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductName("板")) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//
//		try {
//			for (Product pro : product.selectByProductClass("機能服飾")) {
//				System.out.println(pro.toString());
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		ProductDao product = new ProductDaoImpl();
//		
//		try {
//			for (Product emp : product.selectAll()) {
//				System.out.println(emp.toString());
//			
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
