package himedia.myhome.dao;

import java.util.List;

import himedia.myhome.vo.UserVo;

public interface UsersDao {
	public List<UserVo> getList();
	public boolean insert(UserVo userVo);
	public boolean update(UserVo userVo);
	public boolean delete(Long no);
	public UserVo getUserByIDAndPassword(String email
									,String password);
}
