package com.tegasus9.spring.core.io;

import lombok.Getter;

import java.io.*;
import java.nio.file.Files;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class FileSystemResource implements Resource{
    private final File file;

    @Getter
    private final String path;

    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path){
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.file.toPath());
    }
}
