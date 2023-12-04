package com.codicesoftware.plugins.jenkins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.AbortException;
import hudson.model.TaskListener;

import java.util.logging.Logger;

public class AbortExceptionBuilder {

    private AbortExceptionBuilder() {
    }

    public static AbortException build(
            @NonNull final Logger logger, @NonNull final TaskListener listener, @NonNull final Exception e) {
        listener.fatalError(e.getMessage());
        logger.severe(e.getMessage());
        AbortException result = new AbortException(e.getMessage());
        result.initCause(e);
        return result;
    }
}
