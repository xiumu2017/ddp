package com.paradise.interview.io.file;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Iterator;

/**
 * @author Paradise
 */
public class FileCopyDemo {
    /**
     * Java io 实现 文件拷贝
     *
     * @param source 源文件
     * @param dest   目的文件
     * @throws IOException IO 异常
     */
    private static void copy(String source, File dest) throws IOException {
        try (
                InputStream inputStream = new FileInputStream(source);
                OutputStream outputStream = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    /**
     * java.nio 类库 提供的 transferTo 方法实现
     *
     * @param source 源文件
     * @param dest   目的文件
     * @throws IOException IO 异常
     */
    private static void copyByChannel(String source, File dest) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    /**
     * java.nio Files工具类
     *
     * @param source 源文件
     * @param dest   目的文件
     * @throws IOException IO 异常
     */
    private static void filesCopy(String source, File dest) throws IOException {
        Files.copy(new File(source).toPath(), dest.toPath());

    }

    public static void main(String[] args) {
        try {
            copy("E://1.txt", new File("E://11.txt"));
            copyByChannel("E://11.txt", new File("E://22.txt"));
            filesCopy("E://22.txt", new File("E://2222.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
