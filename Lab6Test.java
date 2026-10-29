import java.util.List;

public class Lab6Test {
    public static void main(String[] args) {
        List<ParkingSession> sessions = List.of(
            new ParkingSession("SGX 1234", "A01", 60),
            new ParkingSession("SGX 1234", "A01", 30),
            new ParkingSession("XYZ 5678", "B02", 45),
            new ParkingSession("SGX 1234", "A03", 90),
            new ParkingSession("SGX 1234", "A03", 60),
            new ParkingSession("SGX 1234", "A01", 45),
            new ParkingSession("XYZ 5678", "B02", 30)
        );
        ParkingService p = new ParkingService(sessions);

        check(p.spotsVisited("SGX 1234").equals(
            List.of("A01", "A01", "A03", "A03", "A01")));
        check(p.totalDuration("SGX 1234") == 285);
        check(p.countSpot("SGX 1234", "A01") == 3);
        check(p.countSpot("SGX 1234", "A03") == 2);
        check(p.countSpot("XYZ 5678", "B02") == 2);
        check(p.mostVisitedSpot("SGX 1234").equals("A01"));
        check(p.mostVisitedSpot("XYZ 5678").equals("B02"));
        check(p.mostVisitedSpot("ZZZ 0000").equals(""));
        System.out.println("All Lab 6 tests passed.");
    }

    private static void check(boolean condition) {
        if (!condition) {
            throw new AssertionError("Lab 6 check failed");
        }
    }
}
