package ru.job4j.gc.cache;

import java.util.Scanner;

public class Emulator {
    private DirFileCache dirFileCache;
    private final Scanner scanner = new Scanner(System.in);
    private final String startText =
            "Выберите действие" + System.lineSeparator()
    + " 1 - указать кэшируемую директорию" + System.lineSeparator()
    + " 2 - загрузить содержимое файла в кэш" + System.lineSeparator()
    + " 3 - получить содержимое файла из кэша" + System.lineSeparator()
    + " 4 - выход";
    private final int setDir = 1;
    private final int putFile = 2;
    private final int getFile = 3;
    private final int exit = 4;

    public void startUI() {
        int menu = 0;
        while (menu != 4) {
            System.out.println(startText);
            menu = scanner.nextInt();
            action(menu);
        }
    }

    private void action(int action) {
        switch (action) {
            case setDir:
               cachingDir();
                break;
            case putFile:
                toCache();
                break;
            case getFile:
                getFile();
                break;
            case exit:
                System.out.println("Выход");
                break;
            default:
                System.out.println("Ошибка ввода!");
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
        dirFileCache.put(key, dirFileCache.load(key));
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

