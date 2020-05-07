/**
 * @author teaho2015@gmail.com
 * since 2017/2/21
 */
package net.teaho.blog.server.blog.dto;

public class PageDTO {

    private int pageSize; // 每页的记录数

    private long currentPageNum;

    private Object data; // 当前页中存放的记录,类型一般为List

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

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
