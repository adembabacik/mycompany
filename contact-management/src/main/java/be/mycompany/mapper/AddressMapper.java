package be.mycompany.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import be.mycompany.domain.Address;

@Mapper
public interface AddressMapper {
	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	be.mycompany.json.Address map(Address address);
	Address map(be.mycompany.json.Address address);
}
