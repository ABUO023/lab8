
public class TestAnonymizer {
    public static void main(String[] args) {
        String testHTML = "<html><body>\n" +
            "<a href=\"http://example.com/page1\">Link 1</a>\n" +
            "<a href='http://example.com/page2'>Link 2</a>\n" +
            "<a href=\"/page3\">Relative Link</a>\n" +
            "</body></html>";
        
        System.out.println("Original HTML:");
        System.out.println(testHTML);
        System.out.println("\nAnonymized HTML:");
        
        String anonymized = HTMLAnonymizer.anonymizeHTML(testHTML, "http://example.com/");
        System.out.println(anonymized);
    }
}
