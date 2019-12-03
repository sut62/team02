package com.okta.entity.remand;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.okta.entity.book.Book;
import com.okta.entity.bookfine.Employee;
import com.okta.entity.borrow.Borrow;
import com.okta.entity.member.Member;

@Data
@Entity
@Table(name = "REMAND")
public class Remand {
    @Id
    @SequenceGenerator(name = "REMAND_SEQ", sequenceName = "REMAND_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMAND_SEQ")
    @Column(name = "Remand_id", unique = true, nullable = true)
    private Long id;

    @Column(name = "Remanddate")
    private Date remanddate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
    @JoinColumn(name = "Member_id", insertable = true)
    @JsonManagedReference
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Emp_id", insertable = true)
    @JsonManagedReference
    private @NotNull Employee employee;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Borrow.class)
    @JoinColumn(name = "Borrow_id", insertable = true)
    @JsonManagedReference
    private Borrow borrow;

}
