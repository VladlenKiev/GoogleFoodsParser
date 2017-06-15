package Translater;

import Model.Review;
import Model.TranslatePackage;
import Parser.MakerTranslatePackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 13.06.2017.
 */
public class MakerTranslatedPackageMultiThread extends Thread {

    static int processors = Runtime.getRuntime().availableProcessors();

    public void setCountThread(ArrayList<Review> reviewList) {
        //ListDivider.calculateBucketSize(2,reviewList.size());
        ListDivider.setBasketSize(ListDivider.calculateBucketSize(processors, reviewList.size()));
        ListDivider.divideListInfo(reviewList);
    }

    public ArrayList<ArrayList<Review>> getListReview(ArrayList<Review> reviewList) {
        //System.out.println("reviewList.size()="+reviewList.size());
        setCountThread(reviewList);
        return ListDivider.arrayDivide(reviewList);
    }

    public void runMultiThreadTranslatedPackage(ArrayList<ArrayList<Review>> arrayReviewList) {

        for (int i = 0; i < processors; i++) {
            ArrayList<TranslatePackage> listTPthread = new ArrayList<TranslatePackage>();
            MakerTranslatePackage makerTranslatePackage = new MakerTranslatePackage(arrayReviewList.get(i), listTPthread);
            makerTranslatePackage.start();

            try {
                makerTranslatePackage.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Translater translaterThread = new Translater(arrayReviewList.get(i), listTPthread);
            translaterThread.start();
            try {
                translaterThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
