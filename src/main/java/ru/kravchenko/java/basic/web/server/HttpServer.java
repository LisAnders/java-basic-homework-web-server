package ru.kravchenko.java.basic.web.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpServer {
    private int port;
    private Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
    }


    public void start() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Сервер запущен на порту: " + this.port);
            this.dispatcher = new Dispatcher();
            System.out.println("Диспетчер проинициализирован");
            while (true) {
                service.execute(
                        () -> {
                            try (Socket socket = serverSocket.accept()) {
                                byte[] buffer = new byte[8192];
                                int n = socket.getInputStream().read(buffer);
                                String rawRequest = new String(buffer, 0, n);
                                HttpRequest request = new HttpRequest(rawRequest);
                                request.info(true);
                                dispatcher.execute(request, socket.getOutputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

            }
        } catch (IOException e) {
            service.shutdown();
            e.printStackTrace();
        }
        service.shutdown();

    }
}
