package Translater;

import Model.Review;
import Model.TranslatePackage;

import java.util.ArrayList;

/**
 * Created by admin on 14.06.2017.
 */
public class TranslaterMultiThread {
    static int processors = Runtime.getRuntime().availableProcessors();

    public void setCountThread(ArrayList<Review> reviewList) {
        //ListDivider.calculateBucketSize(2,reviewList.size());
        ListDivider.setBasketSize(ListDivider.calculateBucketSize(processors, reviewList.size()));
        ListDivider.divideListInfo(reviewList);

    }

    public ArrayList<ArrayList<Review>> getListReview(ArrayList<Review> reviewList) {
        setCountThread(reviewList);
        return ListDivider.arrayDivide(reviewList);
    }
    /*public ArrayList<ArrayList<Review>> getListTP(ArrayList<TranslatePackage> listTP){
        setCountThread(listTP);
        return ListDivider.arrayDivide(listTP);
    }*/

    public void runMulthiThreadTranslate(ArrayList<ArrayList<Review>> arrayReviewList) {

       /* for (int i=0;i< processors;i++){
           Translater translater0 = new Translater(arrayReviewList.get(i));
        translater0.start();

        try {
            translater0.join();
            //translater1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }*/
    }

}
