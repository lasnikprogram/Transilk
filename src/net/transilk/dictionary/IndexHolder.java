package net.transilk.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexHolder {
    // .index file holds one index as follows: string \t absBytePos \t byteLength
    private final Map<String, IndexEntry> indexMap;

    public IndexHolder() {
        this.indexMap = new HashMap<>();
    }

    public void put(String searchTerm, int absoluteBytePosition, int byteLength) {
        indexMap.put(searchTerm, new IndexEntry(absoluteBytePosition, byteLength));
    }

    public IndexEntry get(String key) {
        return indexMap.get(key);
    }

    public Set<String> getKeySet() {
        return indexMap.keySet();
    }
}
