package Model;

/**
 * Created by Владимир on 10.06.2017.
 */
public class User implements Comparable<User>{
    private String UserId;
    private String ProfileName;
    private int counter=1;

    public User(String userId, String profileName) {
        this.UserId = userId;
        this.ProfileName = profileName;
    }
    public User() {    }



    public void increaseCounterCommentPerUser(){
        this.counter+=1;
    };

    public int getCounter() {
        return counter;
    }

    public String getUserId() {
        return UserId;
    }

    public String getProfileName() {
        return ProfileName;
    }


    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }

    @Override
    public String toString() {
        return "Model.User( Userid:"+UserId+" ProfileName:"+ProfileName+" counter: "+counter+")";
    }

    @Override
    public int compareTo(User user) {
        if (this.counter>user.getCounter())
            return 1;
        else if(this.counter<user.getCounter())
            return -1;
        else
            return 0;
    }
}
