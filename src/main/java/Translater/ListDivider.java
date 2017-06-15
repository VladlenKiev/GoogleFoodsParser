package Translater;

import Model.Review;

import java.util.ArrayList;

public class ListDivider {
    private static int basketSize = 50000;

    public static int calculateBucketSize(Integer countThread, Integer listSize) {
        return (listSize / (countThread) + listSize % countThread);
    }

    public static void divideListInfo(ArrayList<Review> reviewList) {
        /*ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i <= 196; i++) {
            list.add(new Object());
        }*/

        ArrayList<ArrayList<Review>> listOfLists = arrayDivide(reviewList);
        System.out.println("ArrayList<ArrayList<Review>>===COUNT OF THREAD RUNNING=" + listOfLists.size());
        for (ArrayList<Review> reviewArrayList : listOfLists)
            System.out.println("Load (reviewList) per 1 thread=" + reviewArrayList.size());

        //System.out.println("listOfLists.get(0).size()="+listOfLists.get(1).size());
    }


    public static ArrayList<ArrayList<Review>> arrayDivide(ArrayList<Review> list) {

        ArrayList<ArrayList<Review>> resultList = new ArrayList<>();

        ArrayList<Review> partList = new ArrayList<>();

        for (int index = 0; index <= list.size() - 1; index++) {
            if ((index % basketSize) == 0) {
                partList = new ArrayList<>();
            }
            partList.add(list.get(index));

            if (partList.size() == basketSize || (index == list.size() - 1)) {
                resultList.add(partList);
            }
        }
        return resultList;
    }

    public static int getBasketSize() {
        return basketSize;
    }

    public static void setBasketSize(int basketSize) {
        ListDivider.basketSize = basketSize;
    }

}