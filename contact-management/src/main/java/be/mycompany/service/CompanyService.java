package be.mycompany.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import be.mycompany.domain.Address;
import be.mycompany.domain.Company;
import be.mycompany.domain.Contact;
import be.mycompany.domain.Status;
import be.mycompany.exceptions.BusinessException;
import be.mycompany.exceptions.ResourceNotFoundException;
import be.mycompany.repository.CompanyRepository;
import be.mycompany.repository.ContactRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private ContactRepository contactRepository;

	public Company createCompany(Company company) {
		if (company.getTva() == null) {
			throw new BusinessException("TVA cannot be null.");
		}
		if (company.getMainAddress() == null) {
			throw new BusinessException("Company main address cannot be null.");
		}
		return companyRepository.save(company);
	}

	public Company createCompanyOtherAddress(Long companyId, Address address) {

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found."));

		company.getOtherAddress().add(address);
		return companyRepository.save(company);
	}

	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}

	public Company patchCompanyDetails(Long companyId, Company body) {

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found."));

		if (body.getTva() == null) {
			throw new BusinessException("TVA is required");
		}

		company.setTva(body.getTva());
		return companyRepository.save(company);
	}

	public Company updateCompanyMainAddress(Long companyId, Address address) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found."));

		if (address == null) {
			throw new BusinessException("Address could not be null.");
		}

		company.getMainAddress().setCountry(address.getCountry());
		company.getMainAddress().setStreet(address.getStreet());
		company.getMainAddress().setNumber(address.getNumber());
		company.getMainAddress().setPostalCode(address.getPostalCode());

		return companyRepository.save(company);
	}

	public Company updateCompanyOtherAddress(Long companyId, Long addressId, Address body) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found."));

		Address address = company.getOtherAddress().stream().filter(a -> a.getId() == addressId)
				.findAny().orElseThrow(() -> new ResourceNotFoundException("Address not found."));
	
		address.setCountry(body.getCountry());
		address.setPostalCode(body.getPostalCode());
		address.setStreet(body.getStreet());
		address.setNumber(body.getNumber());
		
		return companyRepository.save(company);
	}

	public Company addContactToCompany(Long companyId, Long contactId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found."));
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found."));
		
		company.getContacts().add(contact);
		
		return companyRepository.save(company);
	}

}
