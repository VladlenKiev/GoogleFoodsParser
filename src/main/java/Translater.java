import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.common.collect.ImmutableList;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * Created by admin on 13.06.2017.
 */
public class Translater extends Thread{
    final static String KEY = "AIzaSyCwcmikymkeFG-F_tWeXSebP1ANUf4cPEw";
    final static TranslateRequestInitializer KEY_INITIALIZER = new TranslateRequestInitializer(KEY);
    public ArrayList<Review> reviewList;

    public Translater(){}
    public Translater(ArrayList<Review> reviewList){
        this.reviewList = reviewList;
    }

    @Override
    public void run() {
        translateText();
    }

    public void translateText() {



        Translate translate = setHTTPConnect();
        // translate
            for (int i=0;i<=reviewList.size()-1;i++) {
                final ImmutableList<String> phrasesToTranslate = ImmutableList.<String>builder().add(reviewList.get(i).getReviewOrg()).build();
                try {
                    TranslationsListResponse tempTranslate= translate.translations().list(phrasesToTranslate, "fr").execute();
                    reviewList.get(i).setReviewTranslated(String.valueOf(tempTranslate.getTranslations().get(0)));
                    System.out.println("translate #"+i+" = "+tempTranslate.getTranslations().size());
                    //System.out.println(translate.translations().list(phrasesToTranslate, "fr").execute());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        showTranslatedReviews(reviewList);
    }

    public static void showTranslatedReviews(ArrayList<Review> reviewList){
        for (int i=0;i<=reviewList.size()-1;i++) {
            System.out.println("TRANSLATED: "+reviewList.get(i).toString());
        }
    }

    private static Translate setHTTPConnect(){
        // Set up the HTTP transport and JSON factory
        HttpTransport httpTransport = null;
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // set up translate
        final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null)
                .setApplicationName("My Project for Roundforest")
                .setTranslateRequestInitializer(KEY_INITIALIZER)
                .build();
        return translate;
    }

    private static void buildPhrasesForTranslate(ArrayList<String> reviewList){
        ArrayList<String> phrasesList = new ArrayList<>();
        for (int i=0;i<=reviewList.size();i++){
            if (reviewList.get(i).length()<=1000) {
                phrasesList.add(reviewList.get(i));
            }
            if (phrasesList.get(i).length()<=1000){

            }


        }

    }

}
