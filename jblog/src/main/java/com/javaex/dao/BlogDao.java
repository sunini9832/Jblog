package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(BlogVo blogVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("BlogXml.insertblog", blogVo);
	}

	public List<PostVo> postList(int cateNo){
		List<PostVo> list = sqlSession.selectList("BlogXml.selectPost", cateNo);
		return list;
	}
	
	public List<CateVo> cateList(String id){
		List<CateVo> list = sqlSession.selectList("CateXml.selectCategory", id);
		return list;
	}
	
	public PostVo selectPost(int postNo) {
		return sqlSession.selectOne("BlogXml.selectPostOne", postNo);
	}

	public BlogVo selectBlogVo(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("BlogXml.selectblog", id);
	}

	public int updateblog(BlogVo blogVo) {
		// TODO Auto-generated method stub
		return sqlSession.update("BlogXml.updateblog", blogVo);
	}
	

}
