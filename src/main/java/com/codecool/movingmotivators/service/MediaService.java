package com.codecool.movingmotivators.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MediaService {

    private static final String MEDIA_PATH = "./src/main/resources/media/";

    public byte[] getCardImageByName(String imageName) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("classes/media/" + imageName);
            System.out.println(in);
            return in != null ? new byte[in] : new byte[0];
//            return Files.readAllBytes(Paths.get(MEDIA_PATH + imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
