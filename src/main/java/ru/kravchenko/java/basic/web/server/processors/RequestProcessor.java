package ru.kravchenko.java.basic.web.server.processors;

import ru.kravchenko.java.basic.web.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException;
}
