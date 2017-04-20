package com.java1234.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.java1234.model.Goods;
import com.java1234.model.PageBean;

public interface GoodsService {

	public List<Goods> selectGoodsNumber();
	public List<Goods> selectMarkByZero();
	public List<Goods> selectMarkByOne();
	public List<Goods> selectGoodsDetail(int id, PageBean pageBean);
	public List<Goods> selectGoodsDetail2(int id, PageBean pageBean);
	public List<Goods> selectGoodsDetail(int id);
	public int selectGoodsDetailCount(int id);
	public List<Goods> selectNewGoods();
	public List<Goods> selectFreeGoods(PageBean pageBean);
	public List<Goods> goodsPage(PageBean pageBean);
	public void deleteGoods(int goodsId);
	public void updateGodos(Goods g);
	public void addGoods(Goods g);
	public String uploadGoodsFile(ServletContext application,HttpServletRequest httpServletRequest,File file,String pictureFileName) throws IOException;
	public int selectGoodsDetailCount2(int id);
}
