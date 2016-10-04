package com.app.xml.common;

import java.io.File;
import java.util.List;

public interface XmlProcessor {

    public List<Person> readData();
    public void writeData(List<Person> data, File file);

}
