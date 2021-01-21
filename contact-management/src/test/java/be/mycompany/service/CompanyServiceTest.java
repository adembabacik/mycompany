package be.mycompany.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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
import be.mycompany.repository.CompanyRepository;
import be.mycompany.repository.ContactRepository;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
	@InjectMocks
	private CompanyService service;

	@Mock
	private CompanyRepository companyRepository;
	@Mock
	private ContactRepository contactRepository;

	@Test
	public void createCompany_tva_null_address_null_businessException() {
		assertThatThrownBy(() -> {
			service.createCompany(Company.builder().build());
		}).isInstanceOf(BusinessException.class);
	}
	
    @Test
    public void createCompanyOtherAddress_company_not_existing_resourceNotFoundException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.empty());
		assertThatThrownBy(() -> {
			service.createCompanyOtherAddress(1L, new Address());
		}).isInstanceOf(ResourceNotFoundException.class);
    }
    
    @Test
    public void patchCompanyDetails_tva_required_businessException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.of(Company.builder().mainAddress(new Address()).tva("123456").build()));
		assertThatThrownBy(() -> {
			service.patchCompanyDetails(1L, Company.builder().mainAddress(new Address()).build());
		}).isInstanceOf(BusinessException.class);
    }
    
    @Test
    public void updateCompanyMainAddress_address_is_required_businessException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.of(Company.builder().mainAddress(new Address()).tva("123456").build()));
		assertThatThrownBy(() -> {
			service.updateCompanyMainAddress(1L, null);
		}).isInstanceOf(BusinessException.class);
    }
    
    @Test
    public void updateCompanyOtherAddress_address_not_found_resourceNotFoundException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.of(Company.builder().mainAddress(new Address()).tva("123456")
				.otherAddress(List.of(Address.builder().id(2L).build())).build()));
		assertThatThrownBy(() -> {
			service.updateCompanyOtherAddress(1L, 5L, new Address());
		}).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void addContactToCompany_contact_doesnt_exit_businessException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.of(Company.builder().mainAddress(new Address()).tva("123456").build()));
		Mockito.when(contactRepository.findById(1L)).thenReturn(Optional.empty());
    	assertThatThrownBy(() -> {
			service.addContactToCompany(1L, 1L);
		}).isInstanceOf(ResourceNotFoundException.class);
    }
    
    @Test
    public void addContactToCompany_company_doesnt_exit_businessException() {
		Mockito.when(companyRepository.findById(1L))
		.thenReturn(Optional.empty());
    	assertThatThrownBy(() -> {
			service.addContactToCompany(1L, 1L);
		}).isInstanceOf(ResourceNotFoundException.class);
    }
}
