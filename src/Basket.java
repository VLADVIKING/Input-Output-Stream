import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Basket {

    private String[] products;
    private long[] prices;
    private int[] productQuantity;
    private long totalPrice;
    ClientLog clientLog = new ClientLog();

    public Basket(String[] products, long[] prices) {
        this.products = products;
        this.prices = prices;
        this.productQuantity = new int[products.length];
    }

    public void addToCart(int productNum, int amount) throws IOException {
        if (Main.checkLog()) {
            clientLog.log(productNum, amount);
        }
        for (int i = 0; i < products.length; i++) {
            if (i == productNum - 1) {
                productQuantity[i] += amount;
            }
        }
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            if (productQuantity[i] > 0) {
                System.out.printf("%s %d шт. %d руб./шт. %d руб. в сумме \n", products[i], productQuantity[i], prices[i], prices[i] * productQuantity[i]);
                totalPrice += prices[i] * productQuantity[i];
            }
        }
        System.out.printf("Итого: \n" +
                "%d руб." + "\n", totalPrice);
    }

    public void saveBasketJsn(File textFile) {
        JSONObject obj = new JSONObject();
        JSONArray listProducts = new JSONArray();
        JSONArray listQuantity = new JSONArray();
        for (int i = 0; i < products.length; i++) {
            listQuantity.add(productQuantity[i]);
            obj.put("Количество", listQuantity);
            listProducts.add(products[i]);
            obj.put("Товар", listProducts);
        }
        try (FileWriter file = new FileWriter(textFile)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBasketJsn(File textFile) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(textFile));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray savedProducts = (JSONArray) jsonObject.get("Товар");
            JSONArray savedProductQuantity = (JSONArray) jsonObject.get("Количество");
            for (int i = 0; i < savedProducts.size(); i++) {
                products[i] = savedProducts.get(i).toString();
                productQuantity[i] = Integer.parseInt(savedProductQuantity.get(i).toString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void saveBasketTxt(File textFile) {
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (String prs : products) {
                out.print(prs + " ");
            }
            for (int pQ : productQuantity) {
                out.print(pQ + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBasketTxt(File textFile) {
        try (BufferedReader in = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] arr = line.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    if (!isDigit(arr[i])) {
                        products[i] = arr[i];
                        continue;
                    }
                    productQuantity[i - products.length] = Integer.parseInt(arr[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


