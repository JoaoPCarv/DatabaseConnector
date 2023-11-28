package com.joaoplima99.utils;

import com.joaoplima99.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.joaoplima99.utils.StringUtils.getAppendedCharString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest {

    @Test
    public void assert_UOE_for_n_less_than_one_on_getAppendedCharString() {
        for(int i = (int) -1E3; i < 1; i++) {
            int iLambda = i;
            assertThrows(UnsupportedOperationException.class, () -> getAppendedCharString('.', iLambda));
        }
    }

    @Test
    public void assert_PASS_to_aan_array_of_asterisk_strings_on_getAppendedCharString() {
        //Safe operation (tested).
        Stream<String> starStream = FileService.getFileTextStreamFromResourcePath("/testing-patterns/asterisk-chains.pat");
        AtomicInteger i = new AtomicInteger(1);
        starStream.forEachOrdered(starChain -> assertEquals(starChain, getAppendedCharString('*', i.getAndIncrement())));
    }
}