/**
 * @author teaho2015@gmail.com
 * since 2017/2/21
 */
package net.teaho.blog.server.api.dto;

import net.teaho.blog.server.common.Builder;
import net.teaho.blog.server.blog.domain.Blog;

import java.util.ArrayList;
import java.util.List;

public class PageDTO {

    private int pageSize; // 每页的记录数

    private long currentPageNum;

    private List data; // 当前页中存放的记录,类型一般为List

    private long totalPageCount;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(long currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public Object getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public static InnerBuilder newBuilder() {
        return new InnerBuilder();
    }

    public static class InnerBuilder implements Builder<PageDTO> {
        private PageDTO pageDTO;

        public InnerBuilder() {
            pageDTO = new PageDTO();
        }

        public InnerBuilder pageSize(int n) {
            pageDTO.pageSize = n;
            return this;
        }

        public InnerBuilder currentPageNum(long n) {
            pageDTO.currentPageNum = n;
            return this;
        }

        public InnerBuilder totalPageCount(long n) {
            pageDTO.totalPageCount = n;
            return this;
        }

        public InnerBuilder data(List<Blog> data) {
            if (pageDTO.data == null) {
                pageDTO.data = new ArrayList<>();
            }
            for (Blog o : data) {
                pageDTO.data.add(BlogDTO.newBuilder().parse(o).build());
            }
            return this;
        }

        @Override
        public PageDTO build() {
            return pageDTO;
        }
    }
}
