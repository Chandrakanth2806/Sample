package com.lordabbett.ecp.vrscontent.service;

import com.lordabbett.ecp.vrscontent.model.entity.Biography;
import com.lordabbett.ecp.vrscontent.model.exception.VRSContentServiceException;
import com.lordabbett.ecp.vrscontent.repository.BiographyRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiographyDataService {

	private static final Logger LOG = LoggerFactory.getLogger(BiographyDataService.class);

	private BiographyRepository biographyRepository;

	@Autowired
	public BiographyDataService(BiographyRepository biographyRepository) {
		this.biographyRepository = biographyRepository;
	}

	public Biography findByPersonId(Integer personId) {
		LOG.info("Fetching IP Bio from the DB by Id: {}", personId);
		Optional<Biography> biographyResult = biographyRepository.findById(personId);
		if (biographyResult.isPresent()) {
			return biographyResult.get();
		}
		throw new VRSContentServiceException("Cannot find person with id" + personId, 404);
	}

	public Biography save(Biography biography) {
		Optional<Biography> biographyResult = biographyRepository.findById(biography.getPersonId());
		if (biographyResult.isPresent()) {
			Biography existing = biographyResult.get();
			biography.setCreatedDate(existing.getCreatedDate());
			biography.setActiveInd(existing.isActiveInd());
			biography.setCreatedBy(existing.getCreatedBy());
		} else {
			biography.setCreatedDate(LocalDateTime.now());
			biography.setActiveInd(true);
			biography.setCreatedBy("system");
		}
		LOG.info("Saving biography to db personId: {}", biography.getPersonId());
		return biographyRepository.save(biography);
	}
}
