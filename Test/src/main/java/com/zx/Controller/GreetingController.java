package com.zx.Controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zx.Pojo.Articel;
import com.zx.Service.ArticelService;

@Controller
public class GreetingController {
	private ArticelService articelService;
//    @RequestMapping("/index")
//    public String index() {
//    	return "index";
//    }
//    
//    @RequestMapping("/main")
//    public String main() {
//    	return "main";
//    }
//	  @RequestMapping("/update")
//	  public String update(@RequestParam(value="id") String id,Model model) {
//		  List<Articel> list=articelService.getArticelContent(id);
//		  model.addAttribute("articel", list.get(0));
//	  	  return "update";
//	  }
	public ArticelService getArticelService() {
		return articelService;
	}
	public void setArticelService(ArticelService articelService) {
		this.articelService = articelService;
	}
	  
}