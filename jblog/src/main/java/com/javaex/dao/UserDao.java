package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao{
	
	@Autowired
	private SqlSession sqlSession;

	public UserVo selectUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UsersXml.selectuser", userVo);
	}

	public int insertUserVo(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("UsersXml.insertuser",userVo);
	}

	public UserVo selectUserVo(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UsersXml.idcheck",id);
	}

}
