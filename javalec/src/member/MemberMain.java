package member;

import java.util.Scanner;
import java.sql.*;

import dao.MemberDAO;

public class MemberMain {

	// 회원 관련 메뉴
	// scott, tiger DB 계정 생성
	// member 테이블 생성(id, pw, name, email)
	// 1. 회원가입 2. 회원정보 수정 3. 로그인 4. 회원탈퇴 5. 시스템 종료
	// 6. 회원목록 7. 회원 검색
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemberDAO mDao = new MemberDAO();
		
		while (true) {
			System.out.println("============< 메뉴 >============");
			System.out.println("1. 회원가입 \t2. 회원정보 수정\t 3. 로그인");
			System.out.println("4. 회원탈퇴 \t5. 시스템 종료\t 6. 회원목록");
			System.out.println("7. 회원 검색");
			
			System.out.println("메뉴를 선택해 주세요: ");
			int selNum = sc.nextInt();
			sc.nextLine();
			if (selNum == 1) {
				System.out.println("id를 입력해 주세요");
				String id = sc.nextLine();
				
				System.out.println("pw를 입력해 주세요");
				String pw = sc.nextLine();
				
				System.out.println("name을 입력해 주세요");
				String name = sc.nextLine();
				
				System.out.println("email을 입력해 주세요");
				String email = sc.nextLine();
				
				dto.MemberDTO mDto = new dto.MemberDTO(id, pw, name, email);
				int result = mDao.insertMember(mDto);
				if (result > 0) {
					System.out.println("회원등록 성공");
				} else {
					System.out.println("회원등록 실패");
				}
			} else if (selNum == 2) {
				System.out.println("메뉴 2번이 선택되었습니다.");
			} else if (selNum == 3) {
				System.out.println("메뉴 3번이 선택되었습니다.");
			} else if (selNum == 4) {
				System.out.println("메뉴 4번이 선택되었습니다.");
			} else if (selNum == 5) {
				System.exit(0);
				System.out.println("Good Bye!!!!");
			} else if (selNum == 6) {
				System.out.println("메뉴 5번이 선택되었습니다.");
			} else if (selNum == 7) {
				System.out.println("메뉴 6번이 선택되었습니다.");
			}
		}
	}
}









