package core.util;


import java.util.HashMap;
import java.util.Map;


public class ScenarioScope {

    private Map<String, Object> context;

    public ScenarioScope() {
        this.context = new HashMap<>();
    }

    public <T> void setContext(final String key, final T value) {
        context.put(key, value);
    }

    public <T> T getContext(final String key, final Class<T> clazz) {
        return clazz.cast(context.get(key));
    }

}
