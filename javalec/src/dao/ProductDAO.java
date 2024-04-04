package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hanul.common.DBManager;


import dto.ProductDTO;

public class ProductDAO {

	// 상품을 등록하고 채번 해온다.
	public static int insertProduct(ProductDTO pDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			con = DBManager.getConnection();
			// 상품 코드를 채번해온다.
			// 한마디로 상품을 등록하면 자동으로 상품 코드를 입력시켜주는 쿼리문이다.

			String sql = "INSERT INTO product(p_code, p_name, p_price, p_content, p_img, p_useyn, p_indate) "
					+ "VALUES(product_seq.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDto.getP_name());
			pstmt.setInt(2, pDto.getP_price());
			pstmt.setString(3, pDto.getP_content());
			pstmt.setString(4, pDto.getP_img());
			pstmt.setString(5, pDto.getP_useyn());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 상품 목록 조회 부분
	public List<ProductDTO> ProductList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> Prolist = new ArrayList<>();

		try {
			con = DBManager.getConnection();
			String sql = "SELECT * " + "FROM product";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDTO product = new ProductDTO();
				product.setP_code(rs.getString("p_code"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_content(rs.getString("P_content"));
				product.setP_img(rs.getString("p_img"));
				product.setP_useyn(rs.getString("P_useyn"));

				Prolist.add(product);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(con, pstmt, rs);
		}

		return Prolist;
	}

	public ProductDTO getProductList(String p_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO product = null;

		try {
			// 데이터베이스 연결
			con = DBManager.getConnection();

			// SQL 문 실행
			String sql = "SELECT * FROM product WHERE p_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();

			// 조회 결과가 있다면 ProductDTO 객체에 저장
			if (rs.next()) {
				product = new ProductDTO();
				product.setP_code(rs.getString("p_code"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_content(rs.getString("p_content"));
				product.setP_img(rs.getString("p_img"));
				product.setP_useyn(rs.getString("p_useyn"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			DBManager.close(con, pstmt, rs);
		}

		return product;
	}

	public int deleteProduct(String p_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			con = DBManager.getConnection();
			String sql = "DELETE FROM product WHERE p_code = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_code);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(con, pstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateProduct(ProductDTO updatedProduct) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    int result = 0;

	    try {
	        con = DBManager.getConnection();
	        String sql = "UPDATE product SET p_name = ?, p_price = ?, p_content = ?, p_img = ?, p_useyn = ? WHERE p_code = ?";
	        
	        pstmt = con.prepareStatement(sql);
	        
	        // updatedProduct 객체에서 값 가져와서 PreparedStatement에 설정
	        pstmt.setString(1, updatedProduct.getP_name());
	        pstmt.setInt(2, updatedProduct.getP_price());
	        pstmt.setString(3, updatedProduct.getP_content());
	        pstmt.setString(4, updatedProduct.getP_img());
	        pstmt.setString(5, updatedProduct.getP_useyn());
	        pstmt.setString(6, updatedProduct.getP_code());

	        result = pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(con, pstmt, null);
	    }

	    return result;
	}}