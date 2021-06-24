package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping(path = "/cards-image", produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody List<byte[]> cardsImage() { return mediaService.getCardsImage(); }
}
