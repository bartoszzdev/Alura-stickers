import java.util.List;

public interface ContentExtractor {
    
    abstract List<Content> extractContents(String json);

}
