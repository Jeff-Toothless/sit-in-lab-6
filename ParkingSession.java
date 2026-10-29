public class ParkingSession {
    private final String plate;
    private final String spot;
    private final int duration;

    public ParkingSession(String plate, String spot, int duration) {
        this.plate = plate;
        this.spot = spot;
        this.duration = duration;
    }

    public String plate() {
        return this.plate;
    }

    public String spot() {
        return this.spot;
    }

    public int duration() {
        return this.duration;
    }

    public boolean isFor(String plate) {
        return this.plate.equals(plate);
    }

    @Override
    public String toString() {
        return this.plate + ":" + this.spot + ":" + this.duration;
    }
}
