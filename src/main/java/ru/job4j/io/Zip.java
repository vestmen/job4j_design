package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.NoSuchElementException;

import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {
        File dir = new File(args.get("d"));
        if (!dir.exists()) {
            throw new NoSuchElementException(String.format("Directory %s is not exist", dir));
        }
        if (args.get("e").charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("Incorrect file extension %s ", args.get("e")));
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Wrong name for zip file:  %s", args.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsZip = ArgsName.of(args);
        validateArgs(argsZip);
        List<Path> rsl = search(Path.of(argsZip.get("d")), a -> !a.toFile().getName().endsWith(argsZip.get("e")));
        zip.packFiles(rsl, new File(argsZip.get("o")));
    }
}
