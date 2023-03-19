package SearchEngine;

import java.util.HashMap;
import java.util.Map;

public class SearchEngine {
    private final Map<Integer, String> list = new HashMap<>();
    public void addToList(int i, String name) {
        list.put(i, name);
    }
    public Map<Integer, String> returnData() {
        return list;
    }
}

