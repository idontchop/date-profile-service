package com.idontchop.dateprofileservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;

import com.idontchop.dateprofileservice.dtos.TraitSelectionPair;
import com.idontchop.dateprofileservice.dtos.UserProfileDto;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.entities.Selection;
import com.idontchop.dateprofileservice.entities.Trait;
import com.idontchop.dateprofileservice.entities.TraitCategory;
import com.idontchop.dateprofileservice.entities.TraitType;
import com.idontchop.dateprofileservice.entities.TraitType.SelectionType;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;
import com.idontchop.dateprofileservice.repositories.SelectionRepository;
import com.idontchop.dateprofileservice.repositories.TraitCategoryRepository;
import com.idontchop.dateprofileservice.repositories.TraitRepository;
import com.idontchop.dateprofileservice.repositories.TraitTypeRepository;
import com.idontchop.dateprofileservice.services.ProfileService;
import com.idontchop.dateprofileservice.services.ReduceService;
import com.idontchop.dateprofileservice.services.TraitService;

@SpringBootTest
class DateProfileServiceApplicationTests {
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	TraitCategoryRepository traitCategoryRepository;
	
	@Autowired
	SelectionRepository selectionRepository;
	
	@Autowired
	TraitTypeRepository traitTypeRepository;
	
	@Autowired
	TraitRepository traitRepository;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	ReduceService reduceService;
	
	@Autowired
	TraitService traitService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testAddProfile () {
		
		String username = "mhaeann";
		String title = "Mhae Ann";
		
		UserProfileDto dto = new UserProfileDto();
		dto.setAboutMe("about me");
		dto.setUsername(username);
		dto.setLookingFor("Looking for rich man");
		dto.setTitle(title);
		dto.setTraits(
				List.of( new TraitSelectionPair("drinking", "lots"),
						new TraitSelectionPair("smoking", "few"),
						new TraitSelectionPair("smoking", "packs"),
						new TraitSelectionPair("drinking", "never")) );
		
		Profile profile = profileService.addProfile(dto);
		
		int size = profile.getTraits().size();
		List<Trait> newTraits = profile.getTraits().stream().filter( t -> !t.isType("smoking")).collect(Collectors.toList());
		
		assertNotEquals(size, newTraits.size());
		
		
		assertTrue(profile.getId() > 1);
		assertTrue(profile.getName().equals(username));
		assertEquals(title,profile.getTitle());
		assertEquals(2, profile.getTraits().size());
	}
	
	// non repo test
	@Test
	void testSelectionPairs () {
		
		// TODO: test duplicate traits and selections
		
		List<TraitSelectionPair> pairs = new ArrayList<>();
		
		//pairs.add( new TraitSelectionPair("smoking", "packs"));
		//pairs.add( new TraitSelectionPair("smoking", "few"));
		pairs.add( new TraitSelectionPair("drinking", "lots"));
		pairs.add( new TraitSelectionPair("smoking", "few"));
		pairs.add( new TraitSelectionPair("drinking", "unknown"));
		
		assertEquals(2, traitService.traitListFromSelectionPairs(pairs).size());
		
		assertEquals(1, traitService.traitListFromSelectionPairs(pairs).get(0).getSelections().size());
	}
	
	// non repo test
	@Test
	void testTraitFromPairs () {
		
		List<TraitSelectionPair> pairs = new ArrayList<>();
		
		pairs.add(new TraitSelectionPair("smoking", "packs"));
		
		Trait trait = traitService.traitFromPair(pairs);
		
		assertEquals(1, trait.getSelections().size());
		assertEquals("smoking", trait.getTraitType().getName());
	}


	void testTrait() {
		
		Trait trait = new Trait();
		
		Profile profile = profileRepository.findByName("2").get();
		TraitType smokingType = traitTypeRepository.findByName("drinking").get();
		
		trait.setTraitType(smokingType);
		trait.setProfile(profile);
		
		traitRepository.save(trait);
		
		Trait trait2 = traitRepository.findById(trait.getId()).get();
		
		assertTrue ( trait2.getId() == trait.getId() );
	}
	
	
	void testReduce () {
		
		List<String> potentials = List.of("1","nada","holytotallynotinhere","2");
		TraitSelectionPair tp = new TraitSelectionPair("smoking","packs");
		
		List<TraitSelectionPair> tpList = new ArrayList<>();
		tpList.add(tp);
		
		List<String> result = reduceService.reduceByTraitSelections(potentials, tpList);
		
		assertEquals(2, result.size());
		assertEquals("1", result.get(0));
		
	}
	


	void  loadDb () {
		
		// Social cat
		TraitCategory cat1 = traitCategoryRepository.findByName("Social").orElseGet( () -> {
			TraitCategory c = new TraitCategory();
			c.setGender("Any");
			c.setName("Social");
			c.setTitle("Drinking and Smoking");
			traitCategoryRepository.save(c);
			return c;
		});
		
		// smoking type
		TraitType smokingType = traitTypeRepository.findByName("drinking").orElseGet( () -> {
			TraitType st = new TraitType();
			st.setCategory(cat1);
			st.setName("drinking");
			st.setTitle("Do you drink?");
			st.setSelectionType(SelectionType.RADIO);
			
			traitTypeRepository.save(st);
			
			// selections
			Selection a = new Selection();
				a.setName("lots");
				a.setTitle("Drink everyday");
				a.setDescription("drunk every day");
				a.setTraitType(st);
			Selection b = new Selection();
				b.setName("few");
				b.setTitle("a few times");
				b.setTitle("a few times per day");
				b.setTraitType(st);
			Selection c = new Selection();
				c.setName("none");
				c.setTitle("None");
				c.setDescription("Never Drinking");
				c.setTraitType(st);
			
			
			selectionRepository.save(a);
			selectionRepository.save(b);
			selectionRepository.save(c);
			
			st.getSelections().add(a);
			st.getSelections().add(b);
			st.getSelections().add(c);
			
			traitTypeRepository.save(st);
			return st;			
		});
		
		assertEquals ( 3, smokingType.getSelections().size());
		
		Profile newProfile = profileRepository.findByName("2").orElseGet( () -> {
			
			Profile np = new Profile();
			np.setName("2");
			np.setAboutMe("About me 2");
			np.setTitle("Nathan");
			np.setLookingFor("Beautiful 2");
			
						
			Trait trait = new Trait();
			trait.setTraitType(smokingType);
			Selection a = smokingType.getSelections().get(0);
			trait.getSelections().add(a);
			
			np.getTraits().add(trait);
			
			profileRepository.save(np);
			
			trait.setProfile(np);
			
			traitRepository.save(trait);
					
			return np;
		});
		
		Trait newTrait = traitRepository.findById(2L).orElseGet( () -> {
			Trait trait = new Trait();
			trait.setTraitType(smokingType);
			Selection a = smokingType.getSelections().get(0);
			trait.getSelections().add(a);
				
			trait.setProfile(newProfile);
			
			Trait t;
			try {
				t = traitRepository.save(trait);
			} catch (Exception e ) {
				throw new IllegalArgumentException (e.getMessage());
			}
			
			assertTrue ( t.getId() > 0);
			return trait;
		});
		
		assertTrue(newTrait.getProfile().getName().equals("2"));

		
		
		
	}
}
