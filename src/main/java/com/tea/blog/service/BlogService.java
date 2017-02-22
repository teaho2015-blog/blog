/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-7
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
package com.tea.blog.service;

import com.tea.frame.jdbc.support.Page;
import com.tea.blog.domain.Blog;
import com.tea.blog.vo.BlogVO;

public interface BlogService {
    public Page getPage(int pageNum);

    public Page getPage(int pageNum, int pageSize);

    public Blog getArticle(String id);

    public BlogVO getArticleAttachElderId(String id);

    public Blog getElderArticle(String id);

}
