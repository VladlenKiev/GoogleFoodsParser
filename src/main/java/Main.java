import Model.*;
import Translater.MakerTranslatedPackageMultiThread;
import com.carrotsearch.sizeof.RamUsageEstimator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

//-Xmx256m -Xms128m -XX:PermSize=128m -XX:MaxPermSize=128m -Xmx512m -Xms256m
public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Runtime.getRuntime().totalMemory()="+Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        System.out.println("Runtime.getRuntime().freeMemory()="+Runtime.getRuntime().freeMemory()/1024/1024+"Mb");



        HashMap<String,User> userCountMap=new HashMap<>();
        HashMap<String,Product> productCountMap=new HashMap<>();
        HashMap<String,Word> wordCountMap=new HashMap<>();
        ArrayList<Review> reviewList = new ArrayList<>();

        System.out.println("****************PARSING********************");
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("****************TRANSLATING********************");
        if (getCommandRun(args)&&getCommandRun(args)){
            System.out.println("API KEY founded. Try to translate!");
            MakerTranslatedPackageMultiThread makerTranslatedPackageMultiThread = new MakerTranslatedPackageMultiThread();
            makerTranslatedPackageMultiThread.runMultiThreadTranslatedPackage(makerTranslatedPackageMultiThread.getListReview(reviewList));
        }


        System.out.println("****************END*******************");
        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");

        getReportSout(userCountMap,productCountMap,wordCountMap,reviewList);

    }

    private static void getReportSout(HashMap<String,User> userCountMap,
                                     HashMap<String,Product> productCountMap,
                                     HashMap<String,Word> wordCountMap,
                                     ArrayList<Review> reviewList){
        System.out.println("****************USING RAM*******************");
        System.out.println("Runtime.getRuntime().totalMemory()="+Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        System.out.println("Runtime.getRuntime().freeMemory()="+Runtime.getRuntime().freeMemory()/1024/1024+"Mb");
        System.out.println("USED RAM="+(Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory())/1024/1024+" Mb used");
        System.out.println("****************STATISTIC*******************");

        System.out.println("RamUsage(wordCountMap)="+RamUsageEstimator.sizeOf(wordCountMap)/1024/1024+"Mb");
        System.out.println("RamUsage(userCountMap)="+RamUsageEstimator.sizeOf(userCountMap)/1024/1024+"Mb");
        System.out.println("RamUsage(productCountMap)="+RamUsageEstimator.sizeOf(productCountMap)/1024/1024+"Mb");

        System.out.println("wordCountMap.size()="+wordCountMap.size());
        System.out.println("userCountMap.size()="+userCountMap.size());
        System.out.println("productCountMap.size()=" + productCountMap.size());
        System.out.println("reviewList.size()=" + reviewList.size());
    }

    private static boolean getCommandRun(String[] args){
        String text;
        try {
            String command = args[0];
            System.out.println("COMMAND:"+command);

            if (command.trim().equalsIgnoreCase("translate=true")) {
                //do translate
                //readAPI KEY
                if (checkAPIKEY()){
                    System.out.println("GOING TRANSLATE");
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Running without COMMAND");
        }
        return false;
    }

    private static boolean checkAPIKEY(){
        //String pathApi = "./API_KEY.txt";
        String pathApi = "D:\\DEVenv\\GoogleFoodsParser\\target\\API_KEY.txt";
        BufferedReader br = null;
        String line=null;
        String apiKey=null;
        try {

            br=new BufferedReader(new FileReader(pathApi));

            while ((line=br.readLine())!=null){

                apiKey = line.trim();

            }

        } catch (FileNotFoundException e) {
            System.out.println("File with API KEY is not found! Translate cannot be running!");
            return false;
        } catch (IOException e) {
            System.out.println("File with API KEY can not be read! Translate cannot be running!");
        }
        return true;
    }
}
