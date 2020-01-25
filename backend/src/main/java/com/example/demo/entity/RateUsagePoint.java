package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Data  
@NoArgsConstructor  
@Table(
   uniqueConstraints = @UniqueConstraint(columnNames = {"EMAIL"})
)
public class RateUsagePoint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RATEUP_SEQ") 
    private Long id;
 
    @ManyToOne
    @NotNull(message = "Status cannot be null")
    private Status status;

    @ManyToOne
    @NotNull(message = "Education Level cannot be null")
    private EducationLevel edlevel;

    @NotNull(message = "Date cannot be null")
    private Date date;
      
    @Email(message = "Email is not a valid Email")
    @NotNull(message = "Email cannot be null")
    private String email; 
    
    @Size(min = 10, max = 200, message 
      = "Suggestion must be between 10 and 200 characters")
    @NotNull(message = "Suggestion cannot be null")
    private String suggestion;

    @ManyToOne
    @NotNull(message = "Rating cannot be null")
    private Rating rating;


}