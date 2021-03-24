import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {

        WriteToFile writeToFile = new WriteToFile();
        GetElements getElements = new GetElements();

        //Map which contain name of categories and links for them
        Map<String, String> links = new HashMap<String, String>();
        links.put("Electronika", "https://allegro.pl/kategoria/elektronika?string=bargain_zone&bmatch=cl-e2101-d3681-c3682-40-ele-1-3-0319");
        links.put("Moda", "https://allegro.pl/kategoria/dom-i-ogrod?string=bargain_zone&bmatch=e2101-d3681-c3682-hou-1-3-0319");
        links.put("Supermarket", "https://allegro.pl/kategoria/supermarket?string=bargain_zone&bmatch=e2101-d3681-c3682-sup-1-3-0319");

        //Set which contain unique values
        HashSet<Product> products = new HashSet<Product>();

        //Select and write elements to file
        for (Map.Entry<String, String> item : links.entrySet()) {
            getElements.getHudnredElements(products, item.getValue());
            writeToFile.writeToCSVFile(products, item.getKey());
        }

    }
}
