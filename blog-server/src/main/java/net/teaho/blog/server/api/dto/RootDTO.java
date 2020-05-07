/**
 * @author teaho2015@gmail.com
 * since 2017/5/1
 */
package net.teaho.blog.server.api.dto;


public class RootDTO {

    public final String documentation_url = new DevelopmentPath(new Domain()).getName();

    public final String blog_page_url = new IntIdPlaceHolderPath(new PagePath(new BlogPath(new APIPath(new Domain())))).getName();

    public final String blog_article_url =  new IdPlaceHolderPath(new ArticlePath(new BlogPath(new APIPath(new Domain())))).getName();

}
