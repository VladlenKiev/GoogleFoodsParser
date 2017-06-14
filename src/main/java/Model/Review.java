package Model;

/**
 * Created by admin on 13.06.2017.
 */
public class Review{
    public String reviewOrg;
    public String reviewTranslated;
    //public ArrayList<String> reviewList;
    public Review(){}
    public Review(String reviewOrg, String reviewTranslated){
        this.reviewOrg = reviewOrg;
        this.reviewTranslated = reviewTranslated;
    }

    public String getReviewOrg() {
        return reviewOrg;
    }

    public void setReviewOrg(String reviewOrg) {
        this.reviewOrg = reviewOrg;
    }

    public String getReviewTranslated() {
        return reviewTranslated;
    }

    public void setReviewTranslated(String reviewTranslated) {
        this.reviewTranslated = reviewTranslated;
    }

    @Override
    public String toString() {
        return "Model.Review{" +
                "Original Model.Review='" + reviewOrg + '\'' +
                ", Translated Model.Review='" + reviewTranslated + '\'' +
                '}';
    }
}
