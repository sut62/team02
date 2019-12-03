package com.cpe.backend.entity;
import lombok.*;
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
import com.cpe.backend.entity.Employee;
@Data
@Entity
@NoArgsConstructor
@Table(name="BOOK")
public class Book {


    @Id
    @SequenceGenerator(name="book_seq",sequenceName="book_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="book_seq")
    @Column(name = "BOOK_ID", unique = true, nullable = true)
    private @NonNull Long book_id;
    // 
    @Column(name="BOOK_NAME")
    private @NonNull String book_name;

    @Column(name="AMOUNT")
    private @NonNull Integer amount;

    @Column(name="BOOK_PRICE")
    private @NonNull Long book_price;

    @Column(name="REGISTRATION_DATE")
    private @NonNull Date registration_date;
    
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "CATEGORY_ID", insertable = true)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Author.class)
    @JoinColumn(name = "AUTHOR_ID", insertable = true)
    private Author author;

	public void setBookDate(Date date) {
        this.registration_date = date;
    }
    
    public void setName(String name){
        this.book_name = name;
    }

    public void setPrice(Long price){
        this.book_price = price;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }


}
