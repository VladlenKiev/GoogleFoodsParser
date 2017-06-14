import Model.Product;
import Model.Review;
import Model.User;
import Model.Word;
import Translater.*;
import Translater.ListDivider;
import com.carrotsearch.sizeof.RamUsageEstimator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

//-Xmx256m -Xms128m -XX:PermSize=128m -XX:MaxPermSize=128m
public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        System.out.println("Runtime.getRuntime().totalMemory()="+Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        System.out.println("Runtime.getRuntime().freeMemory()="+Runtime.getRuntime().freeMemory()/1024/1024+"Mb");
        HashMap<String,User> userCountMap=new HashMap<String, User>();
        HashMap<String,Product> productCountMap=new HashMap<String, Product>();
        HashMap<String,Word> wordCountMap=new HashMap<String, Word>();
        ArrayList<Review> reviewList = new ArrayList<>();
        ArrayList<Review> reviewList0 = new ArrayList<>();


        Parser.TopUser topUserThread = new Parser.TopUser(userCountMap);
        topUserThread.start();
        Parser.TopProduct topProductThread = new Parser.TopProduct(productCountMap);
        topProductThread.start();
        Parser.TopWord topWordThread = new Parser.TopWord(wordCountMap);
        topWordThread.start();
        Parser.TopReview topReview = new Parser.TopReview(reviewList);
        topReview.start();
        try {
            topUserThread.join();
            topProductThread.join();
            topWordThread.join();
            topReview.join();
            System.out.println("after join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Parser.ParserCSV.userParser(userCountMap,productCountMap,wordCountMap);

        reviewList0.add(new Review("1 As a big fan", "not translated"));
        reviewList0.add(new Review("2 I *love* the Multigrain", "not translated"));
        reviewList0.add(new Review("3 I am a reader", "not translated"));
        reviewList0.add(new Review("4 big fan of sweet", "not translated"));
        reviewList0.add(new Review("5 I *love* the you", "not translated"));
        reviewList0.add(new Review("6 I count fat grams", "not translated"));
        reviewList0.add(new Review("7 I count fat rat", "not translated"));
        reviewList0.add(new Review("8 As a big fan of potato", "not translated"));
        reviewList0.add(new Review("9 I *love* the flavor", "not translated"));
        reviewList0.add(new Review("10 I am a fat grams", "not translated"));
        reviewList0.add(new Review("11 big fan of sweet", "not translated"));
        reviewList0.add(new Review("12 I *love* grain", "not translated"));
        reviewList0.add(new Review("13 I count fat ram", "not translated"));

        TranslaterMultiThread translaterMultiThread = new TranslaterMultiThread();
        translaterMultiThread.runMulthiThreadTranslate(translaterMultiThread.getListReview(reviewList0));

        //ListDivider.divideList(reviewList0);
        //reviewList.add(new Model.Review("I *love* the Multigrain flavor of these chips/crackers!  I especially love the fact that they're only 80 mg of sodium--so I was totally excited to see--under the nutritional info at Amazon--that the olive and jalapeno flavors were also 80 mg of sodium per serving.  So I ordered all three. ", "not translated"));
       /* Translater translater0 = new Translater(reviewList);
        translater0.start();
        Translater translater1 = new Translater(reviewList0);
        translater1.start();
        try {
            translater0.join();
            translater1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
        System.out.println("Runtime.getRuntime().totalMemory()="+Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        System.out.println("Runtime.getRuntime().freeMemory()="+Runtime.getRuntime().freeMemory()/1024/1024+"Mb");
        System.out.println((Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory())/1024/1024+" Mb used");

        System.out.println("RamUsage(wordCountMap)="+RamUsageEstimator.sizeOf(wordCountMap)/1024/1024+"Mb");
        System.out.println("RamUsage(userCountMap)="+RamUsageEstimator.sizeOf(userCountMap)/1024/1024+"Mb");
        System.out.println("RamUsage(productCountMap)="+RamUsageEstimator.sizeOf(productCountMap)/1024/1024+"Mb");
        System.out.println("RamUsage(reviewList)="+RamUsageEstimator.sizeOf(reviewList)/1024/1024+"Mb");

        System.out.println("wordCountMap.size()="+wordCountMap.size());


        // System.out.println(ObjectSizeFetcher.getObjectSize(userArrayList));
        /*for (int i=0;i<=10;i++)
            System.out.println(userArrayList.get(i));*/
    }
}
