package ru.job4j.gc.cache;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Emulator {
    private DirFileCache dirFileCache;

    public void cachingDir(String dir) {
        System.out.println(dir);
        dirFileCache = new DirFileCache(dir);
    }

    public void toCache(String key, String value) {
        value = dirFileCache.load(key);
        SoftReference<String> file = new SoftReference<>(value);
        value = null;
        dirFileCache.cache.put(key, file);

    }

    public String getFile(String key) throws IOException {
        String value = dirFileCache.get(key);
        if (value == null) {
            value = dirFileCache.load(key);
            SoftReference<String> file = new SoftReference<>(value);
            dirFileCache.cache.put(key, file);
        }
        return value;
    }
}
