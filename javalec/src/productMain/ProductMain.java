package productMain;

import java.util.Scanner;

import dao.ProductDAO;
import dto.ProductDTO;

import java.util.Scanner;

public class ProductMain {
   

    private static final String p_content = null;

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
                                System.out.println("상품이 등록되었습니다.");
                            } else {
                                System.out.println("상품 등록에 실패했습니다.");
                            }
                            
                        } else if (selNum == 6) {
                            System.out.println("프로그램을 종료합니다.");
                            break;
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                        }
                    }
                    sc.close();
                }
            }



