package com.hicx.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hicx.data.Extension;
import com.hicx.data.file.File;
import com.hicx.data.file.ProcessedFile;

@RunWith(MockitoJUnitRunner.class)
public class TextFileProcessorTest {

    private static final String TEST_FILE = "testFile";

    @Mock
    private File file;

    private FileProcessor fileProcessor;

    @Before
    public void init() {
        String separator = java.io.File.separator;
        StringBuilder sourceFolderPath = new StringBuilder(separator)
            .append("src").append(separator)
            .append("test").append(separator)
            .append("java").append(separator)
            .append("com").append(separator)
            .append("hicx").append(separator)
            .append("processor").append(separator)
            .append("files");

        String basePath = new java.io.File("").getAbsolutePath();
        fileProcessor = new TextFileProcessor(basePath + sourceFolderPath.toString());
    }

    @Test
    public void should_not_process_other_than_txt_files() {
        when(file.getExtension()).thenReturn(Extension.PDF);

        ProcessedFile processedFile = fileProcessor.process(file);

        assertEquals(ProcessedFile.NONE, processedFile);
    }

    @Test
    public void should_process_txt_file() {
        when(file.getExtension()).thenReturn(Extension.TXT);
        when(file.getName()).thenReturn(TEST_FILE);

        ProcessedFile processedFile = fileProcessor.process(file);

        assertNotEquals(ProcessedFile.NONE, processedFile);
        assertEquals(TEST_FILE, processedFile.getName());
        assertEquals(Extension.TXT, processedFile.getExtension());
    }

    @Test()
    public void should_throw_exception_when_file_does_not_exist() {
        when(file.getExtension()).thenReturn(Extension.TXT);
        when(file.getName()).thenReturn("someFile");

        ProcessedFile processedFile = fileProcessor.process(file);
        assertNotEquals(ProcessedFile.NONE, processedFile);
    }
}
