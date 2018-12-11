package com.fc.test.model.auto;

public class News {
    private Integer id;

    private String title;

    private String reader;

    private String summary;

    private String cover;

    private String description;

    private String textTime;

    private String build;

    private String source;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reader='" + reader + '\'' +
                ", summary='" + summary + '\'' +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", textTime='" + textTime + '\'' +
                ", build='" + build + '\'' +
                ", source='" + source + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }

}