import com.carrotsearch.sizeof.RamUsageEstimator;
import com.sun.management.GarbageCollectionNotificationInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Владимир on 10.06.2017.
 */
public class ParserCSV {

    public static void userParser(HashMap<String,User> userCountMap,HashMap<String, Product> productCountMap, HashMap<String,Word> wordCountMap){
        String path = "L:\\!Projects\\GoogleFoodsAnalyze\\Reviews.csv";
        BufferedReader br = null;
        String line=null;

        //ArrayList<Word> wordList=new ArrayList<>();
        //HashMap<String,Product> productCountMap=new HashMap<String, Product>();

        try {

            br=new BufferedReader(new FileReader(path));

            while ((line=br.readLine())!=null){

                User user = splitCSVforUser(line);
                Product product = splitCSVforProduct(line); //new Product();
                Word word[] = splitCSVforWord(line); //new Product();
                //product.setProductId(splitCSVforProduct(line));
                assumeCountWordPerComments(word, wordCountMap);
                assumeCountReviewsPerUser(user, userCountMap);
                assumeCountReviewsPerProduct(product,productCountMap);


            }
            findMostActiveWord(wordCountMap);
            findMostActiveUsers(userCountMap);
            findMostCommentProduct(productCountMap);

            System.out.println("wordCountMap.size()="+wordCountMap.size());
            System.out.println("userCountMap.size()="+userCountMap.size());
            System.out.println("productCountMap.size()="+productCountMap.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static User splitCSVforUser(String line){
        String[] valueCSV=line.split(",");
        return new User(valueCSV[2],valueCSV[3]);
    }
    private static Product splitCSVforProduct(String line){
        String[] valueCSV=line.split(",");
        return new Product(valueCSV[1]);
    }
    private static Word[] splitCSVforWord(String line){
        String[] valueCSV=line.split(",");
        String[] words=valueCSV[9].split(" ");
        Word word[] = new Word[words.length];
        for(int i=0;i<words.length;i++){
            word[i] = new Word(words[i]);
        }

        return word;
    }

    private static void findMostCommentProduct(HashMap<String,Product> productCountMap){
        SortedSet<Product> productsSortSet=new TreeSet<>(Collections.reverseOrder());
        productsSortSet.addAll(productCountMap.values());

        int c=0;
        for(Product product:productsSortSet){
            if(c==productsSortSet.size()-1){
                for (Product pMap:productCountMap.values()){
                    if (pMap.getCounter()==1)
                        System.out.println(c+" ) "+pMap.toString());
                    if (c==400)
                        break;
                    c++;
                }
            }
            System.out.println(c+". "+product.toString());
            c++;
        }
    }

    private static void findMostActiveWord(HashMap<String,Word> wordCountMap){
        SortedSet<Word> wordsSortSet=new TreeSet<>(Collections.reverseOrder());
        wordsSortSet.addAll(wordCountMap.values());

        int c=0;
        for(Word w:wordsSortSet){
            /*if(c==productsSortSet.size()-1){
                for (Product pMap:productCountMap.values()){
                    if (pMap.getCounter()==1)
                        System.out.println(c+" ) "+pMap.toString());
                    if (c==400)
                        break;
                    c++;
                }
            }*/
            System.out.println(c+". "+w.toString());
            c++;
        }
    }


    public static void findMostActiveUsers(HashMap<String,User> userCountMap){
        SortedSet<User> usersSortSet=new TreeSet<>(Collections.reverseOrder());
        usersSortSet.addAll(userCountMap.values());
        System.out.println(userCountMap.size());
        System.out.println(usersSortSet.size());

        int c=0;
        for(User u:usersSortSet){
            if(c==usersSortSet.size()-1){
                for (User uMap:userCountMap.values()){
                    if (uMap.getCounter()==1)
                        System.out.println(c + " ) " + uMap.toString());
                    if (c==400)
                        break;
                    c++;
                }
            }
            System.out.println(c +". "+u.toString());
            c++;
        }
    }

    public static void assumeCountReviewsPerUser(User user, HashMap<String,User> userCountMap){
        if (userCountMap.containsKey(user.getUserId())) {
            userCountMap.get(user.getUserId()).increaseCounterCommentPerUser();
        } else {
            userCountMap.put(user.getUserId(), user);
        }

    }
    public static void assumeCountReviewsPerProduct(Product product, HashMap<String,Product> productCountMap){
        if (productCountMap.containsKey(product.getProductId())) {
            productCountMap.get(product.getProductId()).increaseCounterCommentPerProduct();
        } else {
            productCountMap.put(product.getProductId(), product);
        }

    }
    public static void assumeCountWordPerComments(Word word[], HashMap<String,Word> wordCountMap){
        for (Word w:word){
            if (wordCountMap.containsKey(w.getWord())) {
                wordCountMap.get(w.getWord()).increaseCounterWord();
            } else {
                wordCountMap.put(w.getWord(), w);
            }
        }


    }
}
