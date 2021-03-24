import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

public class WriteToFile {

    //This method create and write data to CSV file
    public void writeToCSVFile(HashSet<Product> products, String title) {

        try (PrintWriter writer = new PrintWriter(new File(title + ".csv"))) {

            writer.print(title + "\n");

            for (Product test : products) {

                StringBuilder sb = new StringBuilder();
                sb.append(test.name);
                sb.append(',');
                sb.append(test.price);
                sb.append('\n');

                writer.write(sb.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

