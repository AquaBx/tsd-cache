package put.poznan.tsdcache.authentication;

import java.util.HashMap;

public class EmbeddedBadLoginAttemptsStorage implements BadLoginAttemptsStorage {

    // TODO 1.2 - Create and initialize a field of HashMap type to store data

    private HashMap<String, Integer> data = new HashMap<>();

    // TODO 1.2 - Finish this method
    @Override
    public Integer get(String key) {
        return data.getOrDefault(key,0);
    }

    // TODO 1.2 - Finish this method
    @Override
    public void put(String key, Integer value) {
        data.put(key, value);
    }

    // TODO 1.2 - Finish this method
    @Override
    public void increment(String key) {
        data.put(key, this.get(key) + 1);
    }

    // TODO 1.2 - Finish this method
    @Override
    public void remove(String key) {
        data.remove(key);
    }
}
