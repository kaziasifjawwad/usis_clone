package com.brainstation.usisclone.controller;
import com.brainstation.usisclone.models.Faculty;
import com.brainstation.usisclone.payload.ApiResponse;
import com.brainstation.usisclone.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<?> getAllFaculty(){
        return new ResponseEntity<>(facultyService.getAllFaculties(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(facultyService.findFacultyById(id),HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(
                    new ApiResponse("No faculty found with this ID"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> postFaculty(@RequestBody Faculty faculty){
        try{
            return new ResponseEntity<>(
                    facultyService.addFaculty(faculty),
                    HttpStatus.OK
            );
        }catch (DataIntegrityViolationException exception){
            return new ResponseEntity<>(
                    new ApiResponse("Some of your data is duplicate"),HttpStatus.BAD_REQUEST
            );
        }

    }

    @PutMapping
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty){
        try{
            return new ResponseEntity<>(new ApiResponse("The information has been updated successfully",
                    facultyService.updateFaculty(faculty)),HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(new ApiResponse("No faculty found with the given ID"),HttpStatus.BAD_REQUEST);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            return new ResponseEntity<>(new ApiResponse("Some of the updated data already exists."),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable Long id){
        try{
            return new ResponseEntity<>( new ApiResponse("The information has been deleted successfully",
                    facultyService.deleteFacultyById(id)),HttpStatus.OK);
        }catch (NoSuchElementException e){
            ApiResponse apiResponse = new ApiResponse("Something went wrong");
            return new ResponseEntity<>( apiResponse ,HttpStatus.BAD_REQUEST);
        }
    }

}
