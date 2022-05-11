package notebook;

public class Note {
    private String theme;
    private String dateOfCreation;
    private String email;
    private String message;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  "\nTheme:\t\t\t\t" + theme +
                "\nDate of creation:\t" + dateOfCreation +
                "\ne-mail:\t\t\t\t" + email +
                "\nMessage:\t\t\t" + message;
    }
}