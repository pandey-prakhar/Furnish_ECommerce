package org.example.userservice.models;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String value;
}
