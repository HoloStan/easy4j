package me.web.framework.utils.page;

public class Page<T> {
    private T data;
    private Integer currentPage;
    private Integer totalPage;
    private Integer currentLength;
    private Integer totalLength;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getCurrentLength() {
        return currentLength;
    }
    public void setCurrentLength(Integer currentLength) {
        this.currentLength = currentLength;
    }
    public Integer getTotalLength() {
        return totalLength;
    }
    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }
 
}
