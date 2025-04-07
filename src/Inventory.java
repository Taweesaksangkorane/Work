import java.util.*;

public class Inventory {
    private Map<Item, Double> stockMap = new HashMap<>();

    public void addItem(Item item, double amountKg) {
        stockMap.put(item, stockMap.getOrDefault(item, 0.0) + amountKg);
    }

    public boolean hasEnough(Item item, double requestedKg) {
        return stockMap.getOrDefault(item, 0.0) >= requestedKg;
    }

    public boolean reduceStock(Item item, double amountKg) {
        if (!hasEnough(item, amountKg)) return false;
        stockMap.put(item, stockMap.get(item) - amountKg);
        return true;
    }

    public double getStock(Item item) {
        return stockMap.getOrDefault(item, 0.0);
    }

    public void printStock() {
        System.out.println("------ สต็อกสินค้า ------");
        for (Map.Entry<Item, Double> entry : stockMap.entrySet()) {
            System.out.printf("%s: %.2f กก.\n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println("-------------------------");
    }
}