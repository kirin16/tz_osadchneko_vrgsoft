package artem.com.tz_osadchneko_vrgsoft.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("data")
    @Expose
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticles(Article article) {
        this.article = article;
    }
}
