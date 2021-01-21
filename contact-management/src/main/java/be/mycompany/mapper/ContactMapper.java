package be.mycompany.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import be.mycompany.domain.Address;
import be.mycompany.domain.Contact;

@Mapper
public interface ContactMapper {
	ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
	
	be.mycompany.json.Contact map(Contact contact);
	Contact map(be.mycompany.json.Contact contact);
	
	be.mycompany.json.Address map(Address address);
	Address map(be.mycompany.json.Address address);
}