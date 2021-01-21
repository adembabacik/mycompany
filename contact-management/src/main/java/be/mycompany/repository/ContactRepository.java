package be.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.mycompany.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
