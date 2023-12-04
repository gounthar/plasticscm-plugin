package com.codicesoftware.plugins.jenkins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.FilePath;
import hudson.model.Computer;
import hudson.model.Node;
import jenkins.model.Jenkins;

public class BuildNode {

    private BuildNode() {
    }

    @NonNull
    public static Node getFromWorkspacePath(@NonNull final FilePath workspacePath) {
        Jenkins jenkins = Jenkins.getInstance();

        if (!workspacePath.isRemote()) {
            return jenkins;
        }

        for (Computer computer : jenkins.getComputers()) {
            if (computer.getChannel() != workspacePath.getChannel()) {
                continue;
            }

            Node node = computer.getNode();
            if (node != null) {
                return node;
            }
        }
        return jenkins;
    }
}
