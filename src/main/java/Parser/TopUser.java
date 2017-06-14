package Parser;

import Model.User;

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
public class TopUser extends Thread {
    public User user;
    public HashMap<String, User> userCountMap;

    public TopUser() {
    }

    public TopUser(HashMap<String, User> userCountMap) {
        this.userCountMap = userCountMap;
    }

    @Override
    public void run() {
        userParserThread(userCountMap);
    }

    public void userParserThread(HashMap<String, User> userCountMap) {
        String path = "D:\\JAVA pr\\amazon-fine-food-reviews\\Reviews.csv";
        BufferedReader br = null;
        String line = null;

        try {

            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {

                User user = splitCSVforUser(line);
                assumeCountReviewsPerUser(user, userCountMap);

            }

            findMostActiveUsers(userCountMap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static User splitCSVforUser(String line) {
        String[] valueCSV = line.split(",");
        return new User(valueCSV[2], valueCSV[3]);
    }


    public static void findMostActiveUsers(HashMap<String, User> userCountMap) {
        SortedSet<User> usersSortSet = new TreeSet<>(Collections.reverseOrder());
        usersSortSet.addAll(userCountMap.values());
        System.out.println(userCountMap.size());
        System.out.println(usersSortSet.size());

        int c = 0;
        for (User u : usersSortSet) {
            if (c == usersSortSet.size() - 1) {
                for (User uMap : userCountMap.values()) {
                    if (uMap.getCounter() == 1)
                        System.out.println(c + " ) " + uMap.toString());
                    if (c == 500)
                        break;
                    c++;
                }
            }
            System.out.println(c + ". " + u.toString());
            c++;
        }
    }

    public static void assumeCountReviewsPerUser(User user, HashMap<String, User> userCountMap) {
        if (userCountMap.containsKey(user.getUserId())) {
            userCountMap.get(user.getUserId()).increaseCounterCommentPerUser();
        } else {
            userCountMap.put(user.getUserId(), user);
        }

    }
}
