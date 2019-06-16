package com.pragmachallenge.converter;

import com.pragmachallenge.dto.ContainerInputDTO;
import com.pragmachallenge.dto.ContainerOutputDTO;
import com.pragmachallenge.model.Container;

import java.util.List;
import java.util.stream.Collectors;

public class ContainerConverter {

    private static ContainerConverter INSTANCE;

    private ContainerConverter(){}

    public static ContainerConverter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ContainerConverter();
        }
        return INSTANCE;
    }

    public List<ContainerOutputDTO> toDTO(List<Container> container){
        return container.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Container toModel(ContainerInputDTO dto){
        Container container = new Container();

        container.setBeer(dto.getBeer());
        container.setCurrentTemperature(dto.getCurrentTemperature());
        container.setId(dto.getId());

        return container;
    }

    public ContainerOutputDTO toDTO(Container container){
        ContainerOutputDTO dto = new ContainerOutputDTO();

        dto.setBeer(container.getBeer());
        dto.setId(container.getId());
        dto.setCurrentTemperature(container.getCurrentTemperature());
        if (container.getBeer() != null){
            dto.setMaxTemperature(container.getBeer().getMaxTemp());
            dto.setMinTemperature(container.getBeer().getMinTemp());
        }

        return dto;
    }


}
