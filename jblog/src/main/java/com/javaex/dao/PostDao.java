package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> postList(int cateNo){
		List<PostVo> list = sqlSession.selectList("PostXml.selectPost", cateNo);
		return list;
	}
	
	public PostVo selectPost(int postNo) {
		return sqlSession.selectOne("PostXml.selectPostOne", postNo);
	}
	
	public int insertPost(PostVo postVo) {
		return sqlSession.insert("PostXml.insertPost", postVo);
	}
	
	public int countPost(int cateNo) {
		return sqlSession.selectOne("PostXml.getPostCount", cateNo);
	}
	
	
	
}
