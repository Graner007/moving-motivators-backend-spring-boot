package com.codecool.movingmotivators.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MediaService {

    private static final String MEDIA_PATH = "./src/main/resources/media/";

    public byte[] getCardImageByName(String imageName) {
        try {
            return Files.readAllBytes(Paths.get(MEDIA_PATH + imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
