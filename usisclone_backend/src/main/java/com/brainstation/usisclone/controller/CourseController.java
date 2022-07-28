package com.brainstation.usisclone.controller;

import com.brainstation.usisclone.models.Course;
import com.brainstation.usisclone.payload.ApiResponse;
import com.brainstation.usisclone.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;



    @GetMapping
    public ResponseEntity<?> getAllCourse(){
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody Course course){
        System.out.println(course);
        return new ResponseEntity<>(
                courseService.addCourse(course),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id){
        try{
            return new ResponseEntity<>( new ApiResponse("The informatin has been deleted successfully",
                    courseService.deleteCourseById(id)),HttpStatus.OK);
        }catch (NoSuchElementException e){
            ApiResponse apiResponse = new ApiResponse("Something went wrong");
            return new ResponseEntity<>( apiResponse ,HttpStatus.BAD_REQUEST);
        }
    }
}
