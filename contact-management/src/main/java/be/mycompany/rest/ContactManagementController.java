package be.mycompany.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import be.mycompany.json.Address;
import be.mycompany.json.Company;
import be.mycompany.json.Contact;
import be.mycompany.rest.handler.ContactManagementApi;
import be.mycompany.mapper.AddressMapper;
import be.mycompany.mapper.CompanyMapper;
import be.mycompany.mapper.ContactMapper;
import be.mycompany.service.CompanyService;
import be.mycompany.service.ContactService;

@Controller
@SuppressWarnings("unchecked")
public class ContactManagementController implements ContactManagementApi {

	@Autowired
	private ContactService contactService;
	@Autowired
	private CompanyService companyService;
	
	@Override
	public ResponseEntity<List<Company>> getCompanies() {
		return new ResponseEntity(companyService.getCompanies(), HttpStatus.OK);
	}
	@Override
	public ResponseEntity createCompany(@Valid Company body) {
		return new ResponseEntity(companyService.createCompany(CompanyMapper.INSTANCE.map(body)), 
				HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity createContact(@Valid Contact body) {
		return new ResponseEntity(contactService.createContact(ContactMapper.INSTANCE.map(body)),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity createCompanyOtherAddress(@Valid Address body, Long companyId) {
		return new ResponseEntity(companyService.createCompanyOtherAddress(companyId, AddressMapper.INSTANCE.map(body)),
				HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity deleteContact(Long contactId) {
		contactService.deleteContact(contactId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Company> patchCompanyDetails(@Valid Company body, Long companyId) {
		return new ResponseEntity(companyService.patchCompanyDetails(companyId, CompanyMapper.INSTANCE.map(body)),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Company> updateCompanyMainAddress(@Valid Address body, Long companyId) {
		return new ResponseEntity(companyService.updateCompanyMainAddress(companyId, AddressMapper.INSTANCE.map(body)),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Company> updateCompanyOtherAddress(@Valid Address body, Long companyId, Long addressId) {
		return new ResponseEntity(companyService.updateCompanyOtherAddress(companyId, addressId, 
				AddressMapper.INSTANCE.map(body)),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Contact> updateContact(@Valid Contact body, Long contactId) {
		return new ResponseEntity(contactService.updateContact(contactId, ContactMapper.INSTANCE.map(body)),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Company> addContactToCompany(Long companyId, Long contactId) {
		return new ResponseEntity(companyService.addContactToCompany(companyId, contactId),
				HttpStatus.OK);
	}
	
}
