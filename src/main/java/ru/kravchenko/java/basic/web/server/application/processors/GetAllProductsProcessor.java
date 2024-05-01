package ru.kravchenko.java.basic.web.server.application.processors;

import com.google.gson.Gson;
import ru.kravchenko.java.basic.web.server.HttpRequest;
import ru.kravchenko.java.basic.web.server.application.DBStorage;
import ru.kravchenko.java.basic.web.server.application.Product;
import ru.kravchenko.java.basic.web.server.logging.LogService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class GetAllProductsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        //StringBuilder response = new StringBuilder("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body>");
        try {
            List<Product> products = DBStorage.getAllProducts();
            Gson gson = new Gson();
            String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + gson.toJson(products);
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException e) {
            //e.printStackTrace();
            LogService.error(e.getMessage());
        }

    }
}
