package com.joaoplima99.utils;

import com.joaoplima99.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.joaoplima99.utils.StringUtils.emptyStringBuilder;
import static com.joaoplima99.utils.StringUtils.getAppendedCharString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtilsTest.class);

    @Test
    public void assert_UnsupportedOperationException_for_n_less_than_one_on_getAppendedCharString() {
        for(int i = (int) -1E3; i < 1; i++) {
            int iLambda = i;
            assertThrows(UnsupportedOperationException.class, () -> getAppendedCharString('.', iLambda));
        }
    }

    @Test
    public void assert_PASS_to_aan_array_of_asterisk_strings_on_getAppendedCharString() {
        //Safe operation (tested).
        Optional<Stream<String>> optionalStarStream = FileService
                .getFileTextStreamFromResourcePath("/testing/testing-patterns/asterisk-chains.pat");
        if (optionalStarStream.isPresent()) {
            Stream<String> starStream = optionalStarStream.get();
            AtomicInteger i = new AtomicInteger(1);
            starStream.forEachOrdered(starChain -> assertEquals(starChain, getAppendedCharString('*', i.getAndIncrement())));
        } else {
            LOG.error("Error loading testing pattern 'asterisk-chains': its text stream is null. Test aborted.");
        }
    }

    @Test
    public void assert_emptied_StringBuilder_on_emptyStringBuilder() {
        StringBuilder sBuilder = new StringBuilder();
        Set<String> stringSet = Sets.newSet("Joao", "Lima", "Java", "JUnit", "Mockito");
        stringSet.forEach(sBuilder::append);
        emptyStringBuilder(sBuilder);
        assertEquals("", sBuilder.toString());
    }
}