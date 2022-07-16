package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CateVo> cateList(String id){
		List<CateVo> list = sqlSession.selectList("CateXml.selectCategory", id);
		return list;
	}
	
	public int insertCate(CateVo cateVo) {
		return sqlSession.insert("CateXml.insertCategory", cateVo);
	}
	
	public int deleteCate(int cateNo) {
		return sqlSession.delete("CateXml.deleteCategory", cateNo);
	}

}
