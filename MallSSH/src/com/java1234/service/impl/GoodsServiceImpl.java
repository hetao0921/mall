package com.java1234.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.java1234.dao.GoodsDao;
import com.java1234.model.Goods;
import com.java1234.model.PageBean;
import com.java1234.service.GoodsService;
import com.java1234.util.DateUtil;

@Component
public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao;

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Resource
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public List<Goods> selectGoodsNumber() {
		return goodsDao.selectGoodsNumber();
	}

	public List<Goods> selectMarkByZero() {
		return goodsDao.selectMarkByZero();
	}

	public List<Goods> selectMarkByOne() {
		return goodsDao.selectMarkByOne();
	}

	public List<Goods> selectGoodsDetail(int id) {
		return goodsDao.selectGoodsDetail(id);
	}

	public List<Goods> selectNewGoods() {
		return goodsDao.selectNewGoods();
	}

	public List<Goods> selectFreeGoods(PageBean pageBean) {
		return goodsDao.selectFreeGoods(pageBean);
	}

	public List<Goods> goodsPage(PageBean pageBean) {
		return goodsDao.goodsPage(pageBean);
	}

	public void deleteGoods(int goodsId) {
		this.goodsDao.deleteGoods(goodsId);
	}

	public void updateGodos(Goods g) {
		this.goodsDao.updateGodos(g);
	}

	public String uploadGoodsFile(ServletContext application,
			HttpServletRequest httpServletRequest, File file, String pictureFileName)
			throws IOException {

		String s1 = new File(
				new File(application.getRealPath(httpServletRequest.getRequestURI())).getParent())
				.getParent();
		System.out.println("root path: " + s1);
		String saveDirectory = s1 + File.separator + "goodsPicture"; //

		System.out.println("root pictureFileName: " + pictureFileName);
		String prefixName =pictureFileName.substring(pictureFileName.indexOf("."));
		System.out.println("root path: " + file.getName());
		String filePath = DateUtil.imageName() + prefixName;
		System.out.println("new file name " + filePath);
		InputStream is = new FileInputStream(file);

		OutputStream os = new FileOutputStream(new File(saveDirectory, filePath));

		System.out.println("fileFileName: " + filePath);

		byte[] buffer = new byte[500];
		int length = 0;

		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}

		os.close();
		is.close();

		return "goodsPicture"+"/"+filePath;

	}

	public void addGoods(Goods g) {
		this.goodsDao.saveGoods(g);
	}

	public int selectGoodsDetailCount(int id) {
		return goodsDao.selectGoodsDetailCount(id);
	}

	public List<Goods> selectGoodsDetail(int id, PageBean pageBean) {
		return goodsDao.selectGoodsDetail(id, pageBean);
	}

	public int selectGoodsDetailCount2(int id) {
		return goodsDao.selectGoodsDetailCount2(id);
	}

	public List<Goods> selectGoodsDetail2(int id, PageBean pageBean) {
		return goodsDao.selectGoodsDetail2(id, pageBean);
	}

	public String uploadGoodsFile(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
