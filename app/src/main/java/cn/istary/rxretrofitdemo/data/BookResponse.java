package cn.istary.rxretrofitdemo.data;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 19:27
 * DESCRIPTION:
 */

public class BookResponse {

    private String title;
    private String pic;
    private String author_name;
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
