import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Basket {

    private String[] products;
    private long[] prices;
    private int[] productQuantity;
    private long totalPrice;

    public Basket(String[] products, long[] prices) {
        this.products = products;
        this.prices = prices;
        this.productQuantity = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        ClientLog clientLog = new ClientLog();
        clientLog.log(productNum, amount);
        totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            if (i == productNum - 1) {
                productQuantity[i] += amount;
            }
            totalPrice += prices[i] * productQuantity[i];
        }
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (productQuantity[i] > 0) {
                System.out.printf("%s %d шт. %d руб./шт. %d руб. в сумме \n", products[i], productQuantity[i], prices[i], prices[i] * productQuantity[i]);
            }
        }
        System.out.printf("Итого: \n" +
                "%d руб.", totalPrice);
    }

    public void saveBasket(File textFile) {
        JSONObject obj = new JSONObject();
        JSONArray listProducts = new JSONArray();
        JSONArray listQuantity = new JSONArray();
        JSONArray listPrices = new JSONArray();
        JSONArray listProductPrices = new JSONArray();
        for (int i = 0; i < products.length; i++) {
            if (productQuantity[i] > 0) {
                listProducts.add(products[i]);
                obj.put("Товар", listProducts);

            }
            if (productQuantity[i] > 0) {
                listQuantity.add(productQuantity[i]);
                obj.put("Количество", listQuantity);

            }
            if (productQuantity[i] > 0) {
                listPrices.add(prices[i]);
                obj.put("Цена за ед.", listPrices);

            }
            if (productQuantity[i] > 0) {
                listProductPrices.add(prices[i] * productQuantity[i]);
                obj.put("Цена за товар", listProductPrices);

            }

        }
        try (FileWriter file = new FileWriter(textFile)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


