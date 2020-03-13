package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import com.example.demo.entity.BookType;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.RemandRepository;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // ทำให้ spingboot , vue ส่งข้อมูหากันได้
class RemandController {
    @Autowired
    private RemandRepository remandRepository;
    @Autowired // สร้างผู้ช่วย ที่คอยสั่ง Repo ให้ทำงานอัตโนมัติ
    private BookTypeRepository booktypeRepository; // สร้าง Obj ชื่อ typeRepo.. ชนิด TypeRepo.. เพื่อใช้งาน Entity Type
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private BookStatusRepository bookStatusRepository;

    RemandController(RemandRepository remandRepository, BookTypeRepository booktypeRepository,
            BorrowRepository borrowRepository, DocumentRepository documentRepository , BookStatusRepository bookStatusRepository) {
        this.remandRepository = remandRepository;
        this.booktypeRepository = booktypeRepository;
        this.borrowRepository = borrowRepository;
        this.documentRepository = documentRepository;
        this.bookStatusRepository = bookStatusRepository;
    }

    @GetMapping("/remand")
    // get ข้อมูลตาม พาท ที่กำหนดจากหน้าเว็บ 9000/remand
    public Collection<Remand> getAllRemand() {
        return remandRepository.findAll().stream().collect(Collectors.toList()); // ส่งค่าทั้งหมดใน remand return
                                                                                 // ไปแสดงที่หน้า 9000/remand
                                                                                 // โดยส่งทั้งหมดออกไปเป็น list
    }

   
    @PostMapping("/remand/{borrowID}/{booktypeID}/{documentID}/{bookstatusID}/{amount}")
    public Remand newRemand(Remand newRemand, @PathVariable long booktypeID, @PathVariable long borrowID,
            @PathVariable long documentID, @PathVariable Long bookstatusID, @PathVariable Integer amount) {
        // System.out.println(">>>>>>>>>>>>>>>>>" + examresultName);

        BookType booktype = booktypeRepository.findById(booktypeID); // เอา obj typeID ไป findById
        Borrow borrow = borrowRepository.findById(borrowID);
        Document document = documentRepository.findById(documentID);
        BookStatus bookstatus = bookStatusRepository.findById(bookstatusID).get();

        newRemand.setAmount(amount);
        newRemand.setBookType(booktype);
        newRemand.setBorrow(borrow);
        newRemand.setDocument(document);
        newRemand.setRemanddate(new Date());
        newRemand.setBookstatus(bookstatus);

        return remandRepository.save(newRemand);
        // เอา obj newRemand ไป save ลง Database หลักจากวิ่งผ่าน Path มา โดย save ด้วย
        // remandRepository
    }
}