package org.example.plugin.pageplugin;

/**
 * TODO 分页插件 pojo
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-11 8:43 PM
 */
public class PageParams {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 是否启用插件
     */
    private Boolean useFlag;
    /**
     * 是否检查当前页码有效性
     */
    private Boolean checkFlag;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer totalPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
