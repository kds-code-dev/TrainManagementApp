public class GoodBogie {
    private String type;      // e.g., "Cylindrical", "Rectangular", "Open", "Box"
    private String cargo;     // e.g., "Petroleum", "Coal", "Grain"

    public GoodBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String getType() {
        return type;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return type + " bogie carrying " + cargo;
    }
}
