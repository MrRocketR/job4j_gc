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
        dirFileCache =  new DirFileCache(dir);
    }

    public void toCache() {
        String s = dirFileCache.load(dirFileCache.toString());

    }

    public String getFile(String key) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        SoftReference<String> file =  dirFileCache.cache.get(key);
        Scanner scanner = new Scanner(new File(Objects.requireNonNull(file.get())));
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());

        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        String dir = "src\\cache";
        File file =  new File(dir);
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        emulator.cachingDir(dir);
        emulator.toCache();
        try {
            System.out.println(emulator.getFile("Names.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
