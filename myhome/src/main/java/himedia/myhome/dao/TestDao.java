package himedia.myhome.dao;

import java.util.Iterator;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class TestDao {
	public static void main(String[] args) {
		//	목록 출력
		list();
		
		//	회원 정보 입력
		insert("홍길동", "1234", "hong@hwalbin.org", "M");
		insert("이영희", "4321", "younghee@lee.name", "F");
		
		//	목록 출력
		list();
	}
	
	private static void insert(String name, String password, String email, String gender) {
		UsersDao dao = new UsersDaoOracleImpl("himedia", "himedia");
		
		UserVo vo = new UserVo(name, password, email, gender);
		
		boolean success = dao.insert(vo);
		
		System.out.println(name + " INSERT " + (success ? "SUCCESS": "FAILED"));
	}

	public static void list() {
		UsersDao dao = new UsersDaoOracleImpl("himedia", "himedia");
		
		List<UserVo> list = dao.getList();
		Iterator<UserVo> it = list.iterator();
		
		System.out.println("====================");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("====================");
	}
	
	
}
