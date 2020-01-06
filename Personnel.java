package com.example.backend.B5923403_Personnel.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="PERSONNEL")
@Getter @Setter
public class Personnel {
    @Id
    @SequenceGenerator(name="personnel_seq",sequenceName="personnel_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="personnel_seq")
    @Column(name="PERSONNEL_ID",unique = true, nullable = true)
    private @NotNull Long personnelId;
    private @NotNull String personnelName;
    private @NotNull String personnelRole;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prefix.class)
    private Prefix prefix;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)
    private Department department;
}
