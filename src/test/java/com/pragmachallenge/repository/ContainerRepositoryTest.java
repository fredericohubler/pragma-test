package com.pragmachallenge.repository;

import com.pragmachallenge.model.BeerEnum;
import com.pragmachallenge.model.Container;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContainerRepositoryTest {

    public static final long ID = 3L;
    public static final int CURRENT_TEMPERATURE = 10;
    private ContainerRepository repository;

    @Before
    public void setUp() {
        repository = new ContainerRepository();
    }

    @Test
    public void shouldReturnCorrectContainerById() {
        Optional<Container> expected = Optional.of(createModel());
        Optional<Container> result = repository.getContainerById(ID);

        assertThat(result, is(expected));
    }

    @Test
    public void shouldUpdateContainer() {
        Container containerToUpdate = new Container();

        containerToUpdate.setBeer(BeerEnum.IPA);
        containerToUpdate.setId(ID);
        containerToUpdate.setCurrentTemperature(5);

        repository.updateContainer(containerToUpdate);

        assertThat(Optional.of(containerToUpdate), is(repository.getContainerById(ID)));

    }

    private Container createModel() {
        Container container = new Container();

        container.setId(ID);
        container.setCurrentTemperature(CURRENT_TEMPERATURE);

        return container;
    }
}
