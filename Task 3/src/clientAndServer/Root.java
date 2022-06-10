package clientAndServer;

import java.util.List;

public class Root {
    private List<People> people;

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Root: " +
                "people: " + people;
    }
}