package com.eugene.geimagetester.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.StabilityAiImageModel;
import org.springframework.ai.stabilityai.api.StabilityAiApi;
import org.springframework.ai.stabilityai.api.StabilityAiApi.GenerateImageRequest;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private StabilityAiImageModel stabilityAiImageModel;

    @Autowired
    public ImageService(StabilityAiImageModel stabilityAiImageModel) {
        this.stabilityAiImageModel = stabilityAiImageModel;
    }

    public ImageResponse generate(String instructionMessage) {
        ImageResponse response = stabilityAiImageModel.call(
                new ImagePrompt(instructionMessage,
                        StabilityAiImageOptions.builder()
                                .withStylePreset("cinematic")
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024).build()));
        return response;
    }
}
