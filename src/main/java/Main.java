import com.carrotsearch.sizeof.RamUsageEstimator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

//-Xmx256m -Xms128m -XX:PermSize=128m -XX:MaxPermSize=128m
public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        System.out.println("Hello World!");
        System.out.println("Runtime.getRuntime().totalMemory()="+Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        System.out.println("Runtime.getRuntime().freeMemory()="+Runtime.getRuntime().freeMemory()/1024/1024+"Mb");
        HashMap<String,User> userCountMap=new HashMap<String, User>();
        HashMap<String,Product> productCountMap=new HashMap<String, Product>();
        HashMap<String,Word> wordCountMap=new HashMap<String, Word>();



        ParserCSV.userParser(userCountMap,productCountMap,wordCountMap);

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

       // System.out.println(ObjectSizeFetcher.getObjectSize(userArrayList));
        /*for (int i=0;i<=10;i++)
            System.out.println(userArrayList.get(i));*/
    }
}
