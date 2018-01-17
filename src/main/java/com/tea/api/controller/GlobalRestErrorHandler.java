package com.tea.api.controller;

import com.sun.deploy.net.HttpResponse;
import com.tea.api.dto.IllegalArgumentDTO;
import com.tea.api.dto.MethodNotSupportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalRestErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestErrorHandler.class);

    private boolean includeQueryString = true;
    private boolean includeClientInfo = true;
    private boolean includeHeaders = true;
    private boolean includePayload = true;
    private boolean includeResponse = true;
    private boolean logEnabled = true;
    private int maxPayloadLength = 500;
    private MultipartResolver multipartResolver = new StandardServletMultipartResolver();


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @RequestMapping(value = "/api/v1/**")
    @ResponseBody
    public Object handleError405(HttpServletRequest request, Exception e)   {
//        ModelAndView mav = new ModelAndView("/405");
//        .addObject("exception", e);

        LOGGER.info("handleError405(HttpServletRequest request, Exception e)");
        //get stacktrace info
        List<String> list = Arrays.asList(e.getStackTrace()).stream()
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());

        return MethodNotSupportDTO.newBuilder()
                .defualtDocumentation_url()
                .message(e.getMessage())
                .addErrrors(list)
                .build();
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(value = "/api/v1/**")
    @ResponseBody
    public Object handleIllegalArgumentException(HttpServletRequest request , HttpServletResponse response, Exception e)   {
//        ModelAndView mav = new ModelAndView("/405");
//        .addObject("exception", e);

        logRequest(request, response, System.currentTimeMillis());
        //get stacktrace info
        List<String> list = Arrays.asList(e.getStackTrace()).stream()
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());

        return IllegalArgumentDTO.newBuilder()
                .defualtDocumentation_url()
                .message(e.getMessage())
                .addErrors(list)
                .build();
    }


    private void logRequest(HttpServletRequest requestToUse, HttpServletResponse response, long requestStartMilliseconds) {
        LOGGER.debug(new StringBuffer("")
                .append(createMessage(requestToUse,response)).append(" done in ")
                .append(String.valueOf( System.currentTimeMillis()-requestStartMilliseconds))
                .append("ms.").toString());
    }

    private String createMessage(HttpServletRequest request,HttpServletResponse response) {
        StringBuilder msg = new StringBuilder();
        msg.append("Request [")
                .append(request.getMethod())
                .append(" uri=").append(request.getRequestURI());
        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                msg.append('?').append(queryString);
            }
        }
        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";client=").append(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";session=").append(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                msg.append(";user=").append(user);
            }
        }
        if (isIncludeHeaders()) {
            msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());
        }
        if (isIncludePayload()) {
            ContentCachingRequestWrapper wrapper =
                    WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }
                    msg.append(";payload=").append(payload);
                }else {
                    msg.append(";payload=ZERO");
                }
            }else if(multipartResolver.isMultipart(request)){
                msg.append(";payload=multipart");
            }
        }

        msg.append("],Response[status=").append(response.getStatus());
        if (isIncludeResponse()) {
            ContentCachingResponseWrapper responseWraper =
                    WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (responseWraper != null) {
                if(isImageMedia(responseWraper.getContentType())) {
                    msg.append(";content=image");
                }else {
                    byte[] buf = responseWraper.getContentAsByteArray();
                    if (buf.length > 0) {
                        int length = Math.min(buf.length, getMaxPayloadLength());
                        String payload;
                        try {
                            payload = new String(buf, 0, length, responseWraper.getCharacterEncoding());
                        }
                        catch (UnsupportedEncodingException ex) {
                            payload = "[unknown]";
                        }
                        msg.append(";content=").append(payload);
                    }else {
                        msg.append(";content=ZERO");
                    }
                }

            }
        }
        msg.append("]");
        return msg.toString();
    }

    private boolean isImageMedia(String contentType) {
        return (contentType != null && contentType.toLowerCase().startsWith("image/"));
    }


    public boolean isIncludeQueryString() {
        return includeQueryString;
    }

    public void setIncludeQueryString(boolean includeQueryString) {
        this.includeQueryString = includeQueryString;
    }

    public boolean isIncludeClientInfo() {
        return includeClientInfo;
    }

    public void setIncludeClientInfo(boolean includeClientInfo) {
        this.includeClientInfo = includeClientInfo;
    }

    public boolean isIncludeHeaders() {
        return includeHeaders;
    }

    public void setIncludeHeaders(boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
    }

    public boolean isIncludePayload() {
        return includePayload;
    }

    public void setIncludePayload(boolean includePayload) {
        this.includePayload = includePayload;
    }

    public boolean isIncludeResponse() {
        return includeResponse;
    }

    public void setIncludeResponse(boolean includeResponse) {
        this.includeResponse = includeResponse;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public int getMaxPayloadLength() {
        return maxPayloadLength;
    }

    public void setMaxPayloadLength(int maxPayloadLength) {
        this.maxPayloadLength = maxPayloadLength;
    }
}
