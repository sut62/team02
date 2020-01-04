package com.example.demo.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Year")
public class Year {

    @Id
    @SequenceGenerator(name="year_seq",sequenceName="year_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="year_seq")  
    @Column(name = "YEAR_ID", unique = true, nullable = true)
    private @NonNull Long yearid;
    private @NonNull String year;

}