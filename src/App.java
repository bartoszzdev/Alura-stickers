import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar a lista de filmes da api:

        //String movieDBApiKey = System.getenv("MOVIEDB_API_KEY");
        //String theMovieDbUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + movieDBApiKey + "&language=en-US&page=1";

        String nasaApiKey = System.getenv("NASA_API_KEY");
        String nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey + "&start_date=2022-04-13&end_date=2022-04-15";
        ContentExtractor extractor = new ContentExtractorNasa();

        //String imdbUrl = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ContentExtractor extractor = new ContentExtractorIMDB();
        
        var clientHttp = new ClientHttp();
        String json = clientHttp.getData(nasaUrl);

        // Extrair somente os dados que nos interessam:

        List<Content> contentList = extractor.extractContents(json);

        // Exibir os dados que nos interessam e criar os stickers:

        Sticker sticker = new Sticker();

        for (Content content : contentList) {

            String title = content.getTitle();
            String contentUrl = content.getUrl();

            InputStream inputStream = new URL(contentUrl).openStream();
            String fileName = "stickers/" + title + ".png";
            sticker.createSticker(inputStream, fileName);

            System.out.println(title);
            System.out.println();
        }
    }
}
