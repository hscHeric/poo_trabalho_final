import java.sql.Date;

public class Post {
    String title; 
    Account user;
    String content;
    Date date;

    public Post(Account user, String content, String title){
        this.title = title;
        this.user = user;
        this.content = content;
        this.date =  new Date(System.currentTimeMillis());
    }

    public Post(Account user, String content, String title, String date){
        this.title = title;
        this.user = user;
        this.content = content;
        this.date = Date.valueOf(date);
    }

    public Account getUser() {
        return this.user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getContent() {
        return this.content;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }
}
