package com.codicesoftware.plugins.jenkins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.model.Item;
import hudson.model.Job;
import hudson.model.Run;

import edu.umd.cs.findbugs.annotations.CheckForNull;

public class LastBuild {

    private LastBuild() {
    }

    @CheckForNull
    public static Run<?, ?> get(@NonNull final Item owner) {
        for (Job<?, ?> job : owner.getAllJobs()) {
            if (job == null) {
                continue;
            }

            Run<?, ?> run = job.getLastBuild();
            if (run != null) {
                return run;
            }
        }
        return null;
    }

}
