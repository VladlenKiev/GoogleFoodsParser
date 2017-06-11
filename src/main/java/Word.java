/**
 * Created by Владимир on 11.06.2017.
 */
public class Word implements Comparable<Word>{

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
        return "Word =["+word+"] counter: "+counter+")";
    }

    @Override
    public int compareTo(Word o) {
        if (this.counter>o.getCounter())
            return 1;
        else if(this.counter<o.getCounter())
            return -1;
        else
            return 0;
    }
}
