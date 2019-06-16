package com.pragmachallenge.converter;

import com.pragmachallenge.dto.ContainerInputDTO;
import com.pragmachallenge.dto.ContainerOutputDTO;
import com.pragmachallenge.model.BeerEnum;
import com.pragmachallenge.model.Container;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContainerConverterTest {

    private static final BeerEnum BEER = BeerEnum.IPA;
    private static final Integer CURRENT_TEMPERATURE = 5;
    private static final long ID = 1L;

    private ContainerConverter converter;

    @Before
    public void setUp() {
        converter = ContainerConverter.getInstance();
    }

    @Test
    public void shouldConvertModelToOutputDTO() {
        ContainerOutputDTO expected = createOutputDTO();
        ContainerOutputDTO result = converter.toDTO(createModel());

        assertThat(result, is(expected));
    }

    @Test
    public void shouldConvertModelListToOutputDTOList() {
        List<ContainerOutputDTO> expected = asList(createOutputDTO(), createOutputDTO());
        List<ContainerOutputDTO> result = converter.toDTO(asList(createModel(), createModel()));

        assertThat(result, is(expected));
    }

    @Test
    public void shouldConvertInputDTOToModel() {
        Container expected = createModel();
        Container result = converter.toModel(createInputDTO(), ID);

        assertThat(result, is(expected));
    }

    private ContainerOutputDTO createOutputDTO() {
        ContainerOutputDTO dto = new ContainerOutputDTO();

        dto.setBeer(BEER);
        dto.setId(ID);
        dto.setCurrentTemperature(CURRENT_TEMPERATURE);
        dto.setMinTemperature(BEER.getMinTemp());
        dto.setMaxTemperature(BEER.getMaxTemp());

        return dto;
    }

    private Container createModel() {
        Container model = new Container();

        model.setBeer(BEER);
        model.setId(ID);
        model.setCurrentTemperature(CURRENT_TEMPERATURE);

        return model;
    }

    private ContainerInputDTO createInputDTO() {
        ContainerInputDTO dto = new ContainerInputDTO();

        dto.setBeer(BEER);
        dto.setCurrentTemperature(CURRENT_TEMPERATURE);

        return dto;
    }
}
