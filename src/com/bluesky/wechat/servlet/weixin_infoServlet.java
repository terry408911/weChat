package com.bluesky.wechat.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluesky.wechat.bean.WeChat;
import com.bluesky.wechat.dao.ConstructionSiteDao;
import com.bluesky.wechat.dao.WeChatDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;




/**
 * Servlet implementation class weixin_infoServlet
 */
@WebServlet("/jsp/weixin_infoServlet")
public class weixin_infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weixin_infoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//========================upload===============================================
		String requestip=request.getRemoteAddr();
		System.out.println("requestip"+requestip);
		String saveDirectory="D:/upload";
		File savedir=new File(saveDirectory);
		if(!savedir.exists()){//如果上传目录不存在则创建它
			savedir.mkdirs();
		}
		
		
		int maxPostSize = 3 * 5 * 1024 * 1024 ;  //上传大小限制：5M
		FileRenamePolicy policy =(FileRenamePolicy)new DefaultFileRenamePolicy(); 
	    MultipartRequest multi;
		multi = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8",policy);
		Enumeration<String> files = multi.getFileNames();	
		while(files.hasMoreElements()){
		String name=files.nextElement();
		File f = multi.getFile(name);
		System.out.println(f.getPath()+" "+f.getName());
		if(f!=null){
		   String fileName = f.getName();
		   System.out.println(saveDirectory);
		   
		   String newFileName=String.valueOf(System.currentTimeMillis());
		   newFileName+=(new Random()).nextInt(20)+fileName.substring(fileName.lastIndexOf("."));
	        File sServerFile= new File(saveDirectory+"\\"+newFileName);
	        System.out.println(saveDirectory+"\\" +newFileName);
	        if(sServerFile.exists()){//将先前上传的文件删除掉，这样重命名才能成功
	   	       sServerFile.delete();
	        }        
	               
		   f.renameTo(sServerFile);//重命名文件
		}
		}
		//-------------------------------------------------------------------------------------
		
		String weChatNo="weChatNo"; //weChatNo is the custom's wechat account,it's need to be replaced 
		WeChat weChat=new WeChat();
		WeChatDao weChatDao=new WeChatDao();
		ConstructionSiteDao constructionIdDao=new ConstructionSiteDao();
		String constructionId=request.getParameter("select_constructionId");
		weChat.setConstructionId(constructionIdDao.queryIdByName(constructionId));
		System.out.println(constructionId);
		weChat.setContent(request.getParameter("content"));
		weChat.setDistrict(request.getParameter("select_district"));
		weChat.setId(request.getParameter("id"));
		weChat.setStreet(request.getParameter("select_street"));
		weChat.setWeChatNo(weChatNo);
		weChat.setHasVideo(false);
		if(files.hasMoreElements()){
			weChat.setHasImg(true);
		}else {
			weChat.setHasImg(false);
		}
		weChatDao.addManager(weChat);
		request.getRequestDispatcher("/jsp/weixin_initServlet").forward(request, response);
	}

}
