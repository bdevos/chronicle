package nl.jpoint.chronicle.domain;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "pages", noClassnameStored = true)
public class Page {

    private String parent;

    private String title;

    @Id
    private String uri;

    private String content;

    @Embedded
    private PageMeta meta = new PageMeta();

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PageMeta getMeta() {
        return meta;
    }

    public void setMeta(PageMeta meta) {
        this.meta = meta;
    }
}
