package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CateDao cateDao;
	
	@Autowired
	private PostDao postDao;
	
	public int updateblog(BlogVo blogVo) {
		return blogDao.updateblog(blogVo);
		
	}
	
	public List<PostVo> postList(int cateNo) {
		return postDao.postList(cateNo);
	}
	
	public List<CateVo> cateList(String id) {
		return cateDao.cateList(id);
	}
	
	public PostVo selectPost(int postNo) {
		return postDao.selectPost(postNo);
	}
	
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}
	
	public int insertCate(CateVo cateVo) {
		return cateDao.insertCate(cateVo);
	}
	
	public int countPost(int cateNo) {
		return postDao.countPost(cateNo);
	}
	
	public int deleteCate(int cateNo) {
		return cateDao.deleteCate(cateNo);
	}



}
