class Article {
    private String url;
    private String name;

    public Article (String url) {
        this.url = url;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "https://planetakino.ua" + url;

    }
}