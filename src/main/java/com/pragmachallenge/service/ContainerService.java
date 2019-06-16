package com.pragmachallenge.service;

import com.pragmachallenge.exception.BusinesException;
import com.pragmachallenge.model.Container;
import com.pragmachallenge.repository.ContainerRepository;

import java.util.List;

public class ContainerService {

    private static ContainerService INSTANCE;
    private final ContainerRepository containerRepository;

    private ContainerService() {
        containerRepository = ContainerRepository.getInstance();
    }

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public static ContainerService getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ContainerService();
        }
        return INSTANCE;
    }

    public List<Container> getAllContainers() {
        return containerRepository.getContainerList();
    }

    public Container getContainerById(Long id) {
        return containerRepository.getContainerById(id)
                .orElseThrow(() -> new BusinesException("Container with id " + id + " was not found."));
    }

    public Container updateContainer(Container container) {
        containerRepository.getContainerById(container.getId())
                .orElseThrow(() -> new BusinesException("Container with id " + container.getId() + " was not found."));
        return containerRepository.updateContainer(container);
    }

}
