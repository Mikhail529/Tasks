import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CLS {
    public static void main(String[] args) throws InterruptedException {

        // Task 4. Многопоточность. Порт. Коробли заходят в порт для разгрузки/загрузки контейнеров.
        // Число контейнеров, находящихся в текущий момент в порту и на коробле, должно быть неотрицательным
        // и превышающим заданую грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
        // У одного причала может стоять один коробль. Коробль может загружаться у причала или разгружатся

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<Ship> arrayList = new ArrayList<>();
        createListShip(arrayList);

        Port port = new Port();
        System.out.println("\"" + port.getName() + "\"");
        System.out.println("Port in working...\n");
        for (int i=0; i<arrayList.size(); i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        shipMaintenance(port,arrayList.get(finalI));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        executorService.shutdown();
        boolean bool = executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("-----------------------");
        System.out.println("Cargo in the port: " + port.getCargo());
    }

    public static void createListShip(ArrayList arrayList) {
        for (int i=0; i<100; i++) {
            arrayList.add(new Ship(i+1));
        }
    }

    public static void shipMaintenance(Port port, Ship ship) {
        if (ship.isStatus()) {
            Random random = new Random();
            int loading = 10 + random.nextInt(90);

            if (port.getCargo() >= loading) {
                port.setCargo(port.getCargo()-loading);
                System.out.println("Loading:   " + ship + "(" + loading + "/" + port.getCargo() + ")");
            }
            else if (port.getCargo() < loading) {
                System.out.println("Loading:   "  + ship + "(" + loading + "/" + port.getCargo() + ")" + " Это корабль не смог загрузться!");
            }
        }
        else if (!ship.isStatus()) {
            int unLoading = ship.getCargo();
            port.setCargo(port.getCargo() + unLoading);
            System.out.println("Unloading: " + ship + " (" + unLoading + "/" + port.getCargo() + ")");
            ship.setCargo(0);
        }
    }
}