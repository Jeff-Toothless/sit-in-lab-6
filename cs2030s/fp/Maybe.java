package cs2030s.fp;

public abstract class Maybe<T> {
    private Maybe() {
    }

    private static final None NONE = new None();

    public static <T> Maybe<T> none() {
        @SuppressWarnings("unchecked")
        Maybe<T> result = (Maybe<T>) NONE;
        return result;
    }

    public static <T> Maybe<T> some(T value) {
        return new Some<>(value);
    }

    public static <T> Maybe<T> of(T value) {
        return value == null ? Maybe.none() : Maybe.some(value);
    }

    public abstract Maybe<T> filter(BooleanCondition<? super T> pred);

    public abstract <U> Maybe<U> map(
            Transformer<? super T, ? extends U> func);

    public abstract <U> Maybe<U> flatMap(
            Transformer<? super T, ? extends Maybe<? extends U>> func);

    public abstract void ifPresent(Consumer<? super T> consumer);

    public abstract T orElse(T fallback);

    private static final class None extends Maybe<Object> {
        @Override
        public Maybe<Object> filter(BooleanCondition<? super Object> pred) {
            return this;
        }

        @Override
        public <U> Maybe<U> map(
                Transformer<? super Object, ? extends U> func) {
            return Maybe.none();
        }

        @Override
        public <U> Maybe<U> flatMap(
                Transformer<? super Object,
                ? extends Maybe<? extends U>> func) {
            return Maybe.none();
        }

        @Override
        public void ifPresent(Consumer<? super Object> consumer) {
        }

        @Override
        public Object orElse(Object fallback) {
            return fallback;
        }

        @Override
        public String toString() {
            return "[]";
        }
    }

    private static final class Some<T> extends Maybe<T> {
        private final T value;

        private Some(T value) {
            this.value = value;
        }

        @Override
        public Maybe<T> filter(BooleanCondition<? super T> pred) {
            return pred.test(this.value) ? this : Maybe.none();
        }

        @Override
        public <U> Maybe<U> map(Transformer<? super T, ? extends U> func) {
            return Maybe.of(func.transform(this.value));
        }

        @Override
        public <U> Maybe<U> flatMap(
                Transformer<? super T,
                ? extends Maybe<? extends U>> func) {
            @SuppressWarnings("unchecked")
            Maybe<U> result = (Maybe<U>) func.transform(this.value);
            return result;
        }

        @Override
        public void ifPresent(Consumer<? super T> consumer) {
            consumer.consume(this.value);
        }

        @Override
        public T orElse(T fallback) {
            return this.value;
        }

        @Override
        public String toString() {
            return "[" + this.value + "]";
        }
    }
}
