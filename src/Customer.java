public class Customer {
    private static int idCounter = 1;
    private int id;

    public Customer() {
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "ลูกค้า #" + id;
    }
}