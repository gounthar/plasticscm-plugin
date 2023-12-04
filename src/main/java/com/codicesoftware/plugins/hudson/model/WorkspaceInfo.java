package com.codicesoftware.plugins.hudson.model;

import edu.umd.cs.findbugs.annotations.NonNull;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.Nullable;

public class WorkspaceInfo {

    @NonNull
    private final String repoName;
    @CheckForNull
    private final String branch;
    @CheckForNull
    private final String label;
    @CheckForNull
    private final String changeset;

    public WorkspaceInfo(
            @NonNull String repoName,
            @Nullable String branch,
            @Nullable String label,
            @Nullable String changeset) {
        this.repoName = repoName;
        this.branch = branch;
        this.label = label;
        this.changeset = changeset;
    }

    @NonNull
    public String getRepoName() {
        return repoName;
    }

    @CheckForNull
    public String getBranch() {
        return branch;
    }

    @CheckForNull
    public String getLabel() {
        return label;
    }

    @CheckForNull
    public String getChangeset() {
        return changeset;
    }

    @NonNull
    public String getRepObjectSpec() {
        return String.format("%s@%s", getObjectSpec(), repoName);
    }

    @CheckForNull
    private String getObjectSpec() {
        if (containsValue(branch)) {
            return "br:" + branch;
        }

        if (containsValue(changeset)) {
            return "cs:" + changeset;
        }

        if (containsValue(label)) {
            return "lb:" + label;
        }

        return null;
    }

    private static boolean containsValue(@CheckForNull final String value) {
        return value != null && !value.isEmpty();
    }
}
