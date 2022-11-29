import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public  static void RetrieveXmlData(){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            String path= "Catalogue.xml";
            File f = new File(path);
            Document document = builder.parse(f);
            List<Book> allBooks = new ArrayList<Book>();
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
           // elem.getElementsByTagName("ID").item(0).getChildNodes().item(0).getNodeValue();
                    // Get the value of the ID attribute.
                   String ID = node.getAttributes().getNamedItem("ID").getNodeValue();
                   //node.getAttributes().getNamedItem("ID").getNodeValue();
                    // Get the value of all sub-elements.
                    String author = elem.getElementsByTagName("Author")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    String title = elem.getElementsByTagName("Title")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    String genre = elem.getElementsByTagName("Genre")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    String price = elem.getElementsByTagName("Price")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    String publishDate = elem.getElementsByTagName("Publish_Date")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    String description = elem.getElementsByTagName("Description")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    allBooks.add(new Book(ID, author, title, genre, price, publishDate, description));
                }
            }
            // Print all books
            for (Book b : allBooks) {
                System.out.println(b.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    public static void buildXmlData(int n){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        String path= "Catalogue.xml";
        File f = new File(path);
            try {

                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

        try {
            Document document = builder.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create template

            Document doc = builder.newDocument();
            //create a root node
            Element root = doc.createElement("Catalogue");
            doc.appendChild(root);
            for (int i = 0; i < n; i++) {
                //create a book with its attribute
                Element book = doc.createElement("Book");

                //create ID node and append its value
                Element ID = doc.createElement("ID");
                System.out.println("Enter the Book ID:");
                String idValue = in.next();
                book.setAttribute("ID",idValue);


                //create author node and append its value
                Element author = doc.createElement("Author");
                System.out.println("Enter the author name:");
                String authorValue = in.next();
                Text authorVal = doc.createTextNode(authorValue);
                author.appendChild(authorVal);

                //create a title node and append its value
                Element Title = doc.createElement("Title");
                System.out.println("The book Title: ");
                String TitleValue = in.next();
                Text TitleVal = doc.createTextNode(TitleValue);
                Title.appendChild(TitleVal);

                //create a genre node and append its value
                Element Genre = doc.createElement("Genre");
                System.out.println("The book genre: ");
                String GenreValue = in.next();
                Text GenreVal = doc.createTextNode(GenreValue);
                Genre.appendChild(GenreVal);

                //create a price node and it's value
                Element Price = doc.createElement("Price");
                System.out.println("The book Price:");
                String PriceValue = in.next();
                Text PriceVal = doc.createTextNode(PriceValue);
                Price.appendChild(PriceVal);

                //create a publish date node
                Element publishDate = doc.createElement("Publish_Date");
                System.out.println("The book publishDate: ");
                String publishDateValue = in.next();
                Text publishDateVal = doc.createTextNode(publishDateValue);
                publishDate.appendChild(publishDateVal);

                //create a description node
                Element Description = doc.createElement("Description");
                System.out.println("The Book description: ");
                String DescriptionValue = in.next();
                Text DescriptionVal = doc.createTextNode(DescriptionValue);
                Description.appendChild(DescriptionVal);
                //  apend the attribute to the  father
               // book.appendChild(ID);
                book.appendChild(author);
                book.appendChild(Title);
                book.appendChild(Genre);
                book.appendChild(Price);
                book.appendChild(publishDate);
                book.appendChild(Description);

                //append the father to root
                root.appendChild(book);
                //add root to document

                //write to

            }
            save(doc, path);
        System.out.println("File build successfully..");


    }
    public static void addXMLData(int n){
        String path="Catalogue.xml";
        File file=new File(path);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc;//=// create templete file
        Element root; // =
        try {
            doc=builder.parse(file);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root=doc.getDocumentElement();
        for(int i=0;i<n;i++){
            Element book =doc.createElement("Book"); //child
            root.appendChild(book);

            Element ID = doc.createElement("ID");
            System.out.println("Enter the Book ID:");
            String idValue = in.next();
            book.setAttribute("ID",idValue);

            Element Author = doc.createElement("Author");
            System.out.println("Enter Author name:");
            String authorVal = in.next();
            Author.setTextContent(authorVal);
            book.appendChild(Author);

            Element Title = doc.createElement("Title");
            System.out.println("Enter the Book Title:");
            String TitleValue = in.next();
            Title.setTextContent(TitleValue);
            book.appendChild(Title);

            Element Genre = doc.createElement("Genre");
            System.out.println("Enter the Book Genre:");
            String GenreValue = in.next();
            Genre.setTextContent(GenreValue);
            book.appendChild(Genre);

            Element Price = doc.createElement("Price");
            System.out.println("Enter the Book Price:");
            String PriceValue = in.next();
            Price.setTextContent(PriceValue);
            book.appendChild(Price);

            Element Publish_Date = doc.createElement("Publish_Date");
            System.out.println("Enter the Book Publish date:");
            String PDValue = in.next();
            Publish_Date.setTextContent(PDValue);
            book.appendChild(Publish_Date);


            Element Description = doc.createElement("Description");
            System.out.println("Enter the Book Description:");
            String desc = in.next();
            Description.setTextContent(desc);
            book.appendChild(Description);

            save(doc,path);
            System.out.println("Successful Add new data!");
        }
    }
    public  static  void save(Document document,String path) {
        File f = new File(path);
        DOMSource source = new DOMSource(document);
        //create result stream
        Result result = new StreamResult(f);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        try {
            transformer.transform(source,result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("File Saved successfully....");
    }
    public static void searchByAuthor(String Author ) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String path= "Catalogue.xml";
        File f = new File(path);
        Document document = null;
        try {
            document = builder.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        nodeList = document.getElementsByTagName("Author");
        boolean flag = false;
        if(nodeList.getLength()==0){
            System.out.println("Author not found");
        }
        else {
            for (int i = 0; i < nodeList.getLength(); i++) {
                String value = nodeList.item(i).getTextContent();
                if (value.equalsIgnoreCase(Author)) {
                    flag = true;
                    System.out.println("The content of record: " + nodeList.item(i).getParentNode().getTextContent());
                }
            }
            if(!flag){
                System.out.println("The Author is not found..");
            }
        }
    }
    public static void searchByTitle(String title ) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String path= "Catalogue.xml";
        File f = new File(path);
        Document document = null;
        try {
            document = builder.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        nodeList = document.getElementsByTagName("Title");
        boolean flag = false;
        if(nodeList.getLength()==0){
            System.out.println("Title not found");
        }
        else {
            for (int i = 0; i < nodeList.getLength(); i++) {
                String value = nodeList.item(i).getTextContent();
                if (value.equalsIgnoreCase(title)) {
                    flag = true;
                    System.out.println("The content of record: " + nodeList.item(i).getParentNode().getTextContent());
                }
            }
            if (!flag){
                System.out.println("The title is not found..");
            }
        }
    }
    public static void dropRow(String ID){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String path= "Catalogue.xml";
        File f = new File(path);
        Document document = null;
        try {
            document = builder.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         NodeList nodeList = document.getDocumentElement().getChildNodes();
        boolean flag = false;
        //System.out.println(nodeList);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType()== Node.ELEMENT_NODE){
                Element v =(Element) node;
                String value=node.getAttributes().getNamedItem("ID").getNodeValue();
                if(value.equalsIgnoreCase(ID)) {
                    //Node n = nodeList.item(i).getParentNode();
                    document.getDocumentElement().removeChild(node);
                    flag = true;
                    save(document, path);
                    System.out.println("The record deleted successfully..");
                }
            }

        }
        if (!flag){
            System.out.println("ID record not found.");
        }
    }
    public static void main(String[] args) {
        System.out.println("Hi user, Enter number of books:");

        while (true) {
            System.out.println("1-Build new xml file\n2- Add new data to xml\n3-Search by Author \n4- Search by Title \n5-Delete by ID\n6-Show all data\n7-Exit");
            int num = in.nextInt();
            if (num == 1){
                System.out.println("Enter the size of xml file:");
                int n = in.nextInt();
                buildXmlData(n);
            }
            else if (num == 2) {
                System.out.println("Enter the size of new data:");
                int n = in.nextInt();
                addXMLData(n);
            } else if (num == 3) {
                System.out.println("Enter your favourite author name:");
                String name = in.next();
                searchByAuthor(name);
            } else if (num == 4) {
                System.out.println("Enter your favourite book title:");
                String title = in.next();
                searchByTitle(title);
            } else if (num == 5) {
                System.out.println("Enter the book ID needed to be deleted:");
                String id = in.next();
                dropRow(id);
            }
            else if (num == 6){
                RetrieveXmlData();
            }
            else if (num == 7){
                break;
            }
                else {
                System.out.println("Please choose valid input");
            }

        }
    }
}
