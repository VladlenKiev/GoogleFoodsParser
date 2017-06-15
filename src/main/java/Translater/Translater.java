package Translater;

import Model.Review;
import Model.TranslatePackage;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.common.collect.ImmutableList;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * Created by admin on 13.06.2017.
 */
public class Translater extends Thread {
    final static String KEY = readAPIKEY();
    final static TranslateRequestInitializer KEY_INITIALIZER = new TranslateRequestInitializer(KEY);
    public ArrayList<Review> reviewList;
    public ArrayList<TranslatePackage> listTP;

    public Translater() {
    }

    public Translater(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {
        this.reviewList = reviewList;
        this.listTP = listTP;
    }

    @Override
    public void run() {
        translateText();
    }

    public void translateText() {


        Translate translate = setHTTPConnect();
        // translate
        for (int i = 0; i <= listTP.size() - 1; i++) {
            final ImmutableList<String> phrasesToTranslate = ImmutableList.<String>builder().add(listTP.get(i).getAllOriginalTPMessage()).build();
            try {
                TranslationsListResponse tempTranslate = translate.translations().list(phrasesToTranslate, "fr").execute();
                listTP.get(i).setTranslatedTPMessage(String.valueOf(tempTranslate.getTranslations().get(0).getTranslatedText()));
                //System.out.println("translate #"+i+" = "+tempTranslate.getTranslations().size());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buildPhrasesForTranslate(reviewList, listTP);
        showTranslatedReviews(reviewList);
    }

    public static void showTranslatedReviews(ArrayList<Review> reviewList) {
        for (int i = 0; i <= reviewList.size() - 1; i++) {
            System.out.println("TRANSLATED: " + reviewList.get(i).toString());
        }
    }

    private static Translate setHTTPConnect() {
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

    public static void buildPhrasesForTranslate(ArrayList<Review> reviewList, ArrayList<TranslatePackage> listTP) {
        for (int i = 0; i <= listTP.size() - 1; ++i) {


            ArrayList<Integer> idExpressions = listTP.get(i).getIdExpression();
            String[] tMessages = listTP.get(i).getTranslatedTPMessage(); //getTranslatedTPMessage()


            for (int n = 0; n <= tMessages.length - 1; ++n) {
                //insert answer from GOOGLe!
                reviewList.get(idExpressions.get(n)).setReviewTranslated(tMessages[n]);
            }
        }
    }
    public static String readAPIKEY(){
        String pathApi = "./API_KEY.txt";
        //String pathApi = "D:\\JAVA pr\\amazon-fine-food-reviews\\API_KEY.txt";
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
        } catch (IOException e) {
            System.out.println("File with API KEY can not be read! Translate cannot be running!");
        }
        return apiKey;
    }
}
