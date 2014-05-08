package in.chopl.presto.plugin;

import com.facebook.presto.operator.Description;
import com.facebook.presto.operator.scalar.ScalarFunction;
import com.facebook.presto.spi.type.DoubleType;
import com.facebook.presto.type.SqlType;

public final class ComplexFunctions {
    private ComplexFunctions() {
    }

    @Description("absolute value")
    @ScalarFunction
    @SqlType(DoubleType.class)
    public static double abs(@SqlType(ComplexType.class) long value) {
        Complex v = new Complex(value);
        return v.abs();
    }
}