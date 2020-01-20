package com.example.demo.entity; 
import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity                 
@Data                   //เข้าใจ getter setter อัตโนมัติ เราจึงไม่สร้างก็ได้ 
@Table(name="bookcategory")   
@NoArgsConstructor
public class BookCategory {  
    @Id                                                                      
    @SequenceGenerator(name="bookcategory_seq",sequenceName="bookcategory_seq")          
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookcategory_seq")
    @Column(name="bookcategoryId",unique = true, nullable = false)          

    private @NonNull Long id;                                        
    private @NonNull String bookCategoryName;          
    
}