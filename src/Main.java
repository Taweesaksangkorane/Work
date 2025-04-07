import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueueManager queueManager = new QueueManager();
        Inventory inventory = new Inventory();
        List<Item> menu = List.of(
            new Item("ลูกชิ้นหมู", ItemCategory.MEAT, 150),
            new Item("เบคอน", ItemCategory.MEAT, 200),
            new Item("เห็ดเข็มทอง", ItemCategory.VEGGIE, 100),
            new Item("กะหล่ำปลี", ItemCategory.VEGGIE, 80),
            new Item("ปลาหมึก", ItemCategory.SEAFOOD, 180)
        );

        for (Item item : menu) {
            inventory.addItem(item, 10); // เติมเริ่มต้น 10 กก. ทุกอย่าง
        }

        List<Customer> seatedCustomers = new ArrayList<>();

        while (true) {
            System.out.println("\n---- เมนูร้านหมาล่า ----");
            System.out.println("1. ลูกค้าใหม่เข้าร้าน");
            System.out.println("2. แสดงสถานะเก้าอี้และคิว");
            System.out.println("3. คิดเงินลูกค้าและให้ลุกจากเก้าอี้");
            System.out.println("4. แสดงสต็อก");
            System.out.println("5. เติมสต็อก");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.print("เลือก: ");
            int option = sc.nextInt();

            if (option == 0) break;

            switch (option) {
                case 1 -> {
                    Customer customer = new Customer();
                    queueManager.arrive(customer);

                    if (seatedCustomers.size() < 10) {
                        Order order = new Order();
                        while (true) {
                            System.out.println("เลือกวัตถุดิบที่ต้องการ (0 = พอแล้ว):");
                            for (int i = 0; i < menu.size(); i++) {
                                System.out.println((i + 1) + ". " + menu.get(i));
                            }
                            int choice = sc.nextInt();
                            if (choice == 0) break;
                            if (choice < 1 || choice > menu.size()) continue;

                            Item selected = menu.get(choice - 1);
                            System.out.print("น้ำหนัก (กิโลกรัม): ");
                            double weight = sc.nextDouble();

                            if (inventory.hasEnough(selected, weight)) {
                                inventory.reduceStock(selected, weight);
                                order.addItem(selected, weight);
                            } else {
                                System.out.println("❌ สินค้าไม่พอในสต็อก (คงเหลือ: " +
                                    String.format("%.2f", inventory.getStock(selected)) + " กก.)");
                            }
                        }
                        Receipt.print(order);
                        seatedCustomers.add(customer);
                    }
                }

                case 2 -> queueManager.showStatus();

                case 3 -> {
                    System.out.print("กรอกหมายเลขลูกค้าที่จะลุก (#): ");
                    int id = sc.nextInt();
                    Customer found = null;
                    for (Customer c : seatedCustomers) {
                        if (c.getId() == id) {
                            found = c;
                            break;
                        }
                    }
                    if (found != null) {
                        queueManager.leave(found);
                        seatedCustomers.remove(found);
                    } else {
                        System.out.println("ไม่พบลูกค้าที่ระบุ");
                    }
                }

                case 4 -> inventory.printStock();

                case 5 -> {
                    for (int i = 0; i < menu.size(); i++) {
                        System.out.println((i + 1) + ". " + menu.get(i).getName());
                    }
                    System.out.print("เลือกสินค้าที่จะเติม: ");
                    int itemIdx = sc.nextInt() - 1;
                    if (itemIdx < 0 || itemIdx >= menu.size()) {
                        System.out.println("เลือกผิด");
                        break;
                    }
                    System.out.print("กรอกปริมาณที่เติม (กก.): ");
                    double addAmount = sc.nextDouble();
                    inventory.addItem(menu.get(itemIdx), addAmount);
                    System.out.println("เติมเรียบร้อย");
                }

                default -> System.out.println("เมนูไม่ถูกต้อง");
            }
        }

        sc.close();
    }
}