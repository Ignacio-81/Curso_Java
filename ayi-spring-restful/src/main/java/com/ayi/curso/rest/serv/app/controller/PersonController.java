package com.ayi.curso.rest.serv.app.controller;


import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Api(value = "Person API", tags = {"Persons Service"}) // Le decimos a Swagger que hay una transacción nueva, y que se llama Person Service (Es lo que se ve en grande en el Swagger)
@Slf4j // Esto es para el logeo
@RequestMapping(value = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE}) // Le indica al mundo externo que hay un servicio en la dirección web tal que se llama /persons, y lo que tiene que enviar es un JSON
@RestController // Esto es un controlador REST. @Controller es para un controlador MVC, no es REST
public class PersonController { // La puerta de entrada al endpoint

    private IPersonService personService; // Acá traigo la interfaz del servicio

    /*
    @PutMapping
    @PostMapping
    @DeleteMapping
    Las otras 3 opciones posibles
    */
    @GetMapping(value = "/getAllPersons") // El valor de la URL va a ser así: localhost:8080/persons/getAllPersons
    @ApiOperation( // Es parte del Swagger, esto lo va a mostrar ahí
            value = "Retrieves all Lists Persons",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses(value = { // Posibles respuestas que puedo tener
            @ApiResponse(code = 200, // Tdo ok
                    message = "Body content with basic information about persons",
                    response = PersonResponseDTO[].class),
            @ApiResponse(
                    code = 400, // Hubo algún error
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    }) // Documento tdo mi Swagger
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons() { // Antes solo devolvíamos la lista de PersonResponseDTO, ahora vamos a guardar esa lista en un ResponseEntity
        // Ésta es una estructura que nos permite intercambiar contenido a nivel de HTTP (cabeceras, body, los errores, tdo lo que tenga que ver con la respuesta de nuestro servicio)

        List<PersonResponseDTO> personResponseDTOs = personService.findAllPersons();
        return ResponseEntity.ok(personResponseDTOs); // ResponseEntity.[ver métodos]

    }

    @GetMapping(
            value = "/getPersonById/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "GET",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<PersonResponseDTO> getPersonById(
            @ApiParam(name = "id", required = true, value = "Person Id", example = "1")
            @PathVariable("id") Long id) { // este "id" es lo que está entre llaves en el getmapping {id}

        return ResponseEntity.ok(personService.findPersonById(id));

    }

    @GetMapping( // Ver si anda
            value = "/getPersonByNames/{firstName}/{lastName}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "GET",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<PersonResponseDTO>> getPersonByNames(
            @ApiParam(name = "firstName", required = true, value = "Person Name")
            @PathVariable("firstName") String firstName,
            @ApiParam(name = "lastName", required = true, value = "Person Last Name")
            @PathVariable("lastName") String lastName) {

        List<PersonResponseDTO> personResponseDTOs = personService.findPersonByName(firstName, lastName);
        return ResponseEntity.ok(personResponseDTOs);
    }

    @DeleteMapping(
            value = "/deletePersonById/{id}"
            //produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Delete data associated to List Master by Id",
            httpMethod = "DELETE"
            //response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204, //204 operacion OK , no devuelve informacion de la accion
                    message = "Se ha borrado correctamente"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<Void> deletePersonById(
            @ApiParam(name = "id", required = true, value = "Person Id", example = "1")
            @PathVariable("id") Long id) { // este "id" es lo que está entre llaves en el getmapping {id}

        personService.removePersonById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            value = "/addPerson"
    )
    @ApiOperation(
            value = "POST a new value on the Database",
            httpMethod = "POST",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, // la respuesta es CREATED
                    message = "La nueva persona fue creada"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error")
    })
    public ResponseEntity<PersonResponseDTO> postPerson(@RequestBody PersonDTO persona){

        return new ResponseEntity<>(personService.addPerson(persona), HttpStatus.CREATED);

        //return ResponseEntity.ok(personService.addPerson(persona));
    }
    @GetMapping(value = "/getAllPersons/{page}/{size}")
    @ApiOperation(
            value = "Retrieves all Lists Persons",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = PersonResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<PersonResponseDTO>> getAllPersonsForPage() {

        List<PersonResponseDTO> personResponseDTOs = personService.findAllPersons();
        return ResponseEntity.ok(personResponseDTOs);

    }

}
