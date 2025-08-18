package cz.alexbotkova;

/** Configuration for random array creation. */
public enum RandomArrayCreationBounds {
    /** Default bounds: length from 3 to 10, values limited by modulo 100. */
    BOUNDS(3, 10, 100);

    private final int minLength;
    private final int maxLength;
    private final int modulo;

    RandomArrayCreationBounds(int minLength, int maxLength, int modulo) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.modulo = modulo;
    }

    /**
     * Returns the lower bound for array length.
     * @return minimum length
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Returns the upper bound for array length.
     * @return maximum length
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Returns the modulo used to limit generated values.
     * @return the modulo used
     */
    public int getModulo() {
        return modulo;
    }
}
