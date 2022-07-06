package ru.job4j.gc.cache;


import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Emulator {
    private DirFileCache dirFileCache;
    private final Scanner scanner = new Scanner(System.in);

    public void startUI() {
        int menu = 0;
        while (menu != 4) {
            System.out.println("Выберите действие");
            System.out.println(" 1 - указать кэшируемую директорию");
            System.out.println(" 2 - загрузить содержимое файла в кэш");
            System.out.println(" 3 - получить содержимое файла из кэша");
            System.out.println(" 4 - выход");
            menu = scanner.nextInt();
            action(menu);
        }
    }

    private void action(int action) {
        switch (action) {
            case 1:
               cachingDir();
                break;
            case 2:
                toCache();
                break;
            case 3:
                getFile();
                break;
            case 4:
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
        String text = dirFileCache.load(key);
        System.out.println(text);

    }

    private void getFile() {
        System.out.println("Укажите файл ");
        String key = scanner.next();
        String text = dirFileCache.get(key);
        System.out.println(text);
    }

    public static void test() {
        String dir = "D:\\projects\\job4j_gc\\src\\cache";
        String file = "Names.txt";
        String text = null;
        try {
            text =  Files.readString(Path.of(dir, file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }



    public static void main(String[] args) {
        Emulator emulator = new Emulator();
       emulator.startUI();


    }
}

