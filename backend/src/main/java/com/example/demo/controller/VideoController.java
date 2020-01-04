package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import com.example.demo.repository.LibrarianRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.YearRepository;
import com.example.demo.repository.VideoRepository;

import com.example.demo.entity.Librarian;
import com.example.demo.entity.Video;
import com.example.demo.entity.Type;
import com.example.demo.entity.Year;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

class VideoController {
    @Autowired
    private final VideoRepository videoRepository;

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private TypeRepository typeRepository;


    VideoController(VideoRepository videoRepository){
        this.videoRepository = videoRepository;
    }


    @GetMapping("/video")
    public Collection<Video> Video() {
        return videoRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/addVideo/{librarianid}/{videoName}/{typeid}/{yearid}")
        public Video newVideo(Video newVideo,
                                  @PathVariable long librarianid,
                                  @PathVariable String videoName,
                                  @PathVariable long typeid,
                                  @PathVariable long yearid) {
                                      
          
            Librarian librarian = librarianRepository.findById(librarianid);
            Type type = typeRepository.findById(typeid);
            Year year = yearRepository.findById(yearid);

            newVideo.setLibrarian(librarian);
            newVideo.setVideoName(videoName);
            newVideo.setType(type);
            newVideo.setYear(year);
            

            
            return videoRepository.save(newVideo);

    }
}