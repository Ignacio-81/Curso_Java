package com.ayi.trabajo_final.app.mapper.impl;
/**
 * Customer Mapper Implementation
 *
 */
import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapperImpl extends Exception implements ICustomerMapper {

    private final ModelMapper modelMapper;
    @Override
    public CustomerResponseDTO entityToDto(CustomerEntity entity) {

        CustomerResponseDTO personResponseDTO = new CustomerResponseDTO();
        modelMapper.map(entity, personResponseDTO);
        return personResponseDTO;
    }
    @Override
    public CustomerEntity dtoToEntity(CustomerDTO dto) {
        CustomerEntity personEntity = new CustomerEntity();
        modelMapper.map(dto, personEntity);
        return personEntity;
    }

    @Override
    public CustomerEntity responseDTOToEntity(CustomerResponseDTO dto) {
        CustomerEntity personEntity = new CustomerEntity();
        modelMapper.map(dto, personEntity);
        return personEntity;
    }
    @Override
    public CustomerTicketsResponseDTO entityToCustomerTicketDto(CustomerEntity entity) {

        CustomerTicketsResponseDTO customerTicketsResponseDTO = new CustomerTicketsResponseDTO();
        modelMapper.map(entity, customerTicketsResponseDTO);
        return customerTicketsResponseDTO;
    }


}