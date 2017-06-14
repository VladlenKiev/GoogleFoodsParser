package Translater;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrey on 14.06.2017.
 */
public class TranslatePackage {
    int MaxSize=1000;
    private ArrayList<Integer> idExpression=new ArrayList<Integer>();

    private String cMessage="";
    private String tMessage;

    public String getcMessage() {
        return cMessage;
    }

    public String[] getcMessages(){
        return cMessage.split("\\.");
    }

    public void setcMessage(String cMessage) {
        this.cMessage = cMessage;
    }
    public void settMessage(String tMessage) {
        this.tMessage = tMessage;
    }

    public ArrayList<Integer> getIdExpression(){
        return idExpression;
    }

    public String[] gettMessages(){
        return tMessage.split("\\.");
    }

    public boolean hasAdd(String expression){
        return ((cMessage.length()+expression.length())<=MaxSize)?true:false;
    }

    public void add(String expression, int index){
        StringBuilder sb=new StringBuilder();
        sb.append(cMessage);
        sb.append(expression);
        sb.append(".");
        idExpression.add(index);
        this.cMessage=sb.toString();
    }

    public void Report(){
        //if(idExpression.size()!=getcMessages().length) {
        System.out.println("------------------------");
        System.out.println("cMessage size: " + cMessage.length());
        System.out.println("idExpression size: " + idExpression.size());
        System.out.println("messages size: " + getcMessages().length);
        System.out.println(Arrays.toString(idExpression.toArray()));
        System.out.println(Arrays.toString(getcMessages()));
        System.out.println(getcMessage());
        System.out.println("------------------------");
        //}
    }
}