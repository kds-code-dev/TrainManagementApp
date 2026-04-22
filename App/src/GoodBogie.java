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

    /**
     * Safely assigns cargo to the bogie with validation
     * @param newCargo The cargo to assign
     * @throws CargoSafetyException if the cargo assignment is unsafe
     */
    public void assignCargo(String newCargo) {
        // Validate cargo-shape compatibility
        if (type.equals("Rectangular") && newCargo.equals("Petroleum")) {
            throw new CargoSafetyException(
                "Error: Petroleum cannot be assigned to Rectangular bogie. " +
                "Rectangular bogies are not designed to carry liquid cargo."
            );
        }
        this.cargo = newCargo;
    }

    @Override
    public String toString() {
        return type + " bogie carrying " + cargo;
    }
}
