package neointernship.chess.game.model.util;

import java.util.Objects;

/**
 * Utility class that allows to combine different objects into pairs.
 *
 * @param <T> First object type.
 * @param <U> Second object type.
 */
public final class Pair<T, U> {
    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    private final T first;
    private final U second;


    /**
     * Class constructor that initializes Pair fields with passed objects.
     *
     * @param first  First object.
     * @param second Second object.
     */
    public Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Override of {@link Object#equals(Object)} method.
     *
     * @param o Object instance to check equality with.
     * @return Boolean value that presents result of checking.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    /**
     * Override of Object method.
     *
     * @return Integer value that presents hash code of {@link Pair} instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
