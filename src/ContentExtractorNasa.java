import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNasa implements ContentExtractor {
    
    public List<Content> extractContents(String json) {
        
        // Extrair somente os dados que nos interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> keyList = parser.parse(json);

        List<Content> contents = new ArrayList<Content>();

        // Adicionar os dados necessários para cada conteúdo dentro de Conteúdos
        for (Map<String, String> key : keyList) {

            String title = key.get("title");
            String url = key.get("url");
            var content = new Content(title, url);

            contents.add(content);

        }

        return contents;

    }
    
}
