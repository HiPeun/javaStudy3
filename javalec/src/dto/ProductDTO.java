package dto;

import java.sql.Date;

public class ProductDTO {
	
	
	private String p_code;
	private String p_name;
	private int p_price;
	private String p_content;
	private String p_img;
	private String p_useyn;
	private Date indate;
	
	public ProductDTO() {
		
	}
	public ProductDTO(String p_code, String p_name, int p_price, String p_content, String p_img, String p_useyn,
			Date indate) {
		super();
		this.p_code = p_code;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_content = p_content;
		this.p_img = p_img;
		this.p_useyn = p_useyn;
		this.indate = indate;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_img() {
		return p_img;
	}
	public void setP_img(String p_img) {
		this.p_img = p_img;
	}
	public String getP_useyn() {
		return p_useyn;
	}
	public void setP_useyn(String p_useyn) {
		this.p_useyn = p_useyn;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Override
	public String toString() {
		return "ProductDTO [p_code=" + p_code + ", p_name=" + p_name + ", p_price=" + p_price + ", p_content="
				+ p_content + ", p_img=" + p_img + ", p_useyn=" + p_useyn + ", indate=" + indate + "]";
	}
	
}
