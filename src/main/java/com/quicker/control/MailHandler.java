package com.quicker.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商城信息资源分配
 */
@Controller
public class MailHandler {
//	private MailService MailService;

	/**
	 * 获取商品列表
	 *
	 * @return
	 */
//	@RequestMapping(value = "/goodsList/{number}", method = RequestMethod.GET)
//	public BasicJson getGoodsList(@PathVariable Integer number) {
//		BasicJson basicJson = new BasicJson();
//		try {
//			basicJson.setJsonString(MailService.goodsList(number));
//		} catch (Exception e) {
//			basicJson.getErrorMsg().setDescription("获取商品信息失败");
//			return basicJson;
//		}
//		basicJson.setStatus(true);
//		return basicJson;
//	}

	/**
	 * 获取商品详情 获取�?��goodsId的详细信息，‘content’字�?	 */
//	@RequestMapping(value = "/goodsContent", method = RequestMethod.GET)
//	public BasicJson getGoodsContent(HttpServletRequest request) {
//		BasicJson basicJson = new BasicJson();
//		Integer goodsId = (Integer) request.getSession()
//				.getAttribute("goodsId");
//		MailGoodsInfoEntity mailGoodsInfoEntity = null;
//		try {
//			mailGoodsInfoEntity = MailService.getGoodsInfo(goodsId);
//			basicJson.setJsonString(mailGoodsInfoEntity.getContent());
//		} catch (Exception e) {
//			basicJson.getErrorMsg().setDescription("获取商品信息失败");
//			return basicJson;
//		}
//		return basicJson;
//	}

	/**
	 * “喜欢�?回调 每次调用更新�?��goodsId的like字段 +1
	 */
	@RequestMapping(value = "/like")
	@ResponseBody
	public void updateLike(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute("lastTime_like") == null) {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("初次访问，可以点赞的咩~~");
//			MailGoodsInfoEntity mailGoodsInfoEntity = null;
//			Integer goodsId = (Integer) request.getSession().getAttribute(
//					"goodsId");
//			boolean islike = (boolean) request.getSession().getAttribute(
//					"islike");
//			try {
//				mailGoodsInfoEntity = MailService.getGoodsInfo(goodsId);
//				flag = MailService.like(mailGoodsInfoEntity, islike);
//			} catch (Exception e) {
//				basicJson.getErrorMsg().setDescription("获取商品信息失败");
//				return basicJson;
//			}
//			if (flag)
//				basicJson.setStatus(true);
//			else {
//				basicJson.setStatus(false);
//				basicJson.getErrorMsg().setDescription("处理过程中出现错�?);
//			}
			request.getSession().setAttribute("lastTime_like", new Date());
			
//			return basicJson;
		} else {
			long lastTime_like = ((Date) (request.getSession()
					.getAttribute("lastTime_like"))).getTime();
			long thisTime_like = new Date().getTime();
			if ((thisTime_like - lastTime_like)>= 60000) {
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print(request.getSession().getAttribute("lastTime_like"));
				out.print("可以点赞的咩~~");
				out.print((thisTime_like - lastTime_like));
				request.getSession().setAttribute("lastTime_like", new Date());
			}
//				MailGoodsInfoEntity mailGoodsInfoEntity = null;
//				Integer goodsId = (Integer) request.getSession().getAttribute(
//						"goodsId");
//				boolean islike = (boolean) request.getSession().getAttribute(
//						"islike");
//				try {
//					mailGoodsInfoEntity = MailService.getGoodsInfo(goodsId);
//					flag = MailService.like(mailGoodsInfoEntity, islike);
//				} catch (Exception e) {
//					basicJson.getErrorMsg().setDescription("获取商品信息失败");
//					return basicJson;
//				}
//				if (flag)
//					basicJson.setStatus(true);
//				else {
//					basicJson.setStatus(false);
//					basicJson.getErrorMsg().setDescription("处理过程中出现错�?);
//				}
//				// 再重新设置一下当前操作的时间，为下次作比较准备�?
				
			else {
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print("不能点赞的咩~");
//				basicJson.setStatus(false);
//				basicJson.getErrorMsg().setDescription("请稍后再点赞");
			}
		}
//		return basicJson;
	}

	/**
	 * “阅读�?回调 每次调用更新�?��goodsId的views字段 +1
	 */

//	@RequestMapping(value = "/read", method = RequestMethod.POST)
//	public BasicJson updateread(HttpServletRequest request) {
//		BasicJson basicJson = new BasicJson();
//		boolean flag;
//		if (request.getSession().getAttribute("lastTime_read") == null) {
//			MailGoodsInfoEntity mailGoodsInfoEntity = null;
//			Integer goodsId = (Integer) request.getSession().getAttribute(
//					"goodsId");
//			try {
//				mailGoodsInfoEntity = MailService.getGoodsInfo(goodsId);
//				flag = MailService.read(mailGoodsInfoEntity);
//			} catch (Exception e) {
//				basicJson.getErrorMsg().setDescription("获取商品信息失败");
//				return basicJson;
//			}
//			if (flag)
//				basicJson.setStatus(true);
//			else {
//				basicJson.setStatus(false);
//				basicJson.getErrorMsg().setDescription("处理过程中出现错�?);
//			}
//			request.getSession().setAttribute("lastTime_read", new Date());
//			return basicJson;
//		} else {
//			long lastTime_read = ((Date) (request.getSession()
//					.getAttribute("lastTime_read"))).getTime();
//			long thisTime_read = new Date().getTime();
//			// 超时时间�?分钟,如果超时，则可以执行if下的逻辑流程�?			if ((thisTime_read - lastTime_read) * 1000 * 60 * 5 >= 60000) {
//				MailGoodsInfoEntity mailGoodsInfoEntity = null;
//				Integer goodsId = (Integer) request.getSession().getAttribute(
//						"goodsId");
//				try {
//					mailGoodsInfoEntity = MailService.getGoodsInfo(goodsId);
//					flag = MailService.read(mailGoodsInfoEntity);
//				} catch (Exception e) {
//					basicJson.getErrorMsg().setDescription("获取商品信息失败");
//					return basicJson;
//				}
//				if (flag)
//					basicJson.setStatus(true);
//				else {
//					basicJson.setStatus(false);
//					basicJson.getErrorMsg().setDescription("处理过程中出现错�?);
//				}
//				request.getSession().setAttribute("lastTime_read", new Date());
//			} else {
//				basicJson.setStatus(false);
//				basicJson.getErrorMsg().setDescription("请稍后再浏览");
//			}
//		}
//		return basicJson;
//	}
}
