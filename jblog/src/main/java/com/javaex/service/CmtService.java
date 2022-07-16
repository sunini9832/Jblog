package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CmtDao;
import com.javaex.vo.CmtVo;
import com.javaex.vo.PostVo;

@Service
public class CmtService {
	
	@Autowired
	private CmtDao cmtDao;
	

	public List<CmtVo> cmtList(int postNo) {
		return cmtDao.cmtList(postNo);
	}
	
	public CmtVo cmtSelect(int postNo) {
		return cmtDao.cmtSelect(postNo);
	}
	
	
	public int insertPost(CmtVo cmtVo) {
		return cmtDao.insertCmt(cmtVo);
	}


}
