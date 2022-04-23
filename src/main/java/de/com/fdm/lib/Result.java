package de.com.fdm.lib;

public class Result<V, E> {
    private final V value;
    private final E error;

    public Result(V value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <V, E> Result<V, E> ok(V value) {
        return new Result<>(value, null);
    }

    public static <V, E> Result<V, E> error(E error) {
        return new Result<>(null, error);
    }

    public boolean isError() {
        return this.error != null;
    }

    public boolean isOk() {
        return this.error == null;
    }

    public V getValue() {
        return this.value;
    }

    public E getError() {
        return this.error;
    }
}
