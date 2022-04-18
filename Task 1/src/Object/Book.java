package Object;

public class Book {
    private String name;
    private String author;
    private String description;
    private String type;
    private int yearOfPublishing;
    private int numberOfPages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return  "Name:\t\t\t\t" + name +
                "\nAuthor:\t\t\t\t" + author +
                "\nDescription:\t\t" + description +
                "\nType:\t\t\t\t" + type +
                "\nYear of publishing:\t" + yearOfPublishing +
                "\nNumber of pages:\t" + numberOfPages;
    }
}