public class Receipt {
    public static void print(Order order) {
        System.out.println("------ ใบเสร็จร้านหมาล่า ------");
        for (Map.Entry<Item, Double> entry : order.getItems().entrySet()) {
            Item item = entry.getKey();
            double weight = entry.getValue();
            double price = item.getPricePerKg() * weight;
            System.out.printf("%s - %.2f กก. = %.2f บาท\n", item.getName(), weight, price);
        }
        System.out.println("--------------------------------");
        System.out.printf("รวมทั้งสิ้น: %.2f บาท\n", order.calculateTotal());
    }
}