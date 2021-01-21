package be.mycompany.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.mycompany.domain.Contact;
import be.mycompany.domain.Status;
import be.mycompany.exceptions.BusinessException;
import be.mycompany.exceptions.ResourceNotFoundException;
import be.mycompany.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public Contact updateContact(Long contactId, Contact body) {
		Contact existingContact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found."));
		if (body.getStatus() == null || body.getAddress() == null ||
				body.getFirstname() == null || body.getLastname() == null) {
			throw new BusinessException("Status, Address, firstname and lastname are required fields.");
		}
		if (body.getStatus() == Status.FREELANCE && body.getTva() == null) {
			throw new BusinessException("TVA is required for FREELANCE contact.");
		}
		
		existingContact.setFirstname(body.getFirstname());
		existingContact.setLastname(body.getLastname());
		existingContact.setAddress(body.getAddress());
		existingContact.setStatus(body.getStatus());
		existingContact.setTva(body.getTva());

		return contactRepository.save(existingContact);
	}

	public void deleteContact(Long contactId) {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

		contactRepository.deleteById(contactId);
	}
	

	public Contact createContact(Contact contact) {
		if (contact == null || contact.getStatus() == null || contact.getAddress() == null 
				|| contact.getFirstname() == null
				|| contact.getLastname() == null) {
			throw new BusinessException("Status and Address are required fields.");
		}
		if (contact == null ||contact.getStatus() == Status.FREELANCE && contact.getTva() == null) {
			throw new BusinessException("TVA is required for FREELANCE contact.");
		}

		return contactRepository.save(contact);
	}
}
