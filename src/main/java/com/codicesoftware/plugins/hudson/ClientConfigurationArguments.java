package com.codicesoftware.plugins.hudson;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import com.codicesoftware.plugins.hudson.model.WorkingMode;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Util;
import hudson.util.ArgumentListBuilder;
import hudson.util.Secret;

import edu.umd.cs.findbugs.annotations.Nullable;

public class ClientConfigurationArguments {
    @Nullable
    private final WorkingMode workingMode;
    @Nullable
    private final StandardUsernamePasswordCredentials credentials;
    @Nullable
    private final String server;

    public ClientConfigurationArguments(
            @Nullable WorkingMode workingMode,
            @Nullable StandardUsernamePasswordCredentials credentials,
            @Nullable String server) {
        this.workingMode = workingMode;
        this.credentials = credentials;
        this.server = Util.fixEmpty(server);
    }

    @NonNull
    public ArgumentListBuilder fillParameters(@NonNull ArgumentListBuilder args) {
        if (hasServerValue()) {
            args.add(getServerParam());
        }

        if (hasWorkingModeManualValues()) {
            args.add(getWorkingModeParam());
            args.add(getUserParam());
            args.addMasked(getPasswordParam());
        }
        return args;
    }

    @NonNull
    String getServerParam() {
        return "--server=" + server;
    }

    @NonNull
    String getWorkingModeParam() {
        return "--workingmode=" + workingMode.getPlasticWorkingMode();
    }

    @NonNull
    String getUserParam() {
        return "--username=" + credentials.getUsername();
    }

    @NonNull
    String getPasswordParam() {
        return "--password=" + Secret.toString(credentials.getPassword());
    }

    boolean hasWorkingModeManualValues() {
        return workingMode != WorkingMode.NONE && credentials != null;
    }

    boolean hasServerValue() {
        return server != null;
    }
}
