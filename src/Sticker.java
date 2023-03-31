import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
import java.io.InputStream;
//import java.net.URL;

import javax.imageio.ImageIO;

public class Sticker {
    
    public void createSticker(InputStream inputStream, String stickersOutput) throws Exception {
 
        // Leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("input/movie.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage image = ImageIO.read(inputStream);

        // Criar nova imagem em memória com transparência e novo tamanho
        int width = image.getWidth();
        int height = image.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // Copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        // Configurar fonte
        Font fonte = new Font("Impact", Font.BOLD, 90);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // Escrever a frase na nova imagem
        String stickerText = "TOPZERA";
        var fontHeight = graphics.getFontMetrics(fonte).getHeight();
        var fontWidth = graphics.getFontMetrics(fonte).stringWidth(stickerText);
        var positionX = (width - fontWidth) / 2;
        var positionY = newHeight - (200 - (fontHeight / 2)) / 2;
        graphics.drawString(stickerText, positionX, positionY);

        // Fazer outline no texto
        FontRenderContext fontContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(stickerText, fonte, fontContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(positionX, positionY);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // Escrever a nova imagem em um arquivo
        File outputFile = new File(stickersOutput);
        outputFile.getParentFile().mkdirs();

        ImageIO.write(newImage, "png", outputFile);
    }
}
