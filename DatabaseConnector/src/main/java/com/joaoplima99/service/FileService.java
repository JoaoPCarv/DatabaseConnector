package com.joaoplima99.service;

import com.joaoplima99.utils.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class FileService {

    //This class should not be instantiated.
    @Deprecated(since = "1.0")
    private FileService() {
    }

    public static Logger LOG = LoggerFactory.getLogger(FileService.class);

    public static Stream<String> getFileTextStreamFromResourcePath(String path) {
        Stream<String> textStream = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(
                Objects.requireNonNull(FileService.class.getResource(path).toURI()))))) {
            textStream = StreamUtils.copyStream(bf.lines());
            if(textStream == null) {
                LOG.error("The text stream loaded from the specified resource path [{}]  is null.", path);
                return null;
            }
            return textStream;
        } catch (URISyntaxException | IOException e) {
            LOG.error("Error while loading file from the specified resource path. Exception thrown: {}.", e.getMessage());
            return null;
        }
    }
}