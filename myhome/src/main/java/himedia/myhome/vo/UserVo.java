package himedia.myhome.vo;

import java.util.Date;

public class UserVo {
	private Long no;
	private String name;
	private String password;
	private String email;
	private String gender;
	private Date created_at;
	
	public UserVo() {
		// 기본 생성자 
	}

	public UserVo(Long no, String name, String password, String email, String gender, Date created_at) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.created_at = created_at;
	}
	
	public UserVo(Long no, String name, String password, String email, String gender) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}

	
	public UserVo(String name, String password, String email, String gender) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", password=" + password + ", email=" + email + ", gender="
				+ gender + ", created_at=" + created_at + "]";
	}
	
}
