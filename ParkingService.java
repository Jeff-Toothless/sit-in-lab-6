import cs2030s.fp.InfiniteList;
import cs2030s.fp.Maybe;
import java.util.List;

/**
 * Manages a parking history as a chronological list of parking sessions.
 * The methods below use functional style with InfiniteList<T>.
 */
public class ParkingService {
    private final List<ParkingSession> sessions;

    public ParkingService(List<ParkingSession> sessions) {
        this.sessions = sessions;
    }

    /**
     * Returns an infinite list of all parking sessions for the given plate,
     * in chronological order.
     */
    public InfiniteList<ParkingSession> sessionsOf(String plate) {
        return InfiniteList.fromList(this.sessions)
            .filter(x -> x.isFor(plate));
    }

    /**
     * Returns the list of spots visited by the given plate,
     * in chronological order.
     */
    public List<String> spotsVisited(String plate) {
        return InfiniteList.fromList(this.sessions)
            .filter(x -> x.isFor(plate))
            .map(x -> x.spot())
            .toList();
    }

    /** Returns the total parking duration in minutes for the given plate. */
    public int totalDuration(String plate) {
        return InfiniteList.fromList(this.sessions)
            .filter(x -> x.isFor(plate))
            .map(x -> x.duration())
            .reduce(0, (x, y) -> x + y);
    }

    /**
     * Returns the number of times the given spot appears in the sessions
     * of the given plate.
     */
    public long countSpot(String plate, String spot) {
        return InfiniteList.fromList(this.spotsVisited(plate))
            .filter(x -> x.equals(spot))
            .count();
    }

    /**
     * Returns the spot visited most often by the given plate.
     * If there is a tie, returns the spot that appears first in the original
     * order.  Returns an empty string if the plate has no sessions.
     */
    public String mostVisitedSpot(String plate) {
        return InfiniteList.fromList(this.spotsVisited(plate))
            .reduce("", (best, spot) -> Maybe.of(spot)
                .filter(cur -> this.countSpot(plate, cur)
                    > this.countSpot(plate, best))
                .orElse(best));
    }

    @Override
    public String toString() {
        return this.sessions.toString();
    }
}
