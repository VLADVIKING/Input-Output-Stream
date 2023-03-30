import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> loadConfig = new ArrayList<>();
    static List<String> saveConfig = new ArrayList<>();
    static List<String> logConfig = new ArrayList<>();
    static Basket basket = new Basket(new String[]{"Молоко", "Кефир", "Сметана", "Хлеб", "Батон"}, new long[]{75, 60, 80, 50, 40});

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        File newFile;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Node root = doc.getDocumentElement();
        readShopConfig(root);

        newFile = new File(loadConfig.get(1));
        checkLoad(newFile);

        newFile = new File(saveConfig.get(1));
        basket.addToCart(1, 2);
        checkSave(newFile);
        basket.addToCart(3, 4);
        checkSave(newFile);
        basket.addToCart(5, 1);
        checkSave(newFile);
        basket.addToCart(1, 2);
        checkSave(newFile);
        basket.addToCart(1, 2);
        checkSave(newFile);
        basket.printCart();
    }

    public static void readShopConfig(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                String parentNode = node_.getParentNode().getNodeName();
                if (node_.getNodeName().equals("enabled") && parentNode.equals("load")) {
                    loadConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("fileName") && parentNode.equals("load")) {
                    loadConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("format") && parentNode.equals("load")) {
                    loadConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("enabled") && parentNode.equals("save")) {
                    saveConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("fileName") && parentNode.equals("save")) {
                    saveConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("format") && parentNode.equals("save")) {
                    saveConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("enabled") && parentNode.equals("log")) {
                    logConfig.add(node_.getTextContent());
                }
                if (node_.getNodeName().equals("fileName") && parentNode.equals("log")) {
                    logConfig.add(node_.getTextContent());
                }
            }
            readShopConfig(node_);
        }
    }

    public static void checkLoad(File textFile) {
        if (loadConfig.get(0).equals("true")) {
            if (loadConfig.get(2).equals("json")) {
                basket.loadBasketJsn(textFile);
            }
            if (loadConfig.get(2).equals("text")) {
                basket.loadBasketTxt(textFile);
            }
            basket.printCart();
        }
    }

    public static void checkSave(File textFile) {
        if (saveConfig.get(0).equals("true")) {
            if (saveConfig.get(2).equals("json")) {
                basket.saveBasketJsn(textFile);
            }
            if (saveConfig.get(2).equals("text")) {
                basket.saveBasketTxt(textFile);
            }
        }
    }

    public static boolean checkLog() {
        if (logConfig.get(0).equals("true")) {
            return true;
        }
        return false;
    }

}






