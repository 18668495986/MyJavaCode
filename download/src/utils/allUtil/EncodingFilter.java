package utils.allUtil;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {  //解决乱码的过滤器（自己配写 有些框架会自带的，只需配置即可）
	public EncodingFilter(){
		System.out.println("过滤器构造");
	}
	public void destroy() {
		System.out.println("过滤器销毁");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //将编码改为utf-8
		response.setContentType("textml;charset=utf-8");
		chain.doFilter(request, response);
	}
 
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器初始化");
	}
}

 