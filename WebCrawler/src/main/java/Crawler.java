import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    HashSet <String> urlSet;
    int MAX_DEPTH=2;
    Crawler(){
        urlSet=new HashSet<String>();
    }
    public void  getPageTextsAndLinks(String url, int depth) {
        if (urlSet.contains(url)) {
            return;
        }
        if (MAX_DEPTH > depth) {
            return;
        }
        depth++;
        try {
            //this Jsoup will convert url(html object) to a java document object.
            Document document = Jsoup.connect(url).timeout(5000).get();
            //Indexer works start here
            Indexer indexer= new Indexer(document,url);
            System.out.println(document.title());
            //anchor tag <a>....</a>...it will create hyperlink(it will redirect to another page)
            //<a href=" "> </a>.....this href work as the url
            //that all are the present in the document
            Elements availableLinksOnPage = document.select("a[href]");
            //we are just getting href value
            //here Element object
            for (Element currentLink : availableLinksOnPage) {
                //currentLink is Element object
                //to convert String object, we are using attributeKey
                getPageTextsAndLinks(currentLink.attr("abs:href"), depth);
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
//Initialize Crawler object
        Crawler crawler= new Crawler();
        crawler.getPageTextsAndLinks("https://www.javatpoint.com/", 1);
    }
}