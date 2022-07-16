package com.javaex.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CmtService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.CmtVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;


@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CmtService cmtService;
	
	
	//블로그 메인창
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String main(@PathVariable("id") String id, Model model, @ModelAttribute PostVo postVo) {
		BlogVo bloginfo = userService.bloginfo(id);
		
		model.addAttribute("id", bloginfo.getId());
		model.addAttribute("blogTitle", bloginfo.getBlogTitle());
		model.addAttribute("logoFile", bloginfo.getLogoFile());
		
		//System.out.println(bloginfo);
		
//		List<CmtVo> cmtList = cmtService.cmtList(postVo.getPostNo());
//		model.addAttribute("cmtList", cmtList);
//		System.out.println(cmtList);
		
		List<CateVo> cateList = blogService.cateList(id);
		model.addAttribute("cateList", cateList);
	
		if(cateList.size() != 0) {
		List<PostVo> postList = blogService.postList(cateList.get(0).getCateNo());
		model.addAttribute("postList", postList);
			if(postList.size() != 0) {
				PostVo post = blogService.selectPost(postList.get(0).getPostNo());
				model.addAttribute("post", post);
			}
		}
		
		
//		List<PostVo> postList = blogService.postList(cateList.get(0).getCateNo());
//		model.addAttribute("postList", postList);
//		PostVo post = blogService.selectPost(postList.get(0).getPostNo());
//		model.addAttribute("post", post);
		
		return "blog/blog-main";
	}
	//기본설정
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.GET)
	public String adminBasicPage(@PathVariable("id") String id, Model model) {
		BlogVo bloginfo = userService.bloginfo(id);
		
		model.addAttribute("id", bloginfo.getId());
		model.addAttribute("blogTitle", bloginfo.getBlogTitle());
		model.addAttribute("logoFile", bloginfo.getLogoFile());
		
		return "blog/admin/blog-admin-basic";
	}
	
	//기본설정변경
	@RequestMapping(value="/{id}/admin/basic/update", method=RequestMethod.POST)
	public String mainupdate(@ModelAttribute BlogVo blogVo, @PathVariable("id") String id, Model model, MultipartFile uploadFile) {
		BlogVo bloginfo = userService.bloginfo(id);
		String uploadFolder = "C:/Users/surinkim/eclipse-workspace/jblog/src/main/webapp/resources/assets/images/";
		MultipartFile multipartFile = uploadFile;
		
		File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		
		try {
			multipartFile.transferTo(saveFile);
			blogVo.setLogoFile(multipartFile.getOriginalFilename());
		} catch (Exception e) {
			//log.error(e.getMessage());
			blogVo.setLogoFile(bloginfo.getLogoFile());
		} // end command객체가 아닌 request로 submit한 값 받아오기
	
		blogService.updateblog(blogVo);
		return "redirect:/{id}";
	}
	
	//카테고리
	@RequestMapping(value="/{id}/admin/category", method=RequestMethod.GET)
	public String adminCatePage(@PathVariable("id") String id, Model model) {
		BlogVo bloginfo = userService.bloginfo(id);
		
		model.addAttribute("id", bloginfo.getId());
		model.addAttribute("blogTitle", bloginfo.getBlogTitle());
		model.addAttribute("logoFile", bloginfo.getLogoFile());
		
		List<CateVo> cateList = blogService.cateList(id);
		
		for(CateVo cateVo: cateList) {
			cateVo.setCount(blogService.countPost(cateVo.getCateNo()));
		}
		
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-cate";
	}
	//글작성
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.GET)
	public String adminWritePage(@PathVariable("id") String id, Model model) {
		BlogVo bloginfo = userService.bloginfo(id);

		model.addAttribute("id", bloginfo.getId());
		model.addAttribute("blogTitle", bloginfo.getBlogTitle());
		model.addAttribute("logoFile", bloginfo.getLogoFile());
		
		List<CateVo> cateList = blogService.cateList(id);
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/{id}/addPost", method=RequestMethod.POST)
	  public String addPost(@PathVariable("id") String id, @ModelAttribute PostVo postVo, CateVo cateVo) {
	    
	    blogService.insertPost(postVo);
	    cateVo.setCateNo(cateVo.getCateNo());
	    
	    
	    return "redirect:/{id}";
	  }
	
	@RequestMapping(value="/post/list/{cateno}", method=RequestMethod.GET)
	@ResponseBody
	public List<PostVo> categoryInfo(@PathVariable("cateno") Integer cateNo, Model model) {
		List<PostVo> postList = blogService.postList(cateNo);
			
		return postList;
	}
	
	@RequestMapping(value="/post/select/{postno}", method=RequestMethod.GET)
	@ResponseBody
	public PostVo postInfo(@PathVariable("postno") Integer postNo, Model model) {
		
		PostVo post = blogService.selectPost(postNo);
		
		return post;
	}
	
	@RequestMapping(value="/removeCate/{cateno}", method=RequestMethod.GET)
	@ResponseBody
	public List<CateVo> removeCate(@PathVariable("cateno") Integer cateNo, Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		blogService.deleteCate(cateNo);
		List<CateVo> cateList = blogService.cateList(authUser.getId());
		
		for(CateVo cateVo: cateList) {
			cateVo.setCount(blogService.countPost(cateVo.getCateNo()));
		}
		
		
		return cateList;
	}
	
	@RequestMapping(value="/{id}/addCate", method=RequestMethod.POST)
	@ResponseBody
	public List<CateVo> addCate( @PathVariable("id") String id, @RequestBody CateVo cateVo, HttpSession session) {	
		cateVo.setId(id);
		blogService.insertCate(cateVo);
		
		List<CateVo> cateList = blogService.cateList(id);
		
		for(CateVo cate: cateList) {
			cate.setCount(blogService.countPost(cate.getCateNo()));
		}
		
		return cateList;
	}
	
	//코멘트
	@RequestMapping(value="/cmt/select/{postno}", method=RequestMethod.GET)
	@ResponseBody
	public List<CmtVo> cmtInfo(@PathVariable("postno") Integer postNo, Model model) {
		
		List<CmtVo> cmtList = cmtService.cmtList(postNo);
		
		return cmtList;
	}
}
