package com.example.demo.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data // เข้าใจ getter setter อัตโนมัติ เราจึงไม่สร้างก็ได้
@Table(name = "language")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @SequenceGenerator(name = "language_seq", sequenceName = "language_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_seq")
    @Column(name = "languageId", unique = true, nullable = false)

    private Long id;
    @NotNull
    private String languageName;

}