package com.pragmachallenge.repository;

import com.pragmachallenge.model.Container;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ContainerRepository {

    private static final int TEMPERATURA_INICIAL = 10;
    private static ContainerRepository INSTANCE;

    private List<Container> containerList;

    public ContainerRepository() {
        init();
    }

    public static ContainerRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContainerRepository();
        }
        return INSTANCE;
    }

    public List<Container> getContainerList() {
        return containerList;
    }

    public Optional<Container> getContainerById(final Long id) {
        return containerList.stream()
                .filter(container -> container.getId().equals(id))
                .findFirst();
    }

    public Container updateContainer(Container container) {
        Optional<Container> containerById = getContainerById(container.getId());
        containerById.ifPresent(containerUpdated -> {
            containerUpdated.setCurrentTemperature(container.getCurrentTemperature());
            containerUpdated.setBeer(container.getBeer());
        });
        return containerById.orElse(null);
    }

    private void init() {
        containerList = LongStream.rangeClosed(1, 12).mapToObj(i -> {
            Container container = new Container();
            container.setId(i);
            container.setCurrentTemperature(TEMPERATURA_INICIAL);
            return container;
        }).collect(Collectors.toList());
    }

}
