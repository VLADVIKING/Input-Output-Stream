import java.io.*;

public class Main {
    static Basket basket = new Basket(new String[]{"Молоко", "Кефир", "Сметана", "Хлеб", "Батон"}, new long[]{75, 60, 80, 50, 40});

    public static void main(String[] args) throws IOException {
        File newFile = new File("basket.txt");
        if (newFile.exists()) {
            loadFromTxtFile(newFile);
        }

        basket.addToCart(1, 2);
        basket.saveTxt(newFile);
        basket.addToCart(3, 4);
        basket.saveTxt(newFile);
        basket.addToCart(5, 1);
        basket.saveTxt(newFile);
        basket.addToCart(1, 2);
        basket.saveTxt(newFile);
        basket.addToCart(1, 2);
        basket.saveTxt(newFile);
        basket.printCart();

    }

    public static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader in = new BufferedReader(new FileReader("basket.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                String[] arr = line.split(" ");
                int[] y = new int[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    y[i] = Integer.parseInt(arr[i]);
                }
                basket.setProductQuantity(y);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}

