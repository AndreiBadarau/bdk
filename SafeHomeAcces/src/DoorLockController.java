import java.util.Map;

public class DoorLockController implements ControllerInterface {

    private Map<Tenant, AccessKey> validAccess;

    public DoorLockController(Map<Tenant, AccessKey> validAccess) {
        this.validAccess = validAccess;
    }

    public int tries = 0;
    private DoorStatus door = DoorStatus.CLOSE;

    @Override
    public DoorStatus enterPin(String pin) throws Exception {
        for (Map.Entry<Tenant, AccessKey> entry : validAccess.entrySet()) {
            Tenant tenant = entry.getKey();
            AccessKey accessKey = entry.getValue();

            if (!pin.equals(accessKey.getPin())) {

                tries++;
                throw new InvalidPinException("Pin is incorrect!");

            } else if (pin.equals(accessKey.getPin())) {

                if (door == DoorStatus.OPEN) {
                    door = DoorStatus.CLOSE;
                } else {
                    System.out.println("Pin is correct!");
                    return door = DoorStatus.OPEN;
                }

            }
            if (tries >= 3) {
                throw new TooManyAttempsException("Too many wrong attempts, master key is needed!" + DoorStatus.CLOSE);
            }
        }
        //validAccess.forEach(k,v) -> ;
        //System.out.println(k + "->" + v);
        return DoorStatus.CLOSE;
    }

    @Override
    public void addTenant(String pin, String name) throws Exception {
        Tenant tenant = new Tenant(name);
        AccessKey accessKey = new AccessKey(pin);

        if (validAccess.containsKey(tenant)) {
            throw new TenantAlreadyExistsException("Tenant already exists: " + name);
        }

        validAccess.put(tenant, accessKey);
    }

    @Override
    public void removeTenant(String name) throws Exception {

    }
}
