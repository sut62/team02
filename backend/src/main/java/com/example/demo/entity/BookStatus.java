package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;


@Entity                 
@Data                   //เข้าใจ getter setter อัตโนมัติ เราจึงไม่สร้างก็ได้ 
@Table(name="bookstatus")   
@NoArgsConstructor
@ToString
public class BookStatus {  
    @Id                                                                      
    @SequenceGenerator(name="bookstatus_seq",sequenceName="bookstatus_seq")          
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookstatus_seq")
    @Column(name="bookstatusId",unique = true)          
    private @NotNull Long id;
    
    @Size(min = 3, max = 30)
    @Pattern(regexp =  "^[0-9A-Za-zก-์\\s]+$")
    private @NotNull String bookStatusName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
                                   

    public String getBookStatusName() {
        return this.bookStatusName;
    }

    public void setBookStatusName(String bookStatusName) {
        this.bookStatusName = bookStatusName;
    }


}