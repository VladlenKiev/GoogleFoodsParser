package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 14.06.2017.
 */
public class TranslatePackage {
    int MaxSize = 1000;
    private ArrayList<Integer> idExpression = new ArrayList<Integer>();

    private String originalTPMessage = "";
    private String translatedTPMessage;

    public String getOriginalTPMessage() {
        return originalTPMessage;
    }

    public String[] getAllOriginalTPMessage() {
        return originalTPMessage.split("\\.");
    }

    public void setTranslatedTPMessage(String tpMessage) {
        this.translatedTPMessage = tpMessage;
    }

    public ArrayList<Integer> getIdExpression() {
        return idExpression;
    }

    public String[] getTranslatedTPMessage() {
        return translatedTPMessage.split("\\.");
    }

    public boolean hasAdd(String expression) {
        return ((originalTPMessage.length() + expression.length()) <= MaxSize) ? true : false;
    }

    public void add(String expression, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(originalTPMessage);
        sb.append(expression);
        sb.append(".");
        idExpression.add(index);
        this.originalTPMessage = sb.toString();
    }

    public void Report() {
        //if(idExpression.size()!=getcMessages().length) {
        System.out.println("------------------------");
        System.out.println("TranslatedPackage Message size: " + originalTPMessage.length());
        System.out.println("Count of sentences for translate: " + idExpression.size());
        System.out.println("All Original TranslatedPackage Message size: " + getAllOriginalTPMessage().length);
        System.out.println("Numbers orogonal message in TP for translating=" + Arrays.toString(idExpression.toArray()));
        System.out.println(Arrays.toString(getAllOriginalTPMessage()));
        System.out.println(getOriginalTPMessage());
        System.out.println("------------------------");
        //}
    }

    @Override
    public String toString() {
        return "TranslatePackage{" +
                "Packet Size=" + originalTPMessage.length() +
                ", originalTPMessage='" + originalTPMessage + '\'' +
                ", translatedTPMessage='" + translatedTPMessage + '\'' +
                '}';
    }
}