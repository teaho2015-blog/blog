/**
 * @author teaho2015@gmail.com
 * since 2016/11/24
 */
package com.tea;

public class Constants {

    public static class USER{
        private USER(){}

        public final static String ID = "SYSTEM_GENERATE";
        public final static String NAME = "SYSTEM_GENERATE";
    }

    public static class BLOG {
        private BLOG(){}
        public static final String DEFAULT_IMAGE_URL = "/static/images/blog/default-blog-image.jpg";
        public static final int DEFAULT_PAGE_SIZE = 5;
    }

    public static class URL {
        private URL(){}
        public static final String HTTPCODE_404 = "/page/httpCode/404.jsp";
        public static final String HTTPCODE_401 = "/page/httpCode/401.jsp";
        public static final String HTTPCODE_415 = "/page/httpCode/415.jsp";
        public static final String HTTPCODE_500 = "/page/httpCode/500.jsp";

    }
}
