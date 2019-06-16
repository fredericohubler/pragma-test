package com.pragmachallenge.service;

import com.pragmachallenge.exception.BusinesException;
import com.pragmachallenge.model.BeerEnum;
import com.pragmachallenge.model.Container;
import com.pragmachallenge.repository.ContainerRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContainerServiceTest {

    private static final BeerEnum BEER = BeerEnum.WHEAT_BEER;
    private static final long ID = 1L;
    private static final int TEMPERATURE = 10;
    private static final BeerEnum ANOTHER_BEER = BeerEnum.IPA;
    private static final long ANOTHER_ID = 2L;
    private static final int ANOTHER_TEMPERATURE = 5;
    private static final long INVALID_ID = 3L;

    private ContainerService service;
    private ContainerRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        repository = Mockito.mock(ContainerRepository.class);
        service = new ContainerService(repository);
    }

    @Test
    public void shouldCallRepositoryOnGetContainerList() {
        whenGetAllContainers();
        service.getAllContainers();

        verify(repository).getContainerList();
    }

    @Test
    public void shouldReturnAllContainersOnGetContainerList() {
        whenGetAllContainers();

        assertThat(service.getAllContainers().size(), is(asList(createModel(), createAnotherModel()).size()));
    }

    @Test
    public void shouldCallRepositoryOnGetContainerById() {
        whenGetContainerById(ID);
        service.getContainerById(ID);

        verify(repository).getContainerById(ID);
    }

    @Test
    public void shouldReturnCorrectContainerOnGetContainerById() {
        whenGetContainerById(ID);
        when(repository.getContainerById(ANOTHER_ID)).thenReturn(Optional.of(createAnotherModel()));

        Container expected = createModel();
        Container result = service.getContainerById(ID);
        Container anotherExpected = createAnotherModel();
        Container anotherResult = service.getContainerById(ANOTHER_ID);

        assertThat(result, is(expected));
        assertThat(anotherResult, is(anotherExpected));
    }

    @Test
    public void ShouldThrowExceptionOnGetContainerByIdWhenContainerIdNotFound() {
        whenGetContainerByInvalidId();
        exception.expect(BusinesException.class);
        exception.expectMessage("Container with id 3 was not found.");

        service.getContainerById(INVALID_ID);
    }

    @Test
    public void shouldCallRepositoryOnUpdateContainer() {
        when(repository.updateContainer(createModel())).thenReturn(createModel());
        whenGetContainerById(ID);

        service.updateContainer(createModel());

        verify(repository).updateContainer(createModel());
    }

    @Test
    public void shouldUpdateCorrectContainer() {
        when(repository.updateContainer(createModel())).thenReturn(createModel());
        whenGetContainerById(ID);

        Container expected = createModel();
        Container result = service.updateContainer(createModel());

        assertThat(result, is(expected));
    }

    @Test
    public void shouldThrowExceptionOnUpdateContainerWhenContainerIdNotFound() {
        whenGetContainerByInvalidId();
        exception.expect(BusinesException.class);
        exception.expectMessage("Container with id 3 was not found.");

        service.updateContainer(createInvalidModel());
    }

    private void whenGetContainerByInvalidId() {
        when(repository.getContainerById(INVALID_ID)).thenReturn(Optional.empty());
    }

    private void whenGetContainerById(long id) {
        when(repository.getContainerById(id)).thenReturn(Optional.of(createModel()));
    }

    private void whenGetAllContainers() {
        when(repository.getContainerList()).thenReturn(asList(createModel(), createAnotherModel()));
    }

    public Container createModel() {
        Container model = new Container();

        model.setBeer(BEER);
        model.setId(ID);
        model.setCurrentTemperature(TEMPERATURE);

        return model;
    }

    public Container createAnotherModel() {
        Container model = new Container();

        model.setBeer(ANOTHER_BEER);
        model.setId(ANOTHER_ID);
        model.setCurrentTemperature(ANOTHER_TEMPERATURE);

        return model;
    }

    public Container createInvalidModel() {
        Container model = new Container();

        model.setId(INVALID_ID);

        return model;
    }
}
