package com.codecool.movingmotivators.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {

    private static final String MEDIA_PATH = "./src/main/resources/media/";

    public List<byte[]> getCardsImage() {
        try {
            File[] files = getMediaFiles();
            List<byte[]> result = new ArrayList<>();

            for (File file : files)
                result.add(Files.readAllBytes(Paths.get(file.getPath())));

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File[] getMediaFiles() {
        File dir = new File(MEDIA_PATH);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0)
            return null;

        return files;
    }
}
