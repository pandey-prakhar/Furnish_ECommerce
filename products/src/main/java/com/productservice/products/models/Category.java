package com.productservice.products.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String description;
}

//Ensure JPA Facet is Added:
//Right-click on your project and go to Module Settings.
//In the Project Structure window, ensure that the JPA Facet is added to your module.
//        If not, you can add it by clicking on the + sign and selecting JPA.