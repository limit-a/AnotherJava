package jdbc_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Board {

	// JAVA_BOARD 테이블을 만들고 JDBC를 이용해
	// 조회, 등록, 수정, 삭제가 가능한 게시판을 만들어주세요

	// 번호(PK), 제목, 내용, 작성자, 작성일시

	// 예시)
	// --------------------------------------------
	// 번호 제목 작성자 작성일시
	// --------------------------------------------
	// 1 안녕하세요 홍길동 08-01 15:26
	// 2 반갑습니다 이순신 08-02 20:20
	// 3 가입인사 아이유 08-03 13:31
	// --------------------------------------------
	// 1. 조회 2. 등록 3. 삭제 4. 종료
	// 원하는 메뉴 입력 >>>

	final String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	final String dbUser = "jjh0626";
	final String dbPassword = "java"; // 비밀번호는 String

	final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");

	// 접속 정보는 sqldeveloper에서 좌측에 새 접속을 눌러
	// 계정 정보를 확인할 수 있다

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	String userId = null;
	String userPassword = null;
	String userName = null;

	void disconnect() {
		// 5. ResultSet, Statement, Connection 닫기
		// 반납은 실행 순서의 역순으로 한다
		// rs -> ps -> conn
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	void readBoardList() {
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " SELECT *                           "
					+ "      FROM JAVA_BOARD                  "
					+ "     ORDER BY BOARD_NUM ASC            ";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			System.out.println("--------------------------------------------");
			System.out.printf(" %2s  %4s     %4s      %4s\n", "번호", "제목", "작성자",
					"작성일시");
			System.out.println("--------------------------------------------");

			while (rs.next()) {

				System.out.printf(" %2s    %4s    %4s    %8s",
						rs.getObject("BOARD_NUM"), rs.getObject("BOARD_TITLE"),
						rs.getObject("BOARD_WRITER"),
						sdf.format(rs.getObject("BOARD_DATE")));
				System.out.println();
			}
			System.out.println("--------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	void readBoard() {

		System.out.print("조회할 게시판 번호 선택 >>> ");
		int input = JavaBoardUtil.scanInt();

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " SELECT * FROM JAVA_BOARD "
					+ "     WHERE BOARD_NUM= ? ";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, input);

			rs = ps.executeQuery();

			JavaBoardUtil.clearScreen();
			boolean check = rs.next();
			if (!check) {
				System.out.println("게시판이 존재하지 않습니다");
			} else {
				System.out.printf(" %s    %s    %s",
						rs.getObject("BOARD_TITLE"),
						rs.getObject("BOARD_WRITER"),
						sdf.format(rs.getObject("BOARD_DATE")));
				System.out.println();
				System.out.println(" " + rs.getObject("BOARD_CONTENT"));
			}

//			while (rs.next()) {
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	void insertBoard() {

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			System.out.print("제목 입력 >>> ");
			String boardTitle = JavaBoardUtil.scanLine();
			System.out.print("작성자 입력 >>> ");
			String boardWriter = JavaBoardUtil.scanLine();
			System.out.print("내용 입력 >>> ");
			String boardContent = JavaBoardUtil.scanLine();
			System.out.print("비밀번호 입력 >>> ");
			String boardPassword = JavaBoardUtil.scanLine();

			String sql = " INSERT INTO JAVA_BOARD(                             "
					+ "        BOARD_NUM,                                      "
					+ "        BOARD_TITLE,                                    "
					+ "        BOARD_WRITER,                                   "
					+ "        BOARD_CONTENT,                                  "
					+ "        BOARD_DATE,                                     "
					+ "        BOARD_PASSWORD                                  "
					+ "    )                                                   "
					+ "                                                        "
					+ "    VALUES(                                             "
					+ "        SEQ_JAVA_BOARD.NEXTVAL, ? , ? , ? , SYSDATE, ?  "
					+ "    ) ";

			ps = conn.prepareStatement(sql);

			ps.setString(1, boardTitle);
			ps.setString(2, boardWriter);
			ps.setString(3, boardContent);
			ps.setString(4, boardPassword);

			int result = ps.executeUpdate();

			if (result <= 0) {
				System.out.println("실패");
			} else {
				System.out.println(result + "개 수행");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	void updateBoard() {

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " SELECT * FROM JAVA_BOARD             "
					+ "     WHERE BOARD_NUM= ? ";

			ps = conn.prepareStatement(sql);

			System.out.print("수정할 게시판 번호 선택 >>> ");
			String boardNum = JavaBoardUtil.scanLine();
			ps.setString(1, boardNum);

			rs = ps.executeQuery();

			JavaBoardUtil.clearScreen();
			boolean check = rs.next();
			if (!check) {
				System.out.println("잘못 입력했습니다");
			} else {
				System.out.print("비밀번호 확인 >>> ");
				String checkPass = JavaBoardUtil.scanLine();

				if (!(rs.getObject("BOARD_PASSWORD").equals(checkPass))) {
					System.out.println("비밀번호가 틀렸습니다");
				} else {

					System.out
							.println("현재 제목 : " + rs.getObject("BOARD_TITLE"));
					System.out.print("제목 수정 >>> ");
					String boardTitle = JavaBoardUtil.scanLine();
					System.out.println(
							"현재 작성자 : " + rs.getObject("BOARD_WRITER"));
					System.out.print("작성자 수정 >>> ");
					String boardWriter = JavaBoardUtil.scanLine();
					System.out.println(
							"현재 내용 : " + rs.getObject("BOARD_CONTENT"));
					System.out.print("내용 수정 >>> ");
					String boardContent = JavaBoardUtil.scanLine();
					System.out.print("비밀번호 수정 >>> ");
					String boardPassword = JavaBoardUtil.scanLine();

					sql = "     UPDATE JAVA_BOARD                   "
							+ "    SET BOARD_TITLE = ? ,            "
							+ "        BOARD_WRITER = ? ,           "
							+ "        BOARD_CONTENT = ? ,          "
							+ "        BOARD_DATE = SYSDATE,        "
							+ "        BOARD_PASSWORD = ?           "
							+ "  WHERE BOARD_NUM = ? ";

					ps = conn.prepareStatement(sql);

					ps.setString(1, boardTitle);
					ps.setString(2, boardWriter);
					ps.setString(3, boardContent);
					ps.setString(4, boardPassword);
					ps.setString(5, boardNum);

					int result = ps.executeUpdate();

					if (result <= 0) {
						System.out.println("실패");
					} else {
						System.out.println(result + "개 수행");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	void deleteBoard() {
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " SELECT * FROM JAVA_BOARD "
					+ "     WHERE BOARD_NUM= ? ";

			ps = conn.prepareStatement(sql);

			System.out.print("삭제할 게시판 번호 선택 >>> ");
			String boardNum = JavaBoardUtil.scanLine();
			ps.setString(1, boardNum);

			rs = ps.executeQuery();

			JavaBoardUtil.clearScreen();
			boolean check = rs.next();
			if (!check) {
				System.out.println("잘못 입력했습니다");
			} else {
				System.out.print("비밀번호 확인 >>> ");
				String checkPass = JavaBoardUtil.scanLine();

				if (!(rs.getObject("BOARD_PASSWORD").equals(checkPass))) {
					System.out.println("비밀번호가 틀렸습니다");
				} else {

					sql = "    DELETE FROM JAVA_BOARD           "
							+ " WHERE BOARD_NUM = ?             ";

					ps = conn.prepareStatement(sql);

					ps.setString(1, boardNum);

					int result = ps.executeUpdate();

					if (result <= 0) {
						System.out.println("실패");
					} else {
						System.out.println(result + "개 수행");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	void menu() {
		while (true) {

			readBoardList();
			System.out.println("1. 조회    2. 등록    3. 수정    4. 삭제    0. 로그아웃");
			System.out.print("메뉴 선택 >>> ");
			int input = JavaBoardUtil.scanInt();
			switch (input) {
			case 1:
				readBoard();
				JavaBoardUtil.timeSleep(3);
				JavaBoardUtil.clearScreen();
				break;
			case 2:
				JavaBoardUtil.clearScreen();
				insertBoard();
				JavaBoardUtil.timeSleep(1);
				break;
			case 3:
				updateBoard();
				break;
			case 4:
				deleteBoard();
				break;
			case 0:
				System.out.println("종료");
				return;

			default:
				break;
			}
		}
	}

	void join() {

		System.out.print("회원가입할 아이디 >>> ");
		userId = JavaBoardUtil.scanLine();
		System.out.print("회원가입할 비밀번호 >>> ");
		userPassword = JavaBoardUtil.scanLine();
		System.out.print("회원가입할 이름 >>> ");
		userName = JavaBoardUtil.scanLine();

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " INSERT INTO JAVA_MEMBER(              "
					+ "        MEMBER_ID,                        "
					+ "        MEMBER_PASSWORD,                  "
					+ "        MEMBER_NAME                       "
					+ "    )                                     "
					+ "                                          "
					+ "    VALUES(                               "
					+ "        ? , ? , ?                         "
					+ "    )                                     ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userPassword);
			ps.setString(3, userName);

			int result = ps.executeUpdate();

			if (result <= 0) {
				System.out.println("회원가입 실패");
			} else {
				System.out.println("회원가입 성공");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	void login() {

		System.out.print("로그인 아이디 >>> ");
		userId = JavaBoardUtil.scanLine();
		System.out.print("로그인 비밀번호 >>> ");
		userPassword = JavaBoardUtil.scanLine();

		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			String sql = " SELECT *                           "
					+ "      FROM JAVA_BOARD                  "
					+ "     ORDER BY BOARD_NUM ASC            ";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			System.out.println("--------------------------------------------");
			System.out.printf(" %2s  %4s     %4s      %4s\n", "번호", "제목", "작성자",
					"작성일시");
			System.out.println("--------------------------------------------");

			while (rs.next()) {

				System.out.printf(" %2s    %4s    %4s    %8s",
						rs.getObject("BOARD_NUM"), rs.getObject("BOARD_TITLE"),
						rs.getObject("BOARD_WRITER"),
						sdf.format(rs.getObject("BOARD_DATE")));
				System.out.println();
			}
			System.out.println("--------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	void start() {
		while (true) {

			System.out.println("1. 회원가입    2. 로그인    0. 종료");
			System.out.print("선택 >>> ");
			int input = JavaBoardUtil.scanInt();
			switch (input) {
			case 1:
				join();
				break;
			case 2:
				login();
				break;
			case 0:
				System.out.println("종료");
				return;

			default:
				break;
			}
		}
	}

	public static void main(String[] args) {

		new Board().start();

	}
}
