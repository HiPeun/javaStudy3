package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	            //상품 코드를 채번해온다. 
	            //한마디로 상품을 등록하면 자동으로 상품 코드를 입력시켜주는 쿼리문이다. 
	          

	            System.out.println(pDto.toString());
	            
	            String sql = "INSERT INTO product(p_code, p_name, p_price, p_content, p_img, p_useyn, p_indate) " +
	                         "VALUES(product_seq.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";

	            
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
    // 상품 삭제 메서드
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
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
