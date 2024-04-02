package member;

import java.util.List;
import java.util.Scanner;
import java.sql.*;

import dao.MemberDAO;
import dto.MemberDTO;

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
			System.out.println("================================< 회원 정보 메뉴  >===============================");
			System.out.println("\t1. 회원가입 \t\t2. 회원정보 수정\t\t 3. 로그인");
			System.out.println("\t4. 회원탈퇴 \t\t5. 시스템 종료\t\t 6. 회원목록");
			System.out.println("\t7. 회원 검색");

			System.out.print("\n\n메뉴를 선택해 주세요: ");
			int selNum = sc.nextInt();
			sc.nextLine();
			if (selNum == 1) {
				
}
				
					while(true) {
		            System.out.print("\n아이디를 입력해 주세요: ");
		            String id = sc.nextLine(); 
		           
 
		            // 입력값이 비어있는지 확인
		            if (id.isEmpty()) {
		                System.out.println("아이디를 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            // 아이디가 이미 존재하는지 확인
		            if (mDao.isExistingId(id)) {
		                System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }
		            
		            // 아이디 길이 확인
		            MemberDTO comparisonResult = mDao.comparisonMember(id);
		            if (comparisonResult != null) {
		                System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            // 아이디가 이미 존재하는지 확인
		            if (mDao.isExistingId(id)) {
		                System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            System.out.print("패스워드를 입력해 주세요: ");
		            String pw = sc.nextLine();

		            // 입력값이 비어있는지 확인
		            if (pw.isEmpty()) {
		                System.out.println("패스워드를 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            System.out.print("이름을 입력해 주세요: ");
		            String name = sc.nextLine();

		            // 입력값이 비어있는지 확인
		            if (name.isEmpty()) {
		                System.out.println("이름을 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            System.out.print("이메일을 입력해 주세요: ");
		            String email = sc.nextLine();

		            // 입력값이 비어있는지 확인
		            if (email.isEmpty()) {
		                System.out.println("이메일을 입력해주세요.");
		                continue; // 다시 입력 받도록 반복
		            }

		            // 유효성 검사가 모두 통과한 경우에만 나머지 코드가 실행됩니다.

		            // 나머지 회원가입 로직
		            dto.MemberDTO mDto = new dto.MemberDTO(id, pw, name, email);
		            int result = mDao.insertMember(mDto);
		            if (result > 0) {
		                System.out.println("\n\n\n\n회원 등록 성공");
		            } else {
		                System.out.println("회원 등록 실패");
		            }

		            // 회원가입 완료 후 반복 종료
		            break;
		        }
			    
			} else if (selNum == 2) {
				System.out.print("수정할 회원의 아이디를 입력해 주세요: ");
				String id = sc.nextLine();

				System.out.print("현재 비밀번호를 입력해 주세요: ");
				String pw = sc.nextLine();

				// 현재 비밀번호를 이용하여 회원 정보를 조회합니다.
				MemberDTO currentMember = mDao.getMemberById(id);

				if (currentMember == null) {
					System.out.println("해당 아이디의 회원이 존재하지 않습니다.");
				} else {
					// 입력한 비밀번호가 현재 회원의 비밀번호와 일치하는지 확인합니다.
					if (!currentMember.getPw().equals(pw)) {
						System.out.println("비밀번호가 일치하지 않습니다. 수정을 종료합니다.");
					} else {
						// 새로운 정보 입력 받음
						System.out.println("새로운 패스워드를 입력해 주세요: ");
						String newPw = sc.nextLine();

						System.out.println("새로운 이름을 입력해 주세요: ");
						String name = sc.nextLine();

						System.out.println("새로운 이메일을 입력해 주세요: ");
						String email = sc.nextLine();

						// 회원 정보 업데이트
						MemberDTO mDto = new MemberDTO(id, newPw, name, email);
						int result = mDao.updateMember(mDto);

						if (result > 0) {
							System.out.println("회원 정보 수정 성공!!");
						} else {
							System.out.println("회원 정보 수정 실패...");
						}
					}
				}

			} else if (selNum == 3) {
				System.out.print("아이디를 입력해 주세요: ");
				String id = sc.nextLine();

				System.out.print("비밀번호를 입력해 주세요: ");
				String pw = sc.nextLine();

				MemberDTO mDto = new MemberDTO(id, pw);
				int result = mDao.LoginMember(mDto);

				if (result > 0) {
					System.out.println(" 로그인이 성공적으로 이루어졌습니다. ");
				} else {
					System.out.println(" 존재하지 않는 비밀번호 입니다. ");
				}
			} else if (selNum == 4) {
				System.out.print(" 아이디를 입력해 주세요:");
				String id = sc.nextLine();

				System.out.print(" 비밀번호를 입력해 주세요:");
				String pw = sc.nextLine();

				MemberDTO mDto = new MemberDTO(id, pw);
				int result = mDao.deleteMember(mDto);

				if (result > 0) {
					System.out.println("<======== 회원탈퇴가 완료 되었습니다. 다신보지 말아요 =========>");
				} else {
					System.out.println(" <============유효하지 않는 회원 정보 입니다.===============>");
				}

			} else if (selNum == 5) {
				System.out.println(" \n\n\n\n\n\n\n\n\n\n\n=<시스템이 종료 되었습니다.>= ");
				break;
			} else if (selNum == 6) {// 회원 목록 조회
				List<MemberDTO> members = mDao.listMembers();

				// 회원 목록 출력
				System.out.println("============== <회원 목록> ===============");
				
				for (MemberDTO member : members) {
					System.out.println("[아이디]: " + member.getId() + ", [비밀번호]: " + member.getPw() + ", [이름]: "
							+ member.getName() + ", [이메일]: " + member.getEmail());
					
				}System.out.println("========================================\n\n\n\n");
				

			} else if (selNum == 7) {
				
				//회원 이름으로 검색 
				System.out.print(" 조회하고 싶은 회원의 아이디를 입력하세요:  ");
				String id = sc.nextLine();

				// 회원 검색 메서드 호출
				MemberDTO mDto = new MemberDTO(id);
				MemberDTO foundMember = mDao.getMemberById(mDto.getId());

				if (foundMember != null) {
					// 회원이 존재할 때의 동작 추가
					System.out.println(" 등록되어있는 회원 아이디입니다.");
					// 회원 정보 출력 등 추가적인 동작 수행
				} else {
					// 회원이 존재하지 않을 때의 동작 추가
					System.out.println("존재하지 않는 회원 아이디 입니다. ");
				}
			}
		}
	}
}
