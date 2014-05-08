package in.chopl.presto.plugin;

import com.facebook.presto.operator.scalar.ScalarOperator;
import com.facebook.presto.spi.type.BooleanType;
import com.facebook.presto.spi.type.VarcharType;
import com.facebook.presto.type.SqlType;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import static com.facebook.presto.metadata.OperatorInfo.OperatorType.*;
import static com.facebook.presto.metadata.OperatorInfo.OperatorType.CAST;
import static com.facebook.presto.metadata.OperatorInfo.OperatorType.HASH_CODE;
import static java.nio.charset.StandardCharsets.UTF_8;


public final class ComplexOperators {
    private ComplexOperators() {
    }

    @ScalarOperator(ADD)
    @SqlType(ComplexType.class)
    public static long add(@SqlType(ComplexType.class) long left, @SqlType(ComplexType.class) long right) {
        Complex l = new Complex(left);
        Complex r = new Complex(right);
        return l.add(r).toLong();
    }

    @ScalarOperator(SUBTRACT)
    @SqlType(ComplexType.class)
    public static long subtract(@SqlType(ComplexType.class) long left, @SqlType(ComplexType.class) long right) {
        Complex l = new Complex(left);
        Complex r = new Complex(right);
        return l.sub(r).toLong();
    }

    @ScalarOperator(EQUAL)
    @SqlType(BooleanType.class)
    public static boolean equal(@SqlType(ComplexType.class) long left, @SqlType(ComplexType.class) long right) {
        return left == right;
    }

    @ScalarOperator(NOT_EQUAL)
    @SqlType(BooleanType.class)
    public static boolean notEqual(@SqlType(ComplexType.class) long left, @SqlType(ComplexType.class) long right) {
        return left != right;
    }

    @ScalarOperator(CAST)
    @SqlType(VarcharType.class)
    public static Slice castToVarchar(@SqlType(ComplexType.class) long value) {
        return Slices.copiedBuffer(String.valueOf(value), UTF_8);
    }

    @ScalarOperator(CAST)
    @SqlType(VarcharType.class)
    public static Slice castToSlice(@SqlType(ComplexType.class) long value) {
        Complex v = new Complex(value);
        return Slices.copiedBuffer(v.toString(), UTF_8);
    }

    @ScalarOperator(CAST)
    @SqlType(ComplexType.class)
    public static long castFromSlice(@SqlType(VarcharType.class) Slice value) {
        return Complex.parse(value.toStringUtf8()).toLong();
    }

    @ScalarOperator(HASH_CODE)
    public static int hashCode(@SqlType(ComplexType.class) long value) {
        return (int) (value ^ (value >>> 32));
    }
}
