package cs2030s.fp;

@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T value);
}
