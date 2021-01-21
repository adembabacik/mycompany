package be.mycompany.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import be.mycompany.domain.Address;
import be.mycompany.domain.Company;
import be.mycompany.domain.Contact;
import be.mycompany.domain.Status;
import be.mycompany.exceptions.BusinessException;
import be.mycompany.exceptions.ResourceNotFoundException;
import be.mycompany.repository.ContactRepository;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
	@InjectMocks
	private ContactService service;

	@Mock
	private ContactRepository contactRepository;
	
	@Test
	public void updateContact_missing_required_fields_businessException() {
		Mockito.when(contactRepository.findById(1L))
		.thenReturn(Optional.of(Contact.builder().firstname("a").lastname("b")
				.address(new Address()).status(Status.EMPLOYE).build()));
		
		assertThatThrownBy(() -> {
			service.updateContact(1L, Contact.builder().build());
		}).isInstanceOf(BusinessException.class);
	}

	@Test
	public void updateContact_tva_is_required_businessException() {
		Mockito.when(contactRepository.findById(1L))
		.thenReturn(Optional.of(Contact.builder().firstname("a").lastname("b")
				.address(new Address()).status(Status.EMPLOYE).build()));
		
		assertThatThrownBy(() -> {
			service.updateContact(1L, 
					Contact.builder().firstname("aaa").lastname("bbb").address(new Address())
					.status(Status.FREELANCE).build());
		}).isInstanceOf(BusinessException.class);
		
	}
	
	@Test
	public void createContact_contact_required_fields_businessException() {
		assertThatThrownBy(() -> {
			service.createContact(Contact.builder().build());
		}).isInstanceOf(BusinessException.class);
	}
	
    @Test
    public void deleteContact_contact_not_existing_resourceNotFoundException() {
		Mockito.when(contactRepository.findById(1L))
		.thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> {
			service.deleteContact(1L);
		}).isInstanceOf(ResourceNotFoundException.class);
    }
    
	
	@Test
	public void createContact_contact_status_freelance_tva_not_filled_businessException() {
		assertThatThrownBy(() -> {
			service.createContact(Contact.builder()
					.address(new Address()).firstname("a").lastname("b").status(Status.FREELANCE).build());
		}).isInstanceOf(BusinessException.class);
	}
}
