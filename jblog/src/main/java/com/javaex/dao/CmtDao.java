package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CmtVo;

@Repository
public class CmtDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CmtVo> cmtList(int userNo){
		List<CmtVo> list = sqlSession.selectList("CmtXml.selectCmt", userNo);
		return list;
	}

	public int insertCmt(CmtVo cmtVo) {
		return sqlSession.insert("CmtXml.insertCmt", cmtVo);
	}

	public CmtVo cmtSelect(int postNo) {
		return sqlSession.selectOne("CmtXml.cmtSelectone", postNo);
	}

}
