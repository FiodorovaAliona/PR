import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Spider {
    private List<URLInfo> allLinks;
    private int levelLimit;
    private String keyword;
    private int keywordCount;

    public Spider() {
        this.allLinks = new ArrayList<URLInfo>();
    }

    public void processLinks(final String url, int lev, String keyword) {
        final String host;
        this.keyword = keyword;
        this.levelLimit = lev;
        this.keywordCount = 0;
        final int level = lev - 1;
        if (lev < 0)
            return;
        try {
            final URI baseUrl = new URI(url);
            host = baseUrl.getHost();
        } catch (Exception e) {
            return;
        }
        getLinksWithCount(host, url, level);
    }

    private void getLinksWithCount(String host, String adr, int level) {
        try {
            Document doc = Jsoup.parse(getPageByURL(adr));
            Elements links = doc.select("a[href]");
            int count = countKeywordEntries(doc.body().text());
            keywordCount += count;
            allLinks.add(new URLInfo(adr, (levelLimit - level), count));
            for (Element link : links) {
                String url = link.attr("href");
                url = formatUrl(host, url);
                if (url.isEmpty()) {
                    continue;
                }
                URLInfo foundLink = new URLInfo(url, levelLimit - level);
                int index = allLinks.indexOf(foundLink);
                if (index < 0 && level > 0) {
                    getLinksWithCount(host, url, level - 1);
                } else {
                    if (index < 0){
                        allLinks.add(foundLink);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatUrl(String host, String url) {
        try {
            URI uri = new URI(url);
            if (uri.getHost() == null && uri.getPath() == null)
                return "";
            if (uri.getHost() == null) {
                String path = uri.getPath();
                if (path.length() > 0)
                    if (path.charAt(0) != '/')
                        path = "/" + path;
                url = "http://" + host + path;
            } else if (url.charAt(0) == '/')
                url = "http:" + url;
            return url;
        } catch (URISyntaxException u) {
            return "";
        }
    }

    private int countKeywordEntries(String text) {
        text = text.toLowerCase();
        String keywordLower = keyword.toLowerCase();
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = text.indexOf(keywordLower, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += keywordLower.length();
            }
        }
        return count;
    }

    private static String getPageByURL(String requestURL) {
        URL url;
        String response = "";
        System.out.println("Get: " + requestURL);
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(7000);
            conn.setConnectTimeout(7000);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("User-agent", System.getProperty("http.agent"));
            conn.setDoInput(true);
            int responseCode = conn.getResponseCode();
            if (responseCode < 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = br.toString();
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void printLink() {
        for (URLInfo link : allLinks) {
            System.out.println("Link adress: " + link.getLink() + " Count: " + link.getCount());
        }
        System.out.println("All links found = " + allLinks.size());
        System.out.println("All keyword usage = " + keywordCount);
    }
}
