package com.codicesoftware.plugins.jenkins;

import com.codicesoftware.plugins.hudson.PlasticTool;
import com.codicesoftware.plugins.hudson.commands.CommandRunner;
import com.codicesoftware.plugins.hudson.commands.GetFileCommand;
import com.codicesoftware.plugins.hudson.util.DeleteOnCloseFileInputStream;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.io.IOException;
import java.nio.file.Path;

public class FileContent {
    private FileContent() {
    }

    @NonNull
    public static DeleteOnCloseFileInputStream getFromServer(
            @NonNull final PlasticTool tool,
            @NonNull final String serverFile,
            @NonNull final String repObjectSpec) throws IOException, InterruptedException {
        Path tempFile = TempFile.create();

        String serverPathRevSpec = String.format("serverpath:%s#%s", serverFile, repObjectSpec);

        GetFileCommand command = new GetFileCommand(serverPathRevSpec, tempFile.toAbsolutePath().toString());
        CommandRunner.execute(tool, command).close();

        return new DeleteOnCloseFileInputStream(tempFile);
    }
}
