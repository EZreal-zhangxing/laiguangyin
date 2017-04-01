package com.zx.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.zx.Pojo.Articel;
import com.zx.Pojo.ArticelLike;
import com.zx.Pojo.Message;
import com.zx.Pojo.Model;
import com.zx.Pojo.Pageinfo;
import com.zx.Service.ArticelService;
import com.zx.Service.EncoderHandler;
import com.zx.Service.ModelService;

@RestController
public class ShowArticelController {
    private ArticelService articelService;
    private ModelService modelService;
	@RequestMapping("/getAllArticel")
	public List<Articel> getArtical()
	{
		List<Articel> list=articelService.getAllArticel();
		return list;
	}
	@RequestMapping("/deleteArt")
	public Message deleteArt(@RequestParam(value="articelid") String articelid){
		System.out.println(articelid);
		int result=articelService.deleteArtByid(articelid);
		if(result>0)
		{
			return new Message("success", "删除成功", new Date());
		}else{
			return new Message("fail", "删除失败", new Date());
		}
	}
	/**
	 * 通过页面获取文章
	 * @return
	 */
	@RequestMapping("/getArticelByPage")
	public Pageinfo getArticelByPage(@RequestParam(value="title") String title,@RequestParam(value="modelId") String modelId,@RequestParam(value="page",defaultValue="1") String page)
	{
		Articel at=new Articel();
		if(!"".equals(title))
		{
			at.setTitle(title);
		}
		if(!"".equals(modelId))
		{
			if(modelService.ischildNode(modelId))
			{
				List<Integer> modellist=new ArrayList<Integer>();
				getModels(modelId, modellist);
				int[] models=new int[modellist.size()];
				for (int i = 0; i < models.length; i++) {
					models[i]=modellist.get(i);
				}
				at.setModelids(models);
			}else
			{
				at.setModelId(Integer.parseInt(modelId));
			}
		}
		Integer size=articelService.getNumOfArticelWithcond(at);
		Pageinfo pi=initpage(size, page);
		if(Integer.parseInt(page) >pi.getLastpage())
		{
			pi.setPagenum(pi.getLastpage());
		}
		pi.setCondarticel(at);
		pi.setBegin((pi.getPagenum()-1)*pi.getShownum());
		pi.setEnd(pi.getBegin()+pi.getShownum());
		List list=articelService.getArticelWithcond(pi);
		pi.setArtList(list);
		return pi;
	}
	
	/**
	 * 根据模块查询文章 标题文章展示图
	 * @param model
	 * @return
	 */
	@RequestMapping("/getArticelsBymodel/{model}/{userid}")
	public List<Articel> getArticelsBymodel(@PathVariable(value="model") String model,@PathVariable(value="userid") String userid)
	{
		List<Articel> list=articelService.getArticelBymodel(model);
		
		List<ArticelLike> likelist=articelService.getArticellikebyUser(userid);
		for (int i = 0; i < list.size(); i++) {
			Articel all=list.get(i);
			all.setHaslike(0);
			if(likelist.size()>0)
			{
				for (int j = 0; j < likelist.size(); j++) {
					ArticelLike al=likelist.get(j);
					if(all.getId() == al.getArticelId())
					{
						all.setHaslike(1);
					}
				}
			}
		}
		return list;
	}
	/**
	 * 根据文章ID查询文章内容
	 * @return
	 */
	@RequestMapping("/getArticelContent/{Article}")
	public List<Articel> getArticelContent(@PathVariable(value="Article") String Article)
	{
		List<Articel> list=articelService.getArticelContent(Article);
		return list;
	}
	@RequestMapping("/pointLike")
	public Message pointLike(@RequestParam(value="articelId") Integer articelId,@RequestParam(value="userId") Integer userId)
	{
		if(userId != null && !"".equals(userId))
		{
			ArticelLike al=new ArticelLike();
			al.setArticelId(articelId);
			al.setLikeUser(userId);
			al.setLikeDate(new Date());
			//判断用户是否已经点赞
			boolean result=articelService.hasPointlike(articelId, userId);
			if(result)
			{
				articelService.deletearticelLike(al);
				articelService.dispointlike(articelId);
				return new Message("dissuccess", "取消赞成功！", new Date());
			}else
			{
				articelService.savearticelLike(al);
				articelService.pointlike(articelId);
				return new Message("success", "点赞成功", new Date());
			}
		}else
		{
			return new Message("fail", "请先登陆！", new Date()); 
		}
	}
	//查询挂载文章模块
	@RequestMapping("/getArtModel/{modelid}")
	public List<Model> getArtModel(@PathVariable(value="modelid") String modelid)
	{
		List<Integer> list=new ArrayList<Integer>();
		getModels(modelid, list);
		return modelService.getArtModel(list);
	}
	//查询挂载文章模块
	@RequestMapping("/getAllArtModel")
	public List<Model> getAllArtModel()
	{
		List<Model> resultlist=new ArrayList<Model>();
		List<Model> model_31=getArtModel("31");
		List<Model> model_32=getArtModel("32");
		List<Model> model_33=getArtModel("33");
		resultlist.addAll(model_31);
		resultlist.addAll(model_32);
		resultlist.addAll(model_33);
		return resultlist;
	}
	//查询模块
	@RequestMapping("/getOneLevelModel")
	public List<Model> getOneLevelModel()
	{
		return modelService.getOneLevelModel();
	}
	//查询模块
	@RequestMapping("/getTwoLevelModel/{modelid}")
	public List<Model> getTwoLevelModel(@PathVariable(value="modelid") String modelid)
	{
		return modelService.getOthLevelModel(modelid);
	}
	/**
	 * 检查模块是否是最后一级模块
	 * @param modelid
	 * @return
	 */
	@RequestMapping("/checkModel/{modelid}")
	public Message checkModel(@PathVariable(value="modelid") String modelid)
	{
		List<Model> list=modelService.getOthLevelModel(modelid);
		if(list.size()==0)
		{
			return new Message("success", "校验通过", new Date());
		}else
		{
			return new Message("fail", "请选择下一级目录！", new Date());
		}
	}
	//文章保存
	@RequestMapping("/saveArticel")
	public void saveArticel(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "filepath")  MultipartFile file) throws IOException
	{
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
//		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		PrintWriter bw=response.getWriter();
		String title=request.getParameter("title");
		String model=request.getParameter("model");
		String arttext=request.getParameter("arttext");
		String outUrl=request.getParameter("outUrl");
		String articelDesc=request.getParameter("articelDesc");
		String fileName=file.getOriginalFilename();
		EncoderHandler eh=new EncoderHandler();
		Articel articel=new Articel();
		articel.setTitle(title);
		articel.setContent(arttext);
		articel.setModelId(Integer.parseInt(model));
		articel.setOutUrl(outUrl);
		articel.setArticelDesc(articelDesc);
		//判断模块是否可预约
		boolean modelresult=modelService.modelisorder(model);
		if(modelresult)
		{
			articel.setIsOrder(1);
			String MaxMannum=request.getParameter("MaxMannum");
			articel.setMaxMannum(Integer.parseInt(MaxMannum));
		}else
		{
			articel.setIsOrder(0);
		}
		articel.setDate(new Date());
		
		String file_path=getfilepath();
		File savefile=new File(file_path);
		if(!savefile.exists())
		{
			savefile.mkdirs();
		}
		if(!"".equals(fileName))
		{
			String hzname=fileName.substring(fileName.indexOf("."), fileName.length());
			String md5FileName=eh.encodeByMD5(fileName+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); //文件名 加上 时间戳
			articel.setArticelPicName(md5FileName+hzname);
			String save_file_path=file_path+"/"+md5FileName+hzname;
			File save_file_obje=new File(save_file_path);
			try {
				saveFile(save_file_obje,file.getInputStream());
			} catch (Exception e) {
//				return new Message("fail", "保存失败", new Date());
			}
			articel.setArticelPicPath(save_file_path);
		}		
		int result=articelService.saveArticel(articel);
		String html="<!DOCTYPE html><html lang='en'><head>" +
		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head>" +
		"<title>保存结果</title>" +
		"<body onload=jump()>";
		if(result>0)
		{
			html+="保存成功！";
//			return new Message("success", "保存成功", new Date());
		}else
		{
			html+="保存失败！";
//			return new Message("fail", "保存失败", new Date());
		}
		html+="<a href='edit.html'>返回</a> <b id='sec'></b>秒后跳转！";
		html+="</body><script>var i;var int;function jump(){i=3;" +
		"document.getElementById('sec').innerHTML=i;" +
		"int=window.setInterval('clock(i)',1000)} function clock(){i=i-1;if(i<0){window.clearInterval(int);window.location.href='edit.html';}else{document.getElementById('sec').innerHTML=i;}}</script></html>";
		bw.write(html);
		bw.flush();
		bw.close();
	}
	
	@RequestMapping("/updateArticel")
	public void updateArticel(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "filepath")  MultipartFile file) throws IOException
	{
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
//		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		PrintWriter bw=response.getWriter();
		String articelid=request.getParameter("articelId");
		String title=request.getParameter("title");
		String model=request.getParameter("model");
		String arttext=request.getParameter("arttext");
		String outUrl=request.getParameter("outUrl");
		String articelDesc=request.getParameter("articelDesc");
		String fileName=file.getOriginalFilename();
		EncoderHandler eh=new EncoderHandler();
		Articel articel=new Articel();
		articel.setId(Integer.parseInt(articelid));
		articel.setTitle(title);
		articel.setContent(arttext);
		articel.setModelId(Integer.parseInt(model));
		articel.setOutUrl(outUrl);
		articel.setArticelDesc(articelDesc);
		//判断模块是否可预约
		boolean modelresult=modelService.modelisorder(model);
		if(modelresult)
		{
			articel.setIsOrder(1);
			String MaxMannum=request.getParameter("MaxMannum");
			articel.setMaxMannum(Integer.parseInt(MaxMannum));
		}else
		{
			articel.setIsOrder(0);
		}
		articel.setDate(new Date());
		
		String file_path=getfilepath();
		File savefile=new File(file_path);
		if(!savefile.exists())
		{
			savefile.mkdirs();
		}
		if(!"".equals(fileName))
		{
			String hzname=fileName.substring(fileName.indexOf("."), fileName.length());
			String md5FileName=eh.encodeByMD5(fileName+new Date().toLocaleString());
			articel.setArticelPicName(md5FileName+hzname);
			String save_file_path=file_path+"/"+md5FileName+hzname;
			File save_file_obje=new File(save_file_path);
			try {
				//保存新文件 删除旧文件
				saveFile(save_file_obje,file.getInputStream());
				deleteOldFile(articelid);
			} catch (Exception e) {
//				return new Message("fail", "保存失败", new Date());
			}
			articel.setArticelPicPath(save_file_path);
		}		
		int result=articelService.updateArticel(articel);
		String html="<!DOCTYPE html><html lang='en'><head>" +
		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head>" +
		"<title>保存结果</title>" +
		"<body onload=jump()>";
		if(result>0)
		{
			html+="保存成功！";
//			return new Message("success", "保存成功", new Date());
		}else
		{
			html+="保存失败！";
//			return new Message("fail", "保存失败", new Date());
		}
		html+="<a href='update.html'>返回</a> <b id='sec'></b>秒后跳转！";
		html+="</body><script>var i;var int;function jump(){i=3;" +
		"document.getElementById('sec').innerHTML=i;" +
		"int=window.setInterval('clock(i)',1000)} function clock(){i=i-1;if(i<0){window.clearInterval(int);window.location.href='update.html';}else{document.getElementById('sec').innerHTML=i;}}</script></html>";
		bw.write(html);
		bw.flush();
		bw.close();
	}
	public String getfilepath() throws IOException
	{
		InputStream inStream=ShowArticelController.class.getClassLoader().getResourceAsStream("file.properties");
		Properties pro=new Properties();
		pro.load(inStream);
		String sysStr=System.getProperties().getProperty("os.name");
		if(sysStr.indexOf("Windows") != -1)
		{
			System.out.println(pro.getProperty("windows"));
			return pro.getProperty("windows");
		}else
		{
			System.out.println(pro.getProperty("linux"));
			return pro.getProperty("linux"); 
		}
	}
	public void saveFile(File file,InputStream is) throws IOException
	{
		FileOutputStream fos=new FileOutputStream(file);
		byte[] bytes=new byte[1024*1024];
		int len=0;
		while((len=is.read(bytes))!=-1)
		{
			fos.write(bytes, 0, len);
		}
		fos.flush();
		fos.close();
		is.close();
	}
	public void deleteOldFile(String id)
	{
		List<Articel> list=articelService.getArticelFilepath(id);
		Articel ac=list.get(0);
		File f=new File(ac.getArticelPicPath());
		f.delete();
	}
	
	//通过流读取文件
	@RequestMapping("/readpicFile")
	public void readpicFile(HttpServletResponse response,@RequestParam(value = "filepath")  String filepath) throws IOException
	{
		OutputStream os=null;
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(new File(filepath));
			os=response.getOutputStream();
			byte[] b=new byte[1024*1024];
			int len;
			while((len=fis.read(b))!=-1)
			{
				os.write(b,0,len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(os!=null)
			{
				os.flush();
				os.close();
			}
			if(fis!=null)
			{
				fis.close();
			}
		}
	}
	
	public ArticelService getArticelService() {
		return articelService;
	}
	public void setArticelService(ArticelService articelService) {
		this.articelService = articelService;
	}
	public ModelService getModelService() {
		return modelService;
	}
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	
	//页面初始化
	public Pageinfo initpage(Integer datanum,String page)
	{
		Pageinfo pageif=new Pageinfo();
		int pageshownum=10;//每页展示数
		pageif.setShownum(pageshownum);
		pageif.setFirstpage(1);
		Integer allpage=(datanum>0)?(((datanum)%pageshownum==0)?(datanum/pageshownum):(datanum/pageshownum)+1):1;
		pageif.setAllpage(allpage);
		pageif.setLastpage(allpage);
		Integer pagenum=0;
		
		if(page==null)
		{
			pageif.setPagenum(1);
		}else
		{
			pagenum=Integer.parseInt(page);
			pageif.setPagenum(pagenum);
		}
		pageif.setPrev((pageif.getPagenum()-1)>0?pageif.getPagenum()-1:1);
		pageif.setNext((pageif.getPagenum()+1)<allpage?pageif.getPagenum()+1:allpage);
		String pageStr="";
		if(pagenum-3<=1||allpage<=7)
		{
			for (int i = 1; i <= ((allpage>7)?7:allpage); i++) {
				if(i==((allpage>7)?7:allpage))
				{
					pageStr+=i;
				}else
				{
					pageStr+=i+",";
				}
			}
			if(allpage>7)
			{
				pageStr+=",...";
			}
		}else
		{
			pageStr+="...,";
			int num=((((pagenum+3>allpage)?allpage:pagenum)>3)?3:((pagenum+3>allpage)?allpage:pagenum));
			int cnum=(num+pagenum)>allpage?(allpage-pagenum):num;
			for (int i = cnum-7; i <=cnum; i++) {
				if(i==cnum)
				{
					if(cnum+pagenum==allpage)
					{
						pageStr+=(pagenum+i);
					}else
					{
						pageStr+=(pagenum+i)+",...";
					}
				}else
				{
					pageStr+=(pagenum+i)+",";
				}
			}
		}
		pageif.setPageStr(pageStr.split(","));
		return pageif;
	}
	
	public void getModels(String modelid,List<Integer> result)
	{
		List<Integer> list=modelService.getchildModel(modelid);
		for (int i = 0; i < list.size(); i++) {
			Integer childid=(Integer) list.get(i);
			if(modelService.ischildNode(childid+""))
			{
				getModels(childid+"",result);
			}else
			{
				result.add(childid);
			}
		}
	}
}
