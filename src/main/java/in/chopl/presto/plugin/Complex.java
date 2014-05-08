package in.chopl.presto.plugin;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class Complex {
    private final static int REAL_PART_SHIFT = 32;
    private final static int IMAGINARY_PART_MASK = 0xFFFFFFFF;

    private final int x; // real part
    private final int y; // imaginary part

    public Complex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Complex(long v) {
        this.x = (int) (v >> REAL_PART_SHIFT);
        this.y = (int) (v & IMAGINARY_PART_MASK);
    }

    public long toLong() {
        return ((long) this.x) << REAL_PART_SHIFT | this.y;
    }

    public int getReal() {
        return x;
    }

    public int getImaginary() {
        return y;
    }

    public Complex add(Complex c) {
        return new Complex(x + c.x, y + c.y);
    }

    public Complex sub(Complex c) {
        return new Complex(x - c.x, y - c.y);
    }

    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Complex other = (Complex) obj;
        return x == other.x && y == other.y;
    }

    @JsonValue
    @Override
    public String toString() {
        String op;

        if (y >= 0)
            op = " + ";
        else
            op = " - ";

        return "(" + x + op + y + "i)";
    }

    public static Complex parse(String str) {
        try {
            int x, y;
            String[] es = str.split(" ");
            x = Integer.parseInt(es[0].substring(1), 10);
            y = Integer.parseInt(es[2].substring(0, es[2].length() - 2), 10);
            if (es[1].equals("-")) {
                y = -y;
            }
            return new Complex(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
