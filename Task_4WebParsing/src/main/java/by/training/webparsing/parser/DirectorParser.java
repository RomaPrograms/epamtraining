package by.training.webparsing.parser;

import java.util.ArrayList;
import java.util.List;

public class DirectorParser {
    private List<DeviceHandler> data;

    public DirectorParser() {
        this.data = new ArrayList<>();
    }

    public List<DeviceHandler> getData() {
        return data;
    }

    public void setData(List<DeviceHandler> data) {
        this.data = data;
    }


}
