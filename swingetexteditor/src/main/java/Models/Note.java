package Models;

public class Note implements Comparable<Note>{

    private Long time;
    private String text;
    private String name;

    public Note(){

    }

    public Note(Long time, String text, String name) {
        this.time = time;
        this.text = text;
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "{" +
                "time=" + time +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Note o) {
        return this.getTime().compareTo(o.getTime());
    }
}
