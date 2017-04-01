package com.zx.Service;

import java.util.List;

import com.zx.Dao.ArticelDaoImp;
import com.zx.Pojo.Articel;
import com.zx.Pojo.ArticelLike;
import com.zx.Pojo.Pageinfo;

public class ArticelService {
	private ArticelDaoImp articelDaoImp;
	public List<Articel> getAllArticel()
	{
		return articelDaoImp.getAllarticel();
	}
	public List<Articel> getArticelBymodel(String model)
	{
		return articelDaoImp.getArticelbyModel(model);
	}
	public int saveArticel(Articel articel)
	{
		return articelDaoImp.saveArticel(articel);
	}
	public int updateArticel(Articel articel)
	{
		return articelDaoImp.updateArticel(articel);
	}
	public List<Articel> getArticelContent(String id)
	{
		return articelDaoImp.getArticelCeontent(id);
	}
	
	public List<Articel> getArticelFilepath(String id)
	{
		return articelDaoImp.getArticelFilepath(id);
	}
	
	/**
	 * 通过添加查询符合的文章数目
	 * @return
	 */
	public Integer getNumOfArticelWithcond(Articel articel)
	{
		return articelDaoImp.getNumOfArticelWithcond(articel);
	}
	
	/**
	 * 通过添加查询符合的文章
	 * @return
	 */
	public List getArticelWithcond(Pageinfo pageinfo)
	{
		return articelDaoImp.getArticelWithcond(pageinfo);
	}
	/**
	 * 通过文章ID删除文章
	 */
	public int deleteArtByid(String id)
	{
		return articelDaoImp.deleteArtByid(id);
	}
	/**
	 * 点赞 点赞数加1
	 * @param articeid
	 */
	public void pointlike(Integer articeid)
	{
		articelDaoImp.pointlike(articeid);
	}
	/**
	 * 点赞 点赞数减1
	 * @param articeid
	 */
	public void dispointlike(Integer articeid)
	{
		articelDaoImp.dispointlike(articeid);
	}
	/**
	 * 保存点赞人
	 * @return
	 */
	public void savearticelLike(ArticelLike articellike)
	{
		articelDaoImp.savearticelLike(articellike);
	}
	/**
	 * 删除点赞人
	 * @return
	 */
	public void deletearticelLike(ArticelLike articellike)
	{
		articelDaoImp.deletearticelLike(articellike);
	}
	
	/**
	 * 判断用户是否点赞
	 * @return
	 */
	public boolean hasPointlike(Integer articelid,Integer userid)
	{
		ArticelLike al=new ArticelLike();
		al.setArticelId(articelid);
		al.setLikeUser(userid);
		List list=articelDaoImp.getArticellike(al);
		if(list.size()>0)
		{
			Integer artlist=(Integer)list.get(0);
			if(artlist>0)
			{
				return true;
			}else
			{
				return false;
			}
		}else
		{
			return false;
		}
	}
	
	public List<ArticelLike> getArticellikebyUser(String userid)
	{
		return articelDaoImp.getArticellikeByuserid(userid);
	}
	public ArticelDaoImp getArticelDaoImp() {
		return articelDaoImp;
	}
	public void setArticelDaoImp(ArticelDaoImp articelDaoImp) {
		this.articelDaoImp = articelDaoImp;
	}
	
	
}
