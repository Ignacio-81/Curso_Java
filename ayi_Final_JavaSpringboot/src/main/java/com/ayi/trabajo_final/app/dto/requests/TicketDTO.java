package com.ayi.trabajo_final.app.dto.requests;

import com.ayi.trabajo_final.app.entities.CustomerEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "TicketDTO",
        description = "Represents the data needed to create Tickets"
)
public class TicketDTO implements Serializable {
    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private Double total;

    @ApiModelProperty(position = 3, notes = "Non negative value, The last name is required.")
    public CustomerDTO customer;


}
