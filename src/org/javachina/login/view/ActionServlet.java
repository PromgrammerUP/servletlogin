package org.javachina.login.view;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javachina.connectionpool.ConnectionPool;
import org.javachina.login.dao.LeixingDao;
import org.javachina.login.dto.LeixingDto;
import org.javachina.login.dto.ShangpinDto;
import org.javachina.login.dto.UserDto;
import org.javachina.login.service.LeixingService;
import org.javachina.login.service.LoginService;
import org.javachina.login.service.ShangpinService;

/**
 * Servlet implementation class ActionServlet
 */
//@WebServlet("/actionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response);
	}
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String act = request.getParameter("act");
		if("login".equals(act)){
			
			this.login(request, response);
		}else if("listall".equals(act)){
			this.listAll(request, response);
		}else if("getaddpage".equals(act)){
			//跳转到addshangpinpage页面
			//取得所有类型
			ArrayList<LeixingDto> allLeixing = new LeixingService().getAll();
			request.setAttribute("allLeixing", allLeixing);
			try {
				request.getRequestDispatcher("/addshangpin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if("adddo".equals(act)){
			String name=request.getParameter("name");
			String price = request.getParameter("price");
			String address = request.getParameter("address");
			String inputDate = request.getParameter("inputDate");
			String typeId = request.getParameter("typeId");
			String description = request.getParameter("description");
			Double price1 = Double.parseDouble(price);
			Date inputDate1 = Date.valueOf(inputDate);
			int typeId1 = Integer.parseInt(typeId);
			ShangpinDto sp = new ShangpinDto(name, price1, address, inputDate1, typeId1, description);
			//调用shangpinService添加商品
			ShangpinService service = new ShangpinService();
			boolean isSucceed = service.addShangpin(sp);
			//System.out.println(isSucceed);
			//分发页面
			String message=null;
			if(isSucceed){
				message= "success";
			}else{
				message ="fail";
			}
			//如果javascript代码不写在function中，则页面载入时自动执行
			//如果写入到function，则等待调用执行
			response.getWriter().print(""
					+ "<script language='javascript'>"
					+ "alert('"+message+"');"
					+ "window.location='"+request.getContextPath()+"/actionServlet?act=listall';"
					+ "</script>");
//			try {
//				request.getRequestDispatcher("/actionServlet?act=listall").forward(request, response);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}else if("delete".equals(act)){
			//1.接收参数
			String shangpinId = request.getParameter("shangpinId");
			
			//2.调用service执行逻辑
			ShangpinService service = new ShangpinService();
			boolean isSucceed = service.deleteShangpin(Integer.parseInt(shangpinId));
			
			//3.页面返回
			String message = "fail";
			if(isSucceed){
				message = "success";
			}
			response.getWriter().print(""
					+ "<script language='javascript'>"
					+ "alert('"+message+"');"
					+ "window.location='"+request.getContextPath()+"/actionServlet?act=listall';"
					+ "</script>");
			
		}else if("getupdatepage".equals(act)){
			//取得参数
			String shangpinId = request.getParameter("shangpinId");
			
			//调用服务完成逻辑：1商品对应的数据 2所有类型信息
			ShangpinService service = new ShangpinService();
			//System.out.println(shangpinId);
			ShangpinDto shangpin = service.getShangpin(Integer.parseInt(shangpinId));
			
			LeixingService leixingService = new LeixingService();
			ArrayList<LeixingDto> allLeixing = leixingService.getAll();
			
			//分发页面
			request.setAttribute("shangpin", shangpin);
			request.setAttribute("allLeixing", allLeixing);
			request.getRequestDispatcher("/update.jsp").forward(request, response);
			
		}else if("updatedo".equals(act)){
			//取得参数
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String inputeDate = request.getParameter("inputDate");
			String address = request.getParameter("address");
			String typeId = request.getParameter("typeId");
			String description = request.getParameter("description");
			ShangpinDto dto = new ShangpinDto();
			dto.setId(Integer.parseInt(id));
			dto.setName(name);
			dto.setPrice(Double.parseDouble(price));
			dto.setAddress(address);
			dto.setInputDate(Date.valueOf(inputeDate));
			dto.setLeixingId(Integer.parseInt(typeId));
			dto.setDescription(description);
			
			//调用service
			ShangpinService service = new ShangpinService();
			boolean isSucceed = service.updateShangpin(dto);
			
			//页面转发
			String message = "fail";
			if(isSucceed){
				message = "true";
			}
			response.getWriter().print(""
					+ "<script language='javascript'>"
					+ "alert('"+message+"');"
					+ "window.location='"+request.getContextPath()+"/actionServlet?act=listall';"
					+ "</script>");
			
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response){
		String queryString = request.getQueryString();
		//System.out.println(queryString);//采用get方法是可以得到请求头中的请求字符串，当采用post方法是此值为null
		String userid=request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		UserDto user = new UserDto(userid,pwd);
		LoginService service = new LoginService();
		int result = service.login(user);
		if(result==0){
			try {
				request.getRequestDispatcher("/success.jsp?userId="+userid).forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			request.setAttribute("userid", userid);
			request.setAttribute("pwd", pwd);
			if(result==1){
				request.setAttribute("information", "user not found");
			}else{
				request.setAttribute("information", "user pwd not match");
			}
			try {
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void listAll(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("商品展示");
		//进行页面展示
		//1.接收参数 --无
		//2.调用service完成逻辑
		ShangpinService service = new ShangpinService();
		ArrayList<ShangpinDto> allShangpin = service.getAllShangpin();
		//3.页面分发
		request.setAttribute("allShangpin", allShangpin);
		try {
			request.getRequestDispatcher("/listall.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("连接池开始初始化……");
		ConnectionPool.init();
		System.out.println("连接池初始化完毕……");
	}
}
