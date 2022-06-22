package ru.job4j.gc.cache;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        System.out.println(key);
        File dir = new File(key);
        if (dir.exists()) {
            System.out.println("YES");
            for (File subfile : Objects.requireNonNull(dir.listFiles())) {
                System.out.println(subfile.getName());
                String k = subfile.getName();
                Path path = Paths.get(String.valueOf(subfile.getAbsoluteFile()));
                try {
                    String read = Files.readAllLines(path).get(0);
                    SoftReference<String> v = new SoftReference<>(k);
                    cache.putIfAbsent(k, v);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return cachingDir;
    }

    @Override
    public String toString() {
        return  cachingDir;
    }
}
