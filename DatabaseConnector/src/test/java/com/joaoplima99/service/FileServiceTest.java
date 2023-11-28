package com.joaoplima99.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.joaoplima99.service.FileService.getFileTextStreamFromResourcePath;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

    @Test
    public void assert_NullPointerException_on_getFileTextStreamFromResourcePath() {
        assertThrows(NullPointerException.class, () -> getFileTextStreamFromResourcePath("/invalid-resource-path.dot"));
    }

}