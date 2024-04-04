package productMain;

import java.util.List;
import java.util.Scanner;

import dao.ProductDAO;
import dto.ProductDTO;

import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductDAO pDao = new ProductDAO();

		while (true) {
			System.out.println("================================< 상품 정보 메뉴  >===============================");
			System.out.println("\t1. 상품 등록 \t\t2. 상품 목록\t\t 3. 상품 검색 ");
			System.out.println("\t4. 상품 수정 \t\t5. 상품 삭제\t\t 6. 시스템 종료");
			System.out.print("\n\n메뉴를 선택해 주세요: ");
			int selNum = sc.nextInt();
			sc.nextLine();
//상품 등록 부분
			if (selNum == 1) {
				System.out.println("등록하실 상품의 이름을 입력해 주세요: ");
				String p_name = sc.nextLine();

				System.out.println("상품의 가격을 입력해 주세요: ");
				int p_price = sc.nextInt();
				sc.nextLine(); // 버퍼 비우기

				System.out.println("상품 설명을 입력해 주세요: ");
				String p_content = sc.nextLine();

				System.out.println("상품 이미지 URL을 입력해 주세요: ");
				String p_img = sc.nextLine();

				System.out.println("상품의 사용 여부를 입력해 주세요 (Y/N): ");
				String p_useyn = sc.nextLine();

				// 입력 받은 정보를 ProductDTO 객체에 저장
				ProductDTO pDto = new ProductDTO();
				pDto.setP_name(p_name);
				pDto.setP_price(p_price);
				pDto.setP_content(p_content);
				pDto.setP_img(p_img);
				pDto.setP_useyn(p_useyn);

				// 상품 등록 메서드 호출
				int result = ProductDAO.insertProduct(pDto);
				if (result > 0) {
					System.out.println(" ( " + p_name + " )" + "이(가) 상품이 등록되었습니다.");
				} else {
					System.out.println("상품 등록에 실패했습니다.");
				}
			} else if (selNum == 2) {
				// 상품 리스트를 product 에 담았다.
				List<ProductDTO> products = pDao.ProductList();
				System.out.println("등록된 상품 내역을 조회합니다. ");
				System.out.println("===========<조회된 상품 내역> ==============");
				// get을 이용해 product 에 담긴 목록들을 추출한다.
				for (ProductDTO product : products) {
					System.out.println("[상품 코드]" + product.getP_code() + "[상품 이름]" + product.getP_name() + "[상품 가격]"
							+ product.getP_price() + "[상품 설명]" + product.getP_content() + "[상품 이미지]"
							+ product.getP_img());
				}
				System.out.println("========================================");

				// 상품 검색 부분
			} else if (selNum == 3) {
				System.out.print("검색하고 싶은 상품의 코드를 입력해 주세요:");
				String p_code = sc.nextLine();

				ProductDTO product = pDao.getProductList(p_code);

				// 상품이 존재하는지 확인 후 출력
				if (product != null) {
					System.out.println("[상품 정보]");
					System.out.println("상품 코드: " + product.getP_code());
					System.out.println("상품 이름: " + product.getP_name());
					System.out.println("상품 가격: " + product.getP_price());
					System.out.println("상품 설명: " + product.getP_content());
					System.out.println("상품 이미지: " + product.getP_img());
					System.out.println("상품 사용 여부: " + product.getP_useyn());

				} else {
					System.out.println("해당 상품 코드로 검색된 상품이 없습니다.");
				}

			} else if (selNum == 4) {
				// 상품 수정 부분
				System.out.print("수정하고 싶은 상품 코드를 입력하세요:");
				String p_code = sc.nextLine();
				ProductDTO productToUpdate = pDao.getProductList(p_code);

				// 상품이 존재하는지 확인 후 수정 작업 진행
				if (productToUpdate != null) {
					System.out.println("[상품 수정]");
					System.out.println("[상품 코드]: " + productToUpdate.getP_code());
					System.out.println("[현재 상품 이름]: " + productToUpdate.getP_name());
					System.out.print("새로운 상품 이름을 입력해 주세요:");
					String newP_name = sc.nextLine();
					System.out.print("새로운 상품 가격을 입력해 주세요:");
					int newP_price = sc.nextInt();
					sc.nextLine(); // 버퍼 비우기
					System.out.print("새로운 상품 설명을 입력해 주세요:");
					String newP_content = sc.nextLine();
					System.out.print("새로운 상품 이미지 URL을 입력해 주세요:");
					String newP_img = sc.nextLine();
					System.out.print("새로운 상품의 사용 여부를 입력해 주세요 (Y/N):");
					String newP_useyn = sc.nextLine();

					// 새로운 정보를 ProductDTO 객체에 저장
					ProductDTO updatedProduct = new ProductDTO();
					updatedProduct.setP_code(p_code);
					updatedProduct.setP_name(newP_name);
					updatedProduct.setP_price(newP_price);
					updatedProduct.setP_content(newP_content);
					updatedProduct.setP_img(newP_img);
					updatedProduct.setP_useyn(newP_useyn);

					// 상품 수정 메서드 호출
					int updateResult = pDao.updateProduct(updatedProduct);
					if (updateResult > 0) {
						System.out.println("상품이 성공적으로 수정되었습니다.");
					} else {
						System.out.println("상품 수정에 실패했습니다.");
					}
				} else {
					System.out.println("해당 상품 코드로 검색된 상품이 없습니다.");
				}
			} else if (selNum == 5) {
			    System.out.println("삭제 하고싶은 상품의 코드를 입력하세요:");
			    String p_code = sc.nextLine();
			    
			    // 해당 상품이 존재하는지 확인
			    ProductDTO deleteProduct = pDao.getProductList(p_code);
			    
			    if(deleteProduct != null) {
			        // 상품이 존재하면 삭제
			        int result = pDao.deleteProduct(p_code);
			        if(result > 0) {
			            System.out.println("[상품] "+ p_code + "(이)가 삭제되었습니다.");
			        } else {
			            System.out.println("상품 삭제에 실패했습니다.");
			        }
			    } else {
			        System.out.println("해당 상품 코드로 검색된 상품이 없습니다.");
			    }
			}else if(selNum ==6) {
				System.out.println(" 상품 등록을 종료합니다. ");
				break;
			}
				}
	}
}
