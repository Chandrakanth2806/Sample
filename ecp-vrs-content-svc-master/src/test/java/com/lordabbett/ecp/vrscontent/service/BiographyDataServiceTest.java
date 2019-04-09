package com.lordabbett.ecp.vrscontent.service;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.repository.CrudRepository;

import com.lordabbett.ecp.vrscontent.model.entity.Biography;
import com.lordabbett.ecp.vrscontent.model.exception.VRSContentServiceException;
import com.lordabbett.ecp.vrscontent.repository.BiographyRepository;

@RunWith(MockitoJUnitRunner.class)
public class BiographyDataServiceTest extends Mockito {

	BiographyDataService biographyDataService;

	@Mock
	BiographyRepository biographyRepository;

	@Mock
	Biography biography;

	@Before
	public void setup() {
		biographyDataService = spy(new BiographyDataService(biographyRepository));
		Whitebox.setInternalState(biographyDataService, "biographyRepository", biographyRepository);
	}

	@Test
	public void FindByPersonId() {
		Biography b = new Biography();
		Optional<Biography> ob = Optional.of(b);
		when(biographyRepository.findById(7)).thenReturn(ob);
		biographyDataService.findByPersonId(7);
	}

	@Test
	public void FindByPersonIdException() {
		try {
			Biography b = new Biography();
			Optional<Biography> ob = Optional.empty();
			when(biographyRepository.findById(7)).thenReturn(ob);
			biographyDataService.findByPersonId(7);
		} catch (VRSContentServiceException e) {

		} finally {
			verify(biographyDataService, times(1)).findByPersonId(7);
		}
	}

	@Test
	public void BiographySaveResultPresent() {
		Biography b = new Biography();
		Optional<Biography> ob = Optional.of(b);
		when(biography.getPersonId()).thenReturn(7);
		when(biographyRepository.findById(7)).thenReturn(ob);
		biographyDataService.save(biography);
	}

	@Test
	public void BiographySaveResultisEmpty() {
		Optional<Biography> ob = Optional.empty();
		when(biography.getPersonId()).thenReturn(7);
		when(biographyRepository.findById(7)).thenReturn(ob);
		biographyDataService.save(biography);
	}
}
