package com.codicesoftware.plugins.jenkins;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class TempFile {

    private TempFile() {
    }

    @NonNull
    public static Path create() throws IOException {
        return Files.createTempFile(UUID.randomUUID().toString(), ".tmp");
    }
}
