package in.chopl.presto.plugin;

import com.facebook.presto.metadata.*;

import java.util.List;

public class ComplexFactory implements FunctionFactory, OperatorFactory {
    private static final ComplexFactory factory = new ComplexFactory();
    private final FunctionRegistry.FunctionListBuilder builder;

    public static ComplexFactory getInstance() {
        return factory;
    }

    private ComplexFactory() {
        builder = new FunctionRegistry.FunctionListBuilder()
                .scalar(ComplexFunctions.class)
                .scalar(ComplexOperators.class);
    }

    @Override
    public List<FunctionInfo> listFunctions() {
        return builder.getFunctions();
    }

    @Override
    public List<OperatorInfo> listOperators() {
        return builder.getOperators();
    }
}
