package com.spring.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.spring.model.ErrorInfo;
import com.spring.model.StatusCode;
import com.spring.repository.ErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;


/**
 * @Description 异常过滤器
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
public class ErrorFilter extends ZuulFilter {

    @Autowired
    private ErrorRepository errorRepository;

    Logger logger= LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        logger.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        ErrorInfo errorInfo=new ErrorInfo(StatusCode.Fail_Code,throwable.getCause().getMessage(),"",ctx);
        errorRepository.insert(errorInfo);
        return ctx;
    }
}
