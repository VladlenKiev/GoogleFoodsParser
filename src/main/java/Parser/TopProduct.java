package Parser;

import Model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by admin on 12.06.2017.
 */
public class TopProduct extends Thread {
    public Product product;
    public HashMap<String, Product> productCountMap;

    public TopProduct() {
    }

    public TopProduct(HashMap<String, Product> productCountMap) {
        this.productCountMap = productCountMap;
    }

    @Override
    public void run() {
        productParserThread(productCountMap);
    }

    public void productParserThread(HashMap<String, Product> productCountMap) {
        String path = "D:\\JAVA pr\\amazon-fine-food-reviews\\Reviews.csv";
        BufferedReader br = null;
        String line = null;

        try {

            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {

                Product product = splitCSVforProduct(line);
                assumeCountReviewsPerProduct(product, productCountMap);


            }

            findMostCommentProduct(productCountMap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static Product splitCSVforProduct(String line) {
        String[] valueCSV = line.split(",");
        return new Product(valueCSV[1]);
    }

    private static void findMostCommentProduct(HashMap<String, Product> productCountMap) {
        SortedSet<Product> productsSortSet = new TreeSet<>(Collections.reverseOrder());
        productsSortSet.addAll(productCountMap.values());

        int c = 0;
        for (Product product : productsSortSet) {
            if (c == productsSortSet.size() - 1) {
                for (Product pMap : productCountMap.values()) {
                    if (pMap.getCounter() == 1)
                        System.out.println(c + " ) " + pMap.toString());
                    if (c == 400)
                        break;
                    c++;
                }
            }
            System.out.println(c + ". " + product.toString());
            c++;
        }
    }

    public static void assumeCountReviewsPerProduct(Product product, HashMap<String, Product> productCountMap) {
        if (productCountMap.containsKey(product.getProductId())) {
            productCountMap.get(product.getProductId()).increaseCounterCommentPerProduct();
        } else {
            productCountMap.put(product.getProductId(), product);
        }

    }
}
