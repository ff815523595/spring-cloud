package com.common.filter;

import com.common.base.ResponseCode;
import com.common.base.ResponseData;
import com.common.util.JWTUtils;
import com.common.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 服务端获取请求的时候进行拦截，并对权限进行校验
 * Created by a on 2017/12/14.
 */
@WebFilter(urlPatterns = "/*")
public class HttpBasicAuthorizeFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HttpBasicAuthorizeFilter.class);

    JWTUtils jwtUtils = JWTUtils.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        logger.info("权限校验拦截器初始化成功！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        String auth = httpRequest.getHeader("Authorization");
        String uri = httpRequest.getRequestURI();
        //健康检查控制
        if (uri.equals("/autoconfig") || uri.equals("/configprops") || uri.equals("/beans") || uri.equals("/dump")
                || uri.equals("/env") || uri.equals("/health") || uri.equals("/info") || uri.equals("/mappings")
                || uri.equals("/metrics") || uri.equals("/shutdown") || uri.equals("/trace")) {
            if(httpRequest.getQueryString() == null || !httpRequest.getQueryString().equals("token=goojia123456")){//得到请求的URL地址中附带的参数并校验
                PrintWriter print = httpResponse.getWriter();
                print.write(JsonUtils.toJson(ResponseData.fail("非法请求【缺少token信息】", ResponseCode.NO_AUTH_CODE.getCode())));
                return;
            }
            chain.doFilter(request, response);
        }else if(uri.equals("/oauth/token")){
            chain.doFilter(request, response);
        } else {
            //验证TOKEN
            if (!StringUtils.hasText(auth)) {
                PrintWriter print = httpResponse.getWriter();
                print.write(JsonUtils.toJson(ResponseData.fail("非法请求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode())));
                return;
            }
            JWTUtils.JWTResult jwt = jwtUtils.checkToken(auth);
            if (!jwt.isStatus()) {
                PrintWriter print = httpResponse.getWriter();
                print.write(JsonUtils.toJson(ResponseData.fail(jwt.getMsg(), jwt.getCode())));
                return;
            }
            chain.doFilter(httpRequest, response);
        }
    }

    @Override
    public void destroy() {

    }
}
