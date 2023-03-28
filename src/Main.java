import java.io.*;

public class Main {
    static Basket basket = new Basket(new String[]{"Молоко", "Кефир", "Сметана", "Хлеб", "Батон"}, new long[]{75, 60, 80, 50, 40});

    public static void main(String[] args) throws IOException {
        File newFile = new File("basket.bin");
        if (newFile.exists()) {
            loadFromBinFile(newFile);
        }

        basket.addToCart(1, 2);
        saveBin(newFile);
        basket.addToCart(3, 4);
        saveBin(newFile);
        basket.addToCart(5, 1);
        saveBin(newFile);
        basket.addToCart(1, 2);
        saveBin(newFile);
        basket.addToCart(1, 2);
        saveBin(newFile);
        basket.printCart();
    }

    static void saveBin(File file) {
        try (FileOutputStream fos = new FileOutputStream("basket.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(basket);
        } catch (Exception ex) {
        }
    }

    static Basket loadFromBinFile(File file) {
        try (FileInputStream fis = new FileInputStream("basket.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            basket = (Basket) ois.readObject();
        } catch (Exception ex) {
        }
        return null;
    }
}

