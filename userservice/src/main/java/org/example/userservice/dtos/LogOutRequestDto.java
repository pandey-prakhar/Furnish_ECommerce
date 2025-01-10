package org.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Token;

@Getter
@Setter
public class LogOutRequestDto {
    private Token token;
}
