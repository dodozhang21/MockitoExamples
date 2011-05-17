package net.pureessence.domain;


import java.util.ArrayList;
import java.util.List;

public class ObjectWithList {
    private List<String> logs = new ArrayList<String>();

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}
