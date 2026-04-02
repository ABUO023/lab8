import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XSLTransformer {
    public static void main(String[] args) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
            new StreamSource(new File("pubmed_result.xsl"))
        );
        transformer.transform(
            new StreamSource(new File("pubmed_result.xml")),
            new StreamResult(new File("pubmed_result.html"))
        );
        System.out.println("Transformation complete! pubmed_result.html created.");
    }
}
