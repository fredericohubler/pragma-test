package com.pragmachallenge.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmachallenge.converter.ContainerConverter;
import com.pragmachallenge.dto.ContainerInputDTO;
import com.pragmachallenge.exception.BusinesException;
import com.pragmachallenge.model.Container;
import com.pragmachallenge.service.ContainerService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class ContainerHandler {
    private String CONTEXT = "/containers/";
    private ContainerService containerService;
    private ContainerConverter converter;
    private ObjectMapper mapper;

    public ContainerHandler() {
        this.containerService = ContainerService.getInstance();
        this.mapper = new ObjectMapper();
        this.converter = ContainerConverter.getInstance();
    }

    public HttpHandler handle() {
        return httpExchange -> {
            try {
                switch (httpExchange.getRequestMethod()) {
                    case "GET" :
                        get(httpExchange);
                        break;

                    case "PUT" :
                        put(httpExchange);
                        break;

                    case "OPTIONS":
                        writeResponse(httpExchange, null, 200);
                        break;

                    default :
                        httpExchange.sendResponseHeaders(405, -1);
                        break;
                }
            } catch (BusinesException e) {
                writeResponse(httpExchange, e.getMessage().getBytes(), 404);
            } catch (Exception e) {
                writeResponse(httpExchange, e.getMessage().getBytes(), 500);
            }finally {
                httpExchange.close();
            }
        };
    }

    public void get(HttpExchange httpExchange) throws IOException {

        Optional<Long> containerId = getIdFromUri(httpExchange.getRequestURI());

        if (containerId.isPresent()) {
            Container container = containerService.getContainerById(containerId.get());
            String response = mapper.writeValueAsString(converter.toDTO(container));
            writeResponse(httpExchange, response.getBytes(), 200);
        } else {
            List<Container> containers = containerService.getAllContainers();
            String response = mapper.writeValueAsString(converter.toDTO(containers));
            writeResponse(httpExchange, response.getBytes(), 200);
        }
    }

    public void put(HttpExchange httpExchange) throws IOException {
        Optional<Long> containerId = getIdFromUri(httpExchange.getRequestURI());

        if (containerId.isPresent()) {
            ContainerInputDTO containerToUpdate = mapper.readValue(httpExchange.getRequestBody(), ContainerInputDTO.class);
            containerService.updateContainer(converter.toModel(containerToUpdate));
            writeResponse(httpExchange, null, 204);
        }

    }

    private Optional<Long> getIdFromUri(URI uri) {
        String[] splittedUri = uri.getPath().replace(CONTEXT, "").split("/");
        if (splittedUri != null && splittedUri.length == 1) {
            return Optional.of(new Long(splittedUri[0]));
        } else {
            return Optional.empty();
        }

    }


    public void writeResponse(HttpExchange httpExchange, byte[] response, int code) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "PUT, GET, OPTIONS");
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(code, 0);
        OutputStream output = httpExchange.getResponseBody();
        output.write(response);
        output.flush();
    }

}
