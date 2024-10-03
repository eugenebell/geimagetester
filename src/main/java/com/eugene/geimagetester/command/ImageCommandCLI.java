package com.eugene.geimagetester.command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import com.eugene.geimagetester.service.ImageService;

@ShellComponent
public class ImageCommandCLI {

    private ImageService imageService;
    private static final Logger LOG = LogManager.getLogger(ImageCommandCLI.class);

    @Autowired
    public ImageCommandCLI(ImageService imageService) {
        this.imageService = imageService;
    }

    @ShellMethod(key = { "i", "create-image" })
    public String createImage(@ShellOption(defaultValue = "draw spiderman") String arg) {
        String question = formatStringToSentence(arg);
        // LOG.debug("- Args for q command is :" + question);
        ImageResponse response = imageService.generate(question);// , conversationId, 10);
        ImageGeneration ig = response.getResult();
        String imageData = ig.getOutput().getB64Json();
        try {
            writeToFile(question, imageData);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return ig.toString();
    }

    private String formatStringToSentence(String args) {
        return args.replaceAll(",", " ");
    }

    public void writeToFile(String name, String data) throws IOException {

        File f = new File(name + ".jpg");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(f);
        Decoder de = Base64.getDecoder();

        outputStream.write(de.decode(data));

        outputStream.close();
    }

}
