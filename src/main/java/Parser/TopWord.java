package Parser;


import Model.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by admin on 12.06.2017.
 */
public class TopWord extends Thread {
    public Word word;
    public HashMap<String, Word> wordCountMap;

    public TopWord() {
    }

    public TopWord(HashMap<String, Word> wordCountMap) {
        this.wordCountMap = wordCountMap;
    }

    @Override
    public void run() {
        wordParserThread(wordCountMap);
    }

    public void wordParserThread(HashMap<String, Word> wordCountMap) {
        String path = "D:\\JAVA pr\\amazon-fine-food-reviews\\Reviews.csv";
        BufferedReader br = null;
        String line = null;

        try {

            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                Word word[] = splitCSVforWord(line); //new Model.Product();
                assumeCountWordPerComments(word, wordCountMap);
            }

            findMostActiveWord(wordCountMap);

        } catch (FileNotFoundException e) {
            System.out.println("File with Review is not found! Parsing is not running!");
        } catch (IOException e) {
            System.out.println("File with Review can not be read! Parsing cannot being running!");
        }
    }

    private static Word[] splitCSVforWord(String line) {
        String[] valueCSV = line.split(",");
        String[] words = valueCSV[9].split(" ");
        Word word[] = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            word[i] = new Word(words[i]);
        }

        return word;
    }

    private static void findMostActiveWord(HashMap<String, Word> wordCountMap) {
        SortedSet<Word> wordsSortSet = new TreeSet<>(Collections.reverseOrder());
        wordsSortSet.addAll(wordCountMap.values());

        int c = 0;
        for (Word w : wordsSortSet) {
            if (c == 1000)
                break;
            c++;
            System.out.println(c + ". " + w.toString());
            c++;
        }
    }

    public static void assumeCountWordPerComments(Word word[], HashMap<String, Word> wordCountMap) {
        for (Word w : word) {
            if (isDigitalAndSpecSymbol(w))
                continue;
            if (wordCountMap.containsKey(normalizeWord(w).getWord())) {
                wordCountMap.get(normalizeWord(w).getWord()).increaseCounterWord();
            } else {
                wordCountMap.put(normalizeWord(w).getWord(), w);
            }
        }
    }

    private static boolean isDigitalAndSpecSymbol(Word word) {
        if (word.getWord().equals("") || word.getWord().contentEquals("-") || word.getWord().contentEquals(",") || word.getWord().contentEquals(" ") ||
                word.getWord().equals("\"") || word.getWord().contentEquals("\"\"") ||
                word.getWord().equals("(") || word.getWord().contentEquals(")") ||
                word.getWord().length() == 1 || word.getWord().matches("\\d+")) {
            return true;
        }
        return false;
    }

    private static Word normalizeWord(Word word) {
        return new Word(word.getWord().replace('(', ' ').replace(')', ' ').replace(',', ' ').replace('"', ' ').replace('|', ' ')
                .replace(':', ' ').replace('-', ' ').replace(';', ' ').replace('�', ' ').replace('•', ' ').replace('”', ' ')
                .replace('.', ' ').replace('\"', ' ').trim().toLowerCase());
    }
}
