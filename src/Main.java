import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket = new Basket(new String[]{"Молоко", "Кефир", "Сметана", "Хлеб", "Батон"}, new long[]{75, 60, 80, 50, 40});
        File newFile = new File("basket.json");

        basket.addToCart(1, 2);
        basket.saveBasket(newFile);
        basket.addToCart(3, 4);
        basket.saveBasket(newFile);
        basket.addToCart(5, 1);
        basket.saveBasket(newFile);
        basket.addToCart(1, 2);
        basket.saveBasket(newFile);
        basket.addToCart(1, 2);
        basket.saveBasket(newFile);
        basket.printCart();
    }

}

