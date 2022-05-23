public class Port {
    private String name;
    private int cargo;
    private int capacityPort;

    public Port() {
        this.name = "Port of Long Beach";
        this.cargo = 500;
        this.capacityPort = 10000;
    }

    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }

    public int getCargo() {
        return cargo;
    }
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getCapacityPort() {
        return capacityPort;
    }
    private void setCapacityPort(int capacityPort) {
        this.capacityPort = capacityPort;
    }

    @Override
    public String toString() {
        return  "Port:     " + name + "\n" +
                "cargo:    " + cargo + "\n" +
                "capacity: " + capacityPort + "\n";
    }
}