package com.codicesoftware.plugins.hudson.commands;

import com.codicesoftware.plugins.hudson.util.MaskedArgumentListBuilder;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.FilePath;

public class NewWorkspaceCommand implements Command {
    @NonNull
    private final String workspaceName;
    @NonNull
    private final FilePath workspacePath;

    public NewWorkspaceCommand(@NonNull final String workspaceName, @NonNull final FilePath workspacePath) {
        this.workspaceName = workspaceName;
        this.workspacePath = workspacePath;
    }

    @NonNull
    public MaskedArgumentListBuilder getArguments() {
        MaskedArgumentListBuilder arguments = new MaskedArgumentListBuilder();

        arguments.add("workspace");
        arguments.add("create");
        arguments.add(workspaceName);
        arguments.add(workspacePath.getRemote());

        return arguments;
    }
}
