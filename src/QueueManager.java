import java.util.*;

public class QueueManager {
    private final int MAX_SEATS = 10;
    private List<Customer> seatedCustomers = new ArrayList<>();
    private Queue<Customer> waitingQueue = new LinkedList<>();

    public void arrive(Customer customer) {
        if (seatedCustomers.size() < MAX_SEATS) {
            seatedCustomers.add(customer);
            System.out.println(customer + " ได้นั่งทันที");
        } else {
            waitingQueue.offer(customer);
            System.out.println(customer + " ต้องรอคิว (คิวที่ " + waitingQueue.size() + ")");
        }
    }

    public void leave(Customer customer) {
        if (seatedCustomers.remove(customer)) {
            System.out.println(customer + " ลุกจากเก้าอี้");
            if (!waitingQueue.isEmpty()) {
                Customer next = waitingQueue.poll();
                seatedCustomers.add(next);
                System.out.println(next + " ได้เข้าไปนั่งแทน");
            }
        }
    }

    public void showStatus() {
        System.out.println("------ สถานะร้าน ------");
        System.out.println("ลูกค้าที่นั่งอยู่:");
        for (Customer c : seatedCustomers) {
            System.out.println("- " + c);
        }
        System.out.println("คิวที่รอ:");
        for (Customer c : waitingQueue) {
            System.out.println("- " + c);
        }
        System.out.println("------------------------");
    }
}