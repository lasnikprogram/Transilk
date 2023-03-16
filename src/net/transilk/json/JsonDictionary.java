package net.transilk.json;

import java.util.List;

public class JsonDictionary {
    // json represented as java object, json from https://freedict.org/freedict-database.json
    String name;
    List<JsonDictionaryRelease> releases;

    public String getName() {
        return name;
    }


    public List<JsonDictionaryRelease> getReleases() {
        return releases;
    }

    // e.g. last entry in json has different structure -> return false
    public boolean isValid() {
        return name != null && releases != null;
    }
}
