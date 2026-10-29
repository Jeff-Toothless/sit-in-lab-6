package cs2030s.fp;

@FunctionalInterface
public interface Combiner<S, T, R> {
    R combine(S first, T second);
}
