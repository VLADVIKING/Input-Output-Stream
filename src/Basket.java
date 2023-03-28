import java.io.*;

public class Basket implements Serializable{

    private String[] products;
    private long[] prices;
    private int[] productQuantity;
    private long totalPrice;

    public Basket(String[] products, long[] prices) {
        this.products = products;
        this.prices = prices;
        this.productQuantity = new int[products.length];
    }

    public int[] getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int[] productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void addToCart(int productNum, int amount) {
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

}


