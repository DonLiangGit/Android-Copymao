package pojo;

import java.util.List;

/**
 * Created by new on 1/19/15.
 */
public class Photos {
    private long page;
    private String pages;
    private long perpage;
    private String total;
    private List<Photo> photo;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public long getPerpage() {
        return perpage;
    }

    public void setPerpage(long perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getList() {
        return photo;
    }

    public void setList(List<Photo> photoList) {
        this.photo = photoList;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "page=" + page +
                ", pages='" + pages + '\'' +
                ", perpage=" + perpage +
                ", total='" + total + '\'' +
                ", photo=" + photo +
                '}';
    }
}
