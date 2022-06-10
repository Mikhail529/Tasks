package clientAndServer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Archive {
    public Archive() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("root");
        Element people = document.createElement("people");
        Element element = document.createElement("element");
        Element name = document.createElement("name");
        Element age = document.createElement("age");
        Text text1 = document.createTextNode("Mike");
        Text text2 = document.createTextNode("25");

        document.appendChild(root);
        root.appendChild(people);
        people.appendChild(element);
        element.appendChild(name);
        element.appendChild(age);
        name.appendChild(text1);
        age.appendChild(text2);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("student.xml")));
    }
}