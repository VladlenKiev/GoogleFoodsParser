package Parser;

import Model.Review;
import Model.TranslatePackage;

import java.util.ArrayList;

/**
 * Created by admin on 13.06.2017.
 */
public class MakerTranslatePackage extends Thread {
    public ArrayList<Review> reviewList;
    public ArrayList<TranslatePackage> listTP;

    public MakerTranslatePackage() {
    }

    public MakerTranslatePackage(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {
        this.reviewList = reviewList;
        this.listTP = listTP;
    }

    @Override
    public void run() {
        reviewParserThread(reviewList, listTP);
    }

    public void reviewParserThread(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {

        makeTranslatedPackage(reviewList, listTP);

        //System.out.println("list All Original Reviews size: " + reviewList.size());
        //System.out.println("list Translated Package for API size: " + listTP.size());
        //System.out.println("before fill up listReview ");

        //iteratorReview(reviewList);
    }

    public static void getTranslatedResult(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {
        for (int i = 0; i <= listTP.size() - 1; ++i) {


            ArrayList<Integer> idExpressions = listTP.get(i).getIdExpression();
            String[] tMessages = listTP.get(i).getAllOriginalTPMessage(); //getTranslatedTPMessage()


            for (int n = 0; n <= tMessages.length - 1; ++n) {
                //insert answer from GOOGLe!
                reviewList.get(idExpressions.get(n)).setReviewTranslated(tMessages[n]);
            }
        }
    }

    public static void makeTranslatedPackage(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {
        TranslatePackage tp = null;

        for (int i = 0; i <= reviewList.size() - 1; ++i) {

            String[] sentenceAray = spliterator(reviewList.get(i).getReviewOrg());


            for (int n = 0; n <= sentenceAray.length - 1; ++n) {

                if (tp == null || !(tp.hasAdd(sentenceAray[n]))) {
                    if (tp == null) {
                        tp = new TranslatePackage();
                    } else {
                        listTP.add(tp);
                        tp = new TranslatePackage();
                    }
                }

                if (tp.hasAdd(sentenceAray[n]))
                    tp.add(sentenceAray[n], i);
            }
        }
    }

    public static void iteratorReview(ArrayList<Review> reviewList) {
        int c = 0;
        for (Review s : reviewList) {
            System.out.println("Model.Review #" + c + s.toString());
            c++;
        }
    }

    private static String[] spliterator(String line) {
        return line.split("[.|\\?|\\!]+");
    }
}
