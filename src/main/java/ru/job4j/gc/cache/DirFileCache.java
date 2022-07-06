package ru.job4j.gc.cache;


import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;


public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public void put(String key, String value) {
        SoftReference<String> file = new SoftReference<>(value);
        cache.put(key, file);
    }

    @Override
    protected String load(String key) {
        String text = null;
        try {
            text = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        put(key, text);
        return text;
    }

    @Override
    public String get(String key) {
        SoftReference<String> valueSoft =  cache.getOrDefault(key, null);
        String valueStrong = valueSoft.get();
        if (valueStrong == null) {
            valueStrong =  load(key);
        }
        return valueStrong;
    }

    @Override
    public String toString() {
        return "DirFileCache{" + "cachingDir='"
                + cachingDir + '\'' + '}';
    }
}

