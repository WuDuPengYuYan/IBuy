package cn.buy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.buy.utils.EmptyUtils;

//Servlet基类
@SuppressWarnings("serial")
public abstract class AbstractBaseServlet extends HttpServlet{
		
	@SuppressWarnings("rawtypes")
	public abstract Class getServletClass();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	//doPost方法中处理
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//localhost:8080/IBuy/HomeServlet?action = 'index'
		String action = request.getParameter("action");
		System.out.println("有请求过来吗？－－－－－>"+action);
		Method method = null;
		Object result = null;
		
		if(EmptyUtils.isEmpty(action)){//如果为空，跳转到首页
			request.getRequestDispatcher("/pre/index.jsp").forward(request, response);
		}else{
			try {
				method = getServletClass().getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
				result = method.invoke(this, request,response);//   localhost:8080/IBuy/pre/index.jsp   index  
				//result进行处理
				toView(request, response, result);
			} catch (NoSuchMethodException e) {//找不到方法
				String viewName = "404.jsp";
				request.getRequestDispatcher(viewName).forward(request, response);
				e.printStackTrace();
			} catch (Exception e) {//系统异常
				String viewName = "500.jsp";
				request.getRequestDispatcher(viewName).forward(request, response);
				e.printStackTrace();
			}
		}
	}
	//返回的逻辑名变为一个真实视图名称
	public void toView(HttpServletRequest request, HttpServletResponse response,Object result) 
			throws ServletException, IOException{
		//非空判断
		if(!EmptyUtils.isEmpty(result)){
			if(result instanceof String){//对象类型进行判断
				String viewName = result+".jsp";
				request.getRequestDispatcher(viewName).forward(request, response);
			}else{//如果是其他的结果
				write(result,response);
			}
		}
		
	}
	
	//封装一个向服务器发送数据的方法
	public void write(Object obj,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String json = JSONObject.toJSONString(obj);//数据转换成json对象
		PrintWriter out = null;
		if(null != response){
			try {
				out = response.getWriter();
				out.print(json);
				out.flush();//缓存中
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				out.close();
			}
		}
	}
	
}
