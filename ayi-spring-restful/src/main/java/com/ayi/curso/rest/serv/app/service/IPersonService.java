package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.exception.ReadAccessException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonService  {

    List<PersonResponseDTO> findAllPersons() throws ReadAccessException;

    PersonResponseDTO findPersonById(Long idPerson) throws ReadAccessException;

    // Este lo hice yo, ver después si funciona
    List<PersonResponseDTO> findPersonByName(String firstName, String lastName);

    public void removePersonById(Long idPerson);

    public PersonResponseDTO addPerson(PersonDTO persona);

    PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size);

    PersonResponseDTO updatePersonById(Long idPerson, PersonDTO personaDTO);
}
