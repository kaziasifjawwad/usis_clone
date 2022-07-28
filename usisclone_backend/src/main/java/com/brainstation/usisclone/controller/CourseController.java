package com.brainstation.usisclone.controller;

import com.brainstation.usisclone.models.Course;
import com.brainstation.usisclone.payload.ApiResponse;
import com.brainstation.usisclone.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping(path = "api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;



    @GetMapping
    public ResponseEntity<?> getAllCourse(){
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(courseService.findCourseById(id),HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(
                    new ApiResponse("No course found with this ID"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody Course course){
        try{
            return new ResponseEntity<>(
                    courseService.addCourse(course),
                    HttpStatus.OK
            );
        }catch (DataIntegrityViolationException exception){
            return new ResponseEntity<>(
                    new ApiResponse("Some of your data is duplicate"),HttpStatus.BAD_REQUEST
            );
        }

    }

    @PutMapping
    public ResponseEntity<?> updateCourse(@RequestBody Course course){
        try{
            return new ResponseEntity<>(new ApiResponse("The information has been updated successfully",
                    courseService.updateCourse(course)),HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(new ApiResponse("No course found with the given ID"),HttpStatus.BAD_REQUEST);
        }
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
