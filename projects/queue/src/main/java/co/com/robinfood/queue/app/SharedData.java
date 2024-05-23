package co.com.robinfood.queue.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SharedData {

    private static SharedData instance;

    private SharedData() {
    }

    public static SharedData getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SharedData();
        }
        return instance;
    }

    private Map<String, Object> parameters;

    public SharedData addParameter(String name, Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }

        this.parameters.put(name, value);
        return this;
    }

    public SharedData setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public void clearAll() {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.clear();
    }

}
