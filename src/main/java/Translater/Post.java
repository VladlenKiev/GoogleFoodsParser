package Translater;

/**
 * Created by Andrey on 14.06.2017.
 */
public class Post {
    private String cMessage;
    private String tMessage="";

    public Post(String message){
        this.cMessage=message;
    }

    public String getcMessage() {
        return cMessage;
    }

    public String gettMessage() {
        return tMessage;
    }

    public void settMessage(String expression) {
        StringBuilder sb=new StringBuilder();
        sb.append(tMessage);
        sb.append(expression);
        sb.append(".");
        this.tMessage=sb.toString();
    }

    public void report(){
        System.out.println("---------------");
        System.out.println("cMessage: "+cMessage);
        System.out.println("tMessage: "+tMessage);
        System.out.println("---------------");
    }
}