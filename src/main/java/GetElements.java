import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

// This class contain CSS selectors and get data from web-site
public class GetElements {

    //Selector whitch select form whitch contain product on page
    private static final String form = "section article";
    //Selector witch select name of product
    private static final String name = "div[class=\"m7er_k4 _9c44d_3TzmE\"] h2 a";

    //Selector witch select discount of product
    private static final String discount = "div[class=\"mp0t_ji mpof_vs _9c44d_1VS-Y  _9c44d_3_DDQ  mpof_vs _9c44d_2MDwk  \"] span[class=\"_9c44d_1uHr2\"]";

    //Selector witch select price of product with discount
    private static final String price = "div[class=\"_9c44d_3AMmE\"] span[class=\"_1svub _lf05o\"]";

    //Selector witch select price of product without discount
    private static final String startedPrice = "div span[class=\"mpof_uk mqu1_ae _9c44d_18kEF m9qz_yp _9c44d_2BSa0  _9c44d_KrRuv\"]";

    //Selector witch select price for deliver if specified
    private static final String deliver = "div[class=\"mqu1_g3\"]";

    //return name of product
    private String getName(Element element) {
        return element.select(name).text();
    }

    //return discount
    private String getDiscount(Element element) {
        return element.select(discount).text();
    }

    //return price of product
    private String getPrice(Element element) {
        return element.select(price).text();
    }

    //return price of product without discount
    private String getStartedPrice(Element element) {
        return element.select(startedPrice).text();
    }

    //return information about deliver
    private String getDeliver(Element element) {
        return element.select(deliver).text();
    }

    // this method select one hunded products with discount
    public void getHudnredElements(HashSet<Product> products, String link) throws IOException {

        Document doc = Jsoup.connect(link).get();
        products.clear();

        //select form with products
        Elements elementDatas = doc.select(form);
        int p = 2;
        while (products.size() < 100) {
            for (Element data : elementDatas) {
                if (getDiscount(data).length() != 0 && products.size() < 100) {
                    products.add(new Product(getName(data), getPrice(data), getDiscount(data), getStartedPrice(data), getDeliver(data)));
                }
            }

            //Change page for next
            doc = Jsoup.connect(link + "&p=" + p).get();
            p++;
            elementDatas = doc.select("section article");
        }
    }
}
