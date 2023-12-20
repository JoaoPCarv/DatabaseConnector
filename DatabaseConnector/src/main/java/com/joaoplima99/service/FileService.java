package com.joaoplima99.service;

import com.joaoplima99.manager.ResourceManager;
import com.joaoplima99.utils.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public final class FileService {

    /**
     * @deprecated (This class should not be instantiated)
     */
    @Deprecated(since = "1.0")
    private FileService() {
    }

    public static final Logger LOG = LoggerFactory.getLogger(FileService.class);

    public static Optional<Stream<String>> getFileTextStreamFromResourcePath(String path) {
        Stream<String> textStream;
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(
                Objects.requireNonNull(ResourceManager.getInstance().getResource(path).toURI()))))) {
            textStream = StreamUtils.copyStream(bf.lines());
            if(textStream == null) {
                LOG.error("The text stream loaded from the specified resource path [{}]  is null.", path);
                return Optional.empty();
            }
            return Optional.of(textStream);
        } catch (URISyntaxException | IOException e) {
            LOG.error("Error while loading file from the specified resource path. Exception thrown: {}.", e.getMessage());
            return Optional.empty();
        }
    }
}