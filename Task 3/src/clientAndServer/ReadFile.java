package clientAndServer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";

    public void start() {
        Root root = new Root();
        Document document;

        try {
            document = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error!" + e.toString());
            return;
        }

        Node rootNode = document.getFirstChild();
        NodeList rootChild = rootNode.getChildNodes();
        Node peopleNode = null;

        for (int i=0; i<rootChild.getLength(); i++) {
            if (rootChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (TAG_PEOPLE.equals(rootChild.item(i).getNodeName())) {
                peopleNode = rootChild.item(i);
            }
        }

        if (peopleNode == null) { return; }

        List<People> peopleList = parsPeople(peopleNode);

        root.setPeople(peopleList);

        root.getPeople().stream().filter(people -> {
            return people.getAge() == 25;
        }).forEach(System.out::println);

//        System.out.println(root.getPeople().toString());
    }

    private static Document buildDocument() throws Exception {
        File file = new File("student.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    private static List<People> parsPeople(Node peopleNode) {
        List<People> peopleList = new ArrayList<>();
        NodeList peopleChild = peopleNode.getChildNodes();
        for (int i=0; i<peopleChild.getLength(); i++) {

            if (peopleChild.item(i).getNodeType() != Node.ELEMENT_NODE) { continue; }

            if (!peopleChild.item(i).getNodeName().equals(TAG_ELEMENT)) { continue; }

            String name = "";
            int age = 0;

            NodeList elementChild = peopleChild.item(i).getChildNodes();

            for (int j=0; j<elementChild.getLength(); j++) {

                if (elementChild.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementChild.item(j).getNodeName()) {
                    case TAG_NAME: {
                        name = elementChild.item(j).getTextContent();
                        break;
                    }
                    case TAG_AGE: {
                        age = Integer.parseInt(elementChild.item(j).getTextContent());
                        break;
                    }
                }
            }

            People people = new People(name, age);
            peopleList.add(people);
        }

        return peopleList;
    }
}