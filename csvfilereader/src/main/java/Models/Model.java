package Models;

import java.util.ArrayList;

public class Model {

    private String titelYear;
    private String actor;
    private String genre;
    private String rating;
    private String instructor;


    public Model (){

    }

    public Model(String titelYear, String actor, String genre, String rating, String instructor) {
        this.titelYear = titelYear;
        this.actor = actor;
        this.genre = genre;
        this.rating = rating;
        this.instructor = instructor;
    }

    public String getTitelYear() {
        return titelYear;
    }

    public void setTitelYear(String titelYear) {
        this.titelYear = titelYear;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "[" +
                "titelYear='" + titelYear + '\'' +
                ", actor='" + actor + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", instructor='" + instructor + '\'' +
                ']';
    }
}
