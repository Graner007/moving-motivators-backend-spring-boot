package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping(value = "/{imageName}", produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] cardImage(@PathVariable String imageName) { return mediaService.getCardImageByName(imageName); }

}
