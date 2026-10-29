package cs2030s.fp;

public class Lazy<T> {
    private Producer<? extends T> prod;
    private T val;
    private boolean comp;

    private Lazy(Producer<? extends T> prod) {
        this(prod, null, false);
    }

    private Lazy(T val) {
        this(() -> val, val, true);
    }

    private Lazy(Producer<? extends T> prod, T val, boolean comp) {
        this.prod = prod;
        this.val = val;
        this.comp = comp;
    }

    public static <T> Lazy<T> of(T val) {
        return new Lazy<>(val);
    }

    public static <T> Lazy<T> of(Producer<? extends T> prod) {
        return new Lazy<>(prod);
    }

    public T get() {
        if (!this.comp) {
            this.val = this.prod.produce();
            this.comp = true;
        }
        return this.val;
    }

    public <R> Lazy<R> map(Transformer<? super T, ? extends R> func) {
        return new Lazy<R>(() -> func.transform(this.get()));
    }

    public <R> Lazy<R> flatMap(
            Transformer<? super T, ? extends Lazy<? extends R>> func) {
        return new Lazy<R>(() -> func.transform(this.get()).get());
    }

    public Lazy<Boolean> filter(BooleanCondition<? super T> pred) {
        return new Lazy<Boolean>(() -> pred.test(this.get()));
    }

    public <R, S> Lazy<S> combine(Lazy<? extends R> lazy,
            Combiner<? super T, ? super R, ? extends S> comb) {
        return new Lazy<S>(() -> comb.combine(this.get(), lazy.get()));
    }

    @Override
    public String toString() {
        if (this.comp) {
            return String.valueOf(this.val);
        }
        return "?";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lazy<?>)) {
            return false;
        }
        Lazy<?> lazy = (Lazy<?>) obj;
        if (lazy.get() == null) {
            return this.get() == null;
        }
        return lazy.get().equals(this.get());
    }
}
