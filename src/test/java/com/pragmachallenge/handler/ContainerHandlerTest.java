package com.pragmachallenge.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmachallenge.converter.ContainerConverter;
import com.pragmachallenge.dto.ContainerInputDTO;
import com.pragmachallenge.model.Container;
import com.pragmachallenge.service.ContainerService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

public class ContainerHandlerTest {


    private ContainerService service;
    private ContainerConverter converter;
    private HttpExchange exchange;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ObjectMapper mapper;

    private ContainerHandler handler;

    @Before
    public void setUp() {
        service = mock(ContainerService.class);
        converter = mock(ContainerConverter.class);
        mapper = mock(ObjectMapper.class);
        handler = new ContainerHandler(service, converter, mapper);
        exchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        inputStream = mock(InputStream.class);
    }

    @Test
    public void shouldCallGetWhenRequestMethodIsGET() throws IOException {
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(exchange.getRequestURI()).thenReturn(URI.create("/containers"));
        when(exchange.getResponseHeaders()).thenReturn(new Headers());
        when(exchange.getResponseBody()).thenReturn(outputStream);
        when(mapper.writeValueAsString(emptyList())).thenReturn("string");

        handler.handle(exchange);

        verify(converter).toDTO(emptyList());
    }

    @Test
    public void shouldCallGetWhenRequestMethodIsGETWithPathParameter() throws IOException {
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(exchange.getRequestURI()).thenReturn(URI.create("/containers/1"));
        when(exchange.getResponseHeaders()).thenReturn(new Headers());
        when(exchange.getResponseBody()).thenReturn(outputStream);
        when(mapper.writeValueAsString(null)).thenReturn("string");



        handler.handle(exchange);

        verify(converter).toDTO((Container) null);
    }

    @Test
    public void shouldPut() throws IOException {
        when(exchange.getRequestMethod()).thenReturn("PUT");
        when(exchange.getRequestURI()).thenReturn(URI.create("/containers/1"));
        when(exchange.getResponseHeaders()).thenReturn(new Headers());
        when(exchange.getRequestBody()).thenReturn(inputStream);
        when(exchange.getResponseBody()).thenReturn(outputStream);
        when(mapper.readValue(anyString(), Mockito.eq(ContainerInputDTO.class))).thenReturn(new ContainerInputDTO());

        handler.handle(exchange);

        verify(converter).toModel(null, 1L);
    }
}
