package com.tea.frame.ums;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tea.frame.ums.service.impl.RegisterUmsService;

import com.tea.frame.ums.model.User;
import com.tea.frame.util.UserUtil;

/**
 * @date 2013.7.11
 * @update  2015.4.9 by hetingliang
 * @说明：处理同一账号重复登录问题
 */
public class SingleLoginFilter implements Filter {

    protected FilterConfig filterConfig;
    private RegisterUmsService registerUmsService;
    private static final String RESOURCE_SUFFIX = new String("^.+(\\.css|\\.js|\\.png|\\.jpg|\\.gif|\\.svg|\\.flash|\\.pdf|\\.swf|\\.html)$");

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String projectNameString = req.getContextPath();
        String strURL = req.getServletPath();
        Pattern pattern= Pattern.compile(RESOURCE_SUFFIX);
        Matcher m = pattern.matcher(strURL);
        if (strURL.indexOf("/register") >= 0
                ||strURL.indexOf("/static")>0
                || strURL.indexOf("singleloginturnError.xhtml") > 0
                || strURL.indexOf("singleloginError .xhtml") > 0
                || strURL.indexOf("welcome.jsp") > 0
                ||m.find()) {
        } else {
            String sessionId = session.getId();
            User user = UserUtil.getCurrentLoginUser(req);
            if (user != null && !user.getId().equals("manager")) {
                String oldSessionid = registerUmsService.getUser(
                        user.getId()).getSessionid();
                if (!sessionId.equals(oldSessionid)) {
                    // session.invalidate();
                    res.sendRedirect(projectNameString
                            + "/register");
                    return;
                }
            }else if (user == null){
                res.sendRedirect(projectNameString
                        + "/register");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;
    }

    public RegisterUmsService getRegisterUmsService() {
        return registerUmsService;
    }

    public void setRegisterUmsService(RegisterUmsService registerUmsService) {
        this.registerUmsService = registerUmsService;
    }
}
