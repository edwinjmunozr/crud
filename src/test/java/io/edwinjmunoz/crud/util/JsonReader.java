package io.edwinjmunoz.crud.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class JsonReader {

    public String readJsonFile(String filename) {
        try {
            File file = FileUtils.toFile(JsonReader.class.getResource(filename));
            byte[] content = Files.readAllBytes(file.toPath());
            return new String(content, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
