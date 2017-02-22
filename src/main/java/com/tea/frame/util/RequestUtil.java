/*
 * @company sinobest 
 * @date    2008-5-29
 * @author  luxunheng
 */
package com.tea.frame.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    /**
     * 在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址,用request.getRemoteAddr()方法获取的IP地址是：127.0.0.1
     * 或 192.168.1.110，而并不是客户端的真实ＩＰ.
     * 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串Ｉｐ值，究竟哪个才是真正的用户端的真实IP呢？答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     * squid的设置问题会导致x-forwarded-for为unknown
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); // apache的代理
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // weblogic的代理
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRequestUrl(HttpServletRequest request) {
        return buildRequestUrl(request.getServletPath(), request
                .getRequestURI(), request.getContextPath(), request
                .getPathInfo(), request.getQueryString());
    }

    private static String buildRequestUrl(String servletPath,
                                          String requestURI, String contextPath, String pathInfo,
                                          String queryString) {

        String uri = servletPath;

        if (uri == null) {
            uri = requestURI;
            uri = uri.substring(contextPath.length());
        }

        return uri + ((pathInfo == null) ? "" : pathInfo)
                + ((queryString == null) ? "" : ("?" + queryString));
    }
}
