package com.zx.Service;

import java.util.List;

import com.zx.Dao.BannerDaoImp;
import com.zx.Pojo.Banner;

public class BannerService {
	private BannerDaoImp bannerDaoimp;

	public BannerDaoImp getBannerDaoimp() {
		return bannerDaoimp;
	}

	public void setBannerDaoimp(BannerDaoImp bannerDaoimp) {
		this.bannerDaoimp = bannerDaoimp;
	}
	public List<Banner> getAllbanner()
	{
		return bannerDaoimp.getallbanner();
	}
	
	public boolean saveBanner(Banner banner)
	{
		int result=bannerDaoimp.saveBanner(banner);
		if(result>0)
		{
			return true;
		}else
		{
			return false;
		}
		
	}
	
	public boolean deleteBanner(String id)
	{
		int result=bannerDaoimp.deleteBanner(id);
		if(result>0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	public Banner getbannerByid(String id)
	{
		return bannerDaoimp.getbannerByid(id);
	}
}
