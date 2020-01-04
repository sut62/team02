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
@Table(name="Type")
public class Type {

    @Id
    @SequenceGenerator(name="type_seq",sequenceName="type_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="type_seq")  
    @Column(name = "TYPE_ID", unique = true, nullable = true)
    private @NonNull Long typeid;
    private @NonNull String type;

}