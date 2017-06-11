/**
 * Created by Владимир on 11.06.2017.
 */
public class Word {

    private String word;
    private int counter=1;

    public Word(String word) {
        this.word = word;
    }

    public Word() {    }

    public void increaseCounterWord(){
        this.counter+=1;
    };

    public int getCounter() {
        return counter;
    }

    public String getWord() {
        return word;
    }


    public void setWord(String word) {
        this.word = word;
    }


    @Override
    public String toString() {
        return "Word =["+word+" counter: "+counter+")";
    }

}
