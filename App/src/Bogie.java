public class Bogie {
    private String name;
    private int capacity;
    private String cargo;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.cargo = null;
    }

    public Bogie(String name, String cargo) {
        this.name = name;
        this.capacity = 0; // Not applicable for goods
        this.cargo = cargo;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}