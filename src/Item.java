public class Item {
    private String name;
    private ItemCategory category;
    private double pricePerKg;

    public Item(String name, ItemCategory category, double pricePerKg) {
        this.name = name;
        this.category = category;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public String toString() {
        return name + " (" + pricePerKg + " บาท/กก.)";
    }
}