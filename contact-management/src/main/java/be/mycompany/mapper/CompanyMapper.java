package be.mycompany.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import be.mycompany.domain.Address;
import be.mycompany.domain.Company;

@Mapper
public interface CompanyMapper {
	CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
	
	be.mycompany.json.Company map (Company company);
	Company map(be.mycompany.json.Company company);
	
	be.mycompany.json.Address map(Address address);
	Address map(be.mycompany.json.Address address);
}
