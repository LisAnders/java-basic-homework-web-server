package ru.kravchenko.java.basic.web.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainApp {
    public static void main(String[] args) {
        new HttpServer(8189).start();
    }
}
