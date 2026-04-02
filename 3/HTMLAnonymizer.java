import java.io.*;
import java.net.*;
import java.util.regex.*;

public class HTMLAnonymizer {
    private static final String PROXY_BASE = "http://localhost:8080/furtif?url=";
    
    public static String fetchPage(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
    
    public static String anonymizeHTML(String html, String baseURL) {
        Pattern pattern = Pattern.compile("(?i)href\\s*=\\s*[\"']([^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String url = matcher.group(1);
            String absoluteUrl = resolveURL(url, baseURL);
            try {
                String encodedUrl = URLEncoder.encode(absoluteUrl, "UTF-8");
                String quote = matcher.group().contains("\"") ? "\"" : "'";
                String replacement = "href=" + quote + PROXY_BASE + encodedUrl + quote;
                matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
            } catch (UnsupportedEncodingException e) {
                matcher.appendReplacement(result, Matcher.quoteReplacement(matcher.group()));
            }
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    private static String resolveURL(String url, String baseURL) {
        try {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return url;
            }
            URL base = new URL(baseURL);
            URL resolved = new URL(base, url);
            return resolved.toString();
        } catch (Exception e) {
            return url;
        }
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java HTMLAnonymizer <URL>");
            System.out.println("Example: java HTMLAnonymizer http://example.com");
            System.exit(1);
        }
        
        String urlParam = args[0];
        
        try {
            System.out.println("Fetching page: " + urlParam);
            String htmlContent = fetchPage(urlParam);
            System.out.println("Anonymizing links...");
            String anonymized = anonymizeHTML(htmlContent, urlParam);
            System.out.println("Anonymized HTML:");
            System.out.println(anonymized);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
