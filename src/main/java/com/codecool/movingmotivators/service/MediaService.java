package com.codecool.movingmotivators.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class MediaService {

    private static final String MEDIA_PATH = "../../../../media/";

    public byte[] getCardImageByName(String imageName) {
        try {
            return getClass().getResourceAsStream(MEDIA_PATH + imageName).readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
