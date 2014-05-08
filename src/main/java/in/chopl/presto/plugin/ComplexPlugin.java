package in.chopl.presto.plugin;

import com.facebook.presto.metadata.FunctionFactory;
import com.facebook.presto.spi.Plugin;
import com.facebook.presto.spi.block.BlockEncodingFactory;
import com.facebook.presto.spi.type.Type;
import com.facebook.presto.metadata.OperatorFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class ComplexPlugin implements Plugin {
    private Map<String, String> optionalConfig = ImmutableMap.of();

    @Override
    public void setOptionalConfig(Map<String, String> stringStringMap) {
        this.optionalConfig = ImmutableMap.copyOf(checkNotNull(optionalConfig, "optionalConfig is null"));
    }

    @Override
    public <T> List<T> getServices(Class<T> type) {
        if (type == Type.class) {
            return ImmutableList.of(type.cast(ComplexType.COMPLEX));
        } else if (type == BlockEncodingFactory.class) {
            return ImmutableList.of(type.cast(ComplexType.BLOCK_ENCODING_FACTORY));
        } else if (type == OperatorFactory.class) {
            return ImmutableList.of(type.cast(ComplexFactory.getInstance()));
        } else if (type == FunctionFactory.class) {
            return ImmutableList.of(type.cast(ComplexFactory.getInstance()));
        }
        return ImmutableList.of();
    }
}
