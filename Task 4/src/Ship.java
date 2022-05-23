import java.util.Random;

public class Ship {
    private int number;
    private int cargo;
    private int capacityCargo;
    private boolean status;

    public Ship(int number) {
        Random random = new Random();
        this.number = number;
        this.status = random.nextBoolean();
        this.cargo = setCargoTwoMethod(random);
        this.capacityCargo = 100;
    }

    private int setCargoTwoMethod(Random random) {
        boolean bool = isStatus();
        int temp = 0;
        if (bool)
            temp = 0;
        else if (!bool)
            temp = 10 + random.nextInt(90);
        return temp;
    }

    private int getNumber() {
        return number;
    }
    private void setNumber(int number) {
        this.number = number;
    }

    public int getCargo() {
        return cargo;
    }
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    private int getCapacityCargo() {
        return capacityCargo;
    }
    private void setCapacityCargo(int capacityCargo) {
        this.capacityCargo = capacityCargo;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "Ship: " + number + " " +
                "cargo: " + cargo + " " +
                "capacity: " + capacityCargo + " " +
                "status: " + status;
    }
}