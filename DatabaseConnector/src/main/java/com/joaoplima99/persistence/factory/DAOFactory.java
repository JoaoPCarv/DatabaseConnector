package com.joaoplima99.persistence.factory;

import com.joaoplima99.annotation.persistence.RepositoryMapped;
import com.joaoplima99.model.Entity;
import com.joaoplima99.persistence.interfaces.Repository;
import com.joaoplima99.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public final class DAOFactory {

    /**
     * @deprecated (This class should not be instantiated)
     */
    @Deprecated(since = "2.0")
    private DAOFactory() {}

    private static final Logger LOG = LoggerFactory.getLogger(DAOFactory.class);
    private static Map<@RepositoryMapped Class<? extends Entity>, Repository<? extends Entity>> DAO_CACHE = new HashMap<>();
    private static final List<String> DOMAINS = new ArrayList<>();

    static {
        Optional<Stream<String>> optRepositoryDomains = FileService
                .getFileTextStreamFromResourcePath("files/repository-domains.pat");
        optRepositoryDomains.ifPresent(stringStream -> stringStream.forEach(DOMAINS::add));
        if (DOMAINS.isEmpty()) {
            LOG.error("Error loading the repository domains.");
        }
    }
}
