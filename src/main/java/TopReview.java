import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 13.06.2017.
 */
public class TopReview extends Thread{
    public ArrayList<Review> reviewList;
    public TopReview(){}
    public TopReview(ArrayList<Review> reviewList){
        this.reviewList = reviewList;
    }

    @Override
    public void run() {
        reviewParserThread(reviewList);
    }

    public void reviewParserThread(ArrayList<Review> reviewList){
        String path = "D:\\JAVA pr\\amazon-fine-food-reviews\\Reviews.csv";
        BufferedReader br = null;
        String line=null;

        //ArrayList<Word> wordList=new ArrayList<>();
        //HashMap<String,Product> productCountMap=new HashMap<String, Product>();

        try {

            br=new BufferedReader(new FileReader(path));

            while ((line=br.readLine())!=null){

                Review review = splitCSVforReview(line);
                reviewList.add(review);
            }

            System.out.println("reviewList.size()="+reviewList.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        iteratorReview(reviewList);
    }

    public static void iteratorReview(ArrayList<Review> reviewList){
        int c=0;
        for (Review s:reviewList){
            System.out.println("Review #"+c+s.toString());
            /*if (s.getReviewOrg().length()>1000)
                System.out.println("Review #"+c+"lenght="+s.reviewOrg.length());*/
            c++;
        }

    }


    private static Review splitCSVforReview(String line){
        String[] valueCSV=line.split(",");
        return new Review(valueCSV[9],"not translated");
    }

}
