package Translater;

import Translater.Post;
import Translater.TranslatePackage;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.*;

/**
 * Created by Andrey on 09.06.2017.
 */
public class EnterPoint {
    public static void main(String[] args) {
        String path = "C:\\downloads\\amazon-fine-food-reviews\\Reviews.csv";
        BufferedReader br = null;
        String line=null;


        ArrayList<Post> listPosts=new ArrayList<Post>();
        ArrayList<TranslatePackage> listTP=new ArrayList<TranslatePackage>();
        try {

            br=new BufferedReader(new FileReader(path));

            while ((line=br.readLine())!=null)
                listPosts.add(postSplitter(line));


            /*for(int i=0;i<=100;++i){
                System.out.println(i+" "+listPosts.get(i).getcMessage());
            }*/

            TranslatePackage tp=null;

            for(int i=0;i<=listPosts.size()-1;++i){

                String[]  sentenceAray=spliterator(listPosts.get(i).getcMessage());


                for(int n=0;n<=sentenceAray.length-1;++n){

                    if(tp==null||!(tp.hasAdd(sentenceAray[n]))) {
                        if(tp==null) {
                            tp = new TranslatePackage();
                        } else {
                            listTP.add(tp);
                            tp = new TranslatePackage();
                        }
                    }

                    if(tp.hasAdd(sentenceAray[n]))
                        tp.add(sentenceAray[n],i);

                }

            }

            System.out.println("list posts size: "+listPosts.size());
            System.out.println("list tp size: "+listTP.size());


/*
            for(int i=0;i<100;++i){
                listTP.get(i).Report();
            }*/

            System.out.println("before feel lists post ");

            for(int i=0;i<=listTP.size()-1;++i){


                ArrayList<Integer> idExpressions=listTP.get(i).getIdExpression();
                String[] tMessages=listTP.get(i).getcMessages();


                for(int n=0;n<=tMessages.length-1;++n){
                    listPosts.get(idExpressions.get(n)).settMessage(tMessages[n]);
                }
            }

            for(int i=0;i<=1000;++i){
                listPosts.get(i).report();
            }

            //------------------------


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Post postSplitter(String line){
        String[] l=line.split(",");
        return new Post(l[8]);
    }

    private static String[] spliterator(String line){
        return line.split("[.|\\?|\\!]+");
    }
}