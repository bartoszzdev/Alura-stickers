public class Content {
    
    private final String title;
    private final String url;

    public Content(String title, String url) {
        this.title = title;
        this.url = url;
    }

    // Getters
    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }
}
