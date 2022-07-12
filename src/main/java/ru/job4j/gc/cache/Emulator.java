package ru.job4j.gc.cache;

import java.util.Scanner;

public class Emulator {
    private DirFileCache dirFileCache;
    private final Scanner scanner = new Scanner(System.in);
    private final String startText = """
            Выберите действие
    1 - указать кэшируемую директорию
    2 - загрузить содержимое файла в кэш
    3 - получить содержимое файла из кэша
    4 - выход
    """;
    public static final int DIR = 1;
    public static final int PUT = 2;
    public static final int GET = 3;
    public static final int EXIT = 4;

    public void startUI() {
        int menu = 0;
        while (menu != EXIT) {
            System.out.println(startText);
            menu = scanner.nextInt();
            action(menu);
        }
    }

    private void action(int action) {
        switch (action) {
            case DIR -> cachingDir();
            case PUT -> toCache();
            case GET -> getFile();
            case EXIT -> System.out.println("Выход");
            default -> System.out.println("Ошибка ввода!");
        }
    }


    private void cachingDir() {
        System.out.println("Укажите директорию ");
        String dir = scanner.next();
        dirFileCache = new DirFileCache(dir);
        System.out.println("Установлена директория " + dirFileCache);

    }

    private void toCache() {
        System.out.println("Укажите файл для отправки в кэш ");
        String key = scanner.next();
        dirFileCache.put(key, dirFileCache.get(key));
    }

    private void getFile() {
        System.out.println("Укажите файл ");
        String key = scanner.next();
        String text = dirFileCache.get(key);
        System.out.println(text);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
       emulator.startUI();


    }
}

