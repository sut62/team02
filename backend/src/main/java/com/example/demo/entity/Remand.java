package com.example.demo.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Date;

@Entity
@Data
@Table
@NoArgsConstructor
public class Remand {
    @Id
    @SequenceGenerator(name = "remand_seq", sequenceName = "remand_seq") 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "remand_seq")
    @Column(name = "remandId", unique = true)
    private @NotNull Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Min(1) @Max(10)
    private @NotNull Integer amount;

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

     private @NotNull Date remanddate;
    
    public Date getRemanddate() {
        return this.remanddate;
    }

    public void setRemanddate(Date remanddate) {
        this.remanddate = remanddate;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BookStatus.class)
    @JoinColumn(name = "bookstatusId", insertable = true)
    private @NotNull BookStatus bookStatus;

    public BookStatus getBookstatus() {
        return this.bookStatus;
    }

    public void setBookstatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Borrow.class) 
    @JoinColumn(name = "borrowID", insertable = true)
    private @NotNull Borrow borrow;

    public Borrow getBorrow() {
        return this.borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
    @JoinColumn(name = "memberID", insertable = true) 
    private @NotNull  Member member;

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BookType.class)
    @JoinColumn(name = "booktypeID", insertable = true)
    private @NotNull BookType bookType;

    public BookType getBookType() {
        return this.bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Document.class)
    @JoinColumn(name = "documentID", insertable = true) 
    private @NotNull Document document;

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}