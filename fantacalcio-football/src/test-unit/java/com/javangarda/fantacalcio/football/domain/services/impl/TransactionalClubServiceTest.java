package com.javangarda.fantacalcio.football.domain.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.javangarda.fantacalcio.football.domain.data.ClubDTO;
import com.javangarda.fantacalcio.football.domain.events.DuplicateClubNameException;
import com.javangarda.fantacalcio.football.domain.factories.ClubFactory;
import com.javangarda.fantacalcio.football.domain.model.Club;
import com.javangarda.fantacalcio.football.domain.repositories.ClubRepository;
import com.javangarda.fantacalcio.util.convert.Converter;

@RunWith(MockitoJUnitRunner.class)
public class TransactionalClubServiceTest {

	@Mock
	private ClubRepository clubRepository;
	@Mock
	private ClubFactory clubFactory;
	@Mock
	private Converter<ClubDTO, Club> clubConverter;
	
	@InjectMocks
	private TransactionalClubService service;
	
	@Test
	public void shouldStoreUnactiveClubWhileCreatingNewClub() throws DuplicateClubNameException {
		//given:
		String clubName = "Fiorentina";
		Mockito.when(clubRepository.countByName(clubName)).thenReturn(0);
		Club fiorentina = new Club();
		fiorentina.setId("fff-111");
		Mockito.when(clubFactory.create()).thenReturn(fiorentina);
		//when:
		String id = service.createClub(clubName);
		//then:
		Mockito.verify(clubRepository).save(fiorentina);
		Assert.assertEquals(clubName, fiorentina.getName());
		Assert.assertFalse(fiorentina.isActive());
		Assert.assertEquals("fff-111", id);
	}
	
	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowErrorWhileCreatingNewClubWithDuplicatedName() throws DuplicateClubNameException {
		//given:
		String clubName = "Internazionale";
		Mockito.when(clubRepository.countByName(clubName)).thenReturn(1);
		//when:
		service.createClub(clubName);
		//then:
		Mockito.verify(clubRepository, Mockito.never()).save(Mockito.any(Club.class));
		Mockito.verify(clubFactory, Mockito.never()).create();
	}
	
	@Test
	public void shouldMergeExistingClubWithDataWhileUpdatingClub() throws DuplicateClubNameException {
		//given:
		ClubDTO clubDTO = ClubDTO.builder().active(true).id("abcd-xyz").name("Juventus").build();
		Club juventus = new Club();
		juventus.setId("abcd-xyz");
		juventus.setActive(false);
		juventus.setName("Juventus");
		Club convertedClub = new Club();
		convertedClub.setId("abcd-xyz");
		convertedClub.setActive(true);
		convertedClub.setName("Juventus");
		Mockito.when(clubRepository.countByNameAndNotId("Juventus", "abcd-xyz")).thenReturn(0);
		Mockito.when(clubRepository.findOne("abcd-xyz")).thenReturn(juventus);
		Mockito.when(clubConverter.convertTo(clubDTO)).thenReturn(convertedClub);
		//when:
		service.updateClub(clubDTO);
		//then:
		Assert.assertTrue(juventus.isActive());
	}
	
	@Test(expected=DuplicateClubNameException.class)
	public void shouldThrowErrorWhileUpdatingClubWithDuplicatedName() throws DuplicateClubNameException {
		//given:
		ClubDTO clubDTO = ClubDTO.builder().active(true).id("fff-uuu").name("Internazionale").build();
		Mockito.when(clubRepository.countByNameAndNotId("Internazionale", "fff-uuu")).thenReturn(1);
		//when:
		service.updateClub(clubDTO);
		//then:
		Mockito.verify(clubRepository, Mockito.never()).findOne("fff-uuu");
		Mockito.verify(clubConverter, Mockito.never()).convertTo(clubDTO);
	}

}
