package com.codicesoftware.plugins.hudson.commands;

import com.codicesoftware.plugins.hudson.commands.parsers.WorkspaceInfoParser;
import com.codicesoftware.plugins.hudson.model.WorkspaceInfo;
import com.codicesoftware.plugins.hudson.util.MaskedArgumentListBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

public class GetSelectorSpecCommand implements ParseableCommand<WorkspaceInfo>, Command {

    private final String selectorPath;

    public GetSelectorSpecCommand(String selectorPath) {
        this.selectorPath = selectorPath;
    }

    public MaskedArgumentListBuilder getArguments() {
        MaskedArgumentListBuilder arguments = new MaskedArgumentListBuilder();
        arguments.add("checkselectorsyntax");
        arguments.add("--file=" + selectorPath);
        arguments.add("--spec");
        arguments.add("--machinereadable");
        arguments.add("--fieldseparator=" + WorkspaceInfoParser.DEFAULT_SEPARATOR);
        return arguments;
    }

    public WorkspaceInfo parse(Reader r) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(r)) {
            String line = reader.readLine();
            return WorkspaceInfoParser.parse(line);
        } catch (Exception e) {
            throw new ParseException("Parse error: " + e.getMessage(), 0);
        }
    }
}
