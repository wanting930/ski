package product.vo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table
public class Product implements Serializable {
	
	private static final long serivalVersionUID = 1L;
	private Integer productID;
	private String productClass;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private byte[] productImage;
	private String productDetail;
	private Integer productBuyPerson;
	private String productDate;
	private String productStatus;
	

	@Override
	public String toString() {
		return "Product [productId=" + productID + ", productClass=" + productClass + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productImage="
				+ productImage + ", productDetail=" + productDetail + ", productBuyPerson=" + productBuyPerson
				+ ", productDate=" + productDate + ", productStatus=" + productStatus + "]";
	}
	public static byte[] getPicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	
}