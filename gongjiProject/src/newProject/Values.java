package newProject;

public class Values {
    private String date;
    private String title;
    private String url;
    private String field;
    private String name;
    private String tel;
    private String article;

    public Values(String date, String title, String url, String field, String name, String tel, String article) {
        this.date = date;
        this.title = title;
        this.url = url;
        this.field = field;
        this.name = name;
        this.tel = tel;
        this.article = article;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

}
