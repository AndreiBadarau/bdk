import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ControllerInterface ctrl = new DoorLockController(new HashMap<>());
        try {
            ctrl.addTenant("1234","Andrei");
            ctrl.addTenant("1234","Paul");
            ctrl.addTenant("1234","Andrei");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
