import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XSLTransformer {
    public static void main(String[] args) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
            new StreamSource(new File("AllTracksIndex.xsl"))
        );
        transformer.transform(
            new StreamSource(new File("MusicLibrary.xml")),
            new StreamResult(new File("AllTracksIndex.html"))
        );
        System.out.println("Transformation complete! AllTracksIndex.html created.");
    }
}
