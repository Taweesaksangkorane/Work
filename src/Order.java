import java.util.*;

public class Order {
    private Map<Item, Double> items = new HashMap<>();

    public void addItem(Item item, double weightKg) {
        items.put(item, items.getOrDefault(item, 0.0) + weightKg);
    }

    public double calculateTotal() {
        return items.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPricePerKg() * entry.getValue())
            .sum();
    }

    public Map<Item, Double> getItems() {
        return items;
    }
}