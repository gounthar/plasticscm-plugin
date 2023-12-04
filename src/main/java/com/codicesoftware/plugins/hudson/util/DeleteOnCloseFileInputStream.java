package com.codicesoftware.plugins.hudson.util;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class DeleteOnCloseFileInputStream extends FileInputStream {

    private static final Logger LOGGER = Logger.getLogger(DeleteOnCloseFileInputStream.class.getName());

    @NonNull
    private final Path file;

    public DeleteOnCloseFileInputStream(@NonNull final Path file) throws FileNotFoundException {
        super(file.toAbsolutePath().toFile());
        this.file = file.toAbsolutePath();
    }

    public void close() throws IOException {
        try {
            super.close();
        } finally {
            try {
                Files.deleteIfExists(file);
            } catch (Exception e) {
                LOGGER.warning(String.format("Unable to delete file '%s': %s", file, e.getMessage()));
            }
        }
    }
}
