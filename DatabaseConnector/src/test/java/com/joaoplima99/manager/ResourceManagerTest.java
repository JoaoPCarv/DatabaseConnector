package com.joaoplima99.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
public class ResourceManagerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceManagerTest.class);
    private static final String SIMPLE_TESTING_MESSAGE = "This is a simple message for testing purposes.";

    @Spy
    private ResourceManager resourceManager;
    private MockedStatic<ResourceManager> rsmMockedStatic;

    @Before
    public void setup() {
        rsmMockedStatic = mockStatic(ResourceManager.class);
        rsmMockedStatic.when(ResourceManager::getInstance).thenReturn(resourceManager);
    }

    @After
    public void tearDown() {
        rsmMockedStatic.clearInvocations();
        rsmMockedStatic.close();
    }

    @Test
    public void assert_NPE_for_invalid_path_on_getResource() {
        assertThrows(NullPointerException.class, () -> resourceManager.getResource("invalid-path"));
    }

    @Test
    public void assert_PASS_for_valid_testing_path_on_getResource() {
        URL url = resourceManager.getResource("/testing/testing-supporting-files/simple-testing-file.dat");
        assertNotNull(url);
        try{
            File file = new File(url.toURI());
            assertTrue(file.exists());
            try(BufferedReader bfReader = new BufferedReader(new FileReader(file))) {
                assertEquals(SIMPLE_TESTING_MESSAGE, bfReader.readLine());
            }
            catch (FileNotFoundException fileNotFoundException) {
                LOG.error("Exception thrown when trying to fetch the testing file (from the file path {}).", file.getPath());
            } catch (IOException ioException) {
                LOG.error("Exception thrown when trying to auto-close the corresponding buffered reader for the testing file.");
            }
        } catch (URISyntaxException uriSyntaxException){
            LOG.error("An exception occurred when trying to convert the testing URL ({}) path.", url.toString());
        }
    }
}