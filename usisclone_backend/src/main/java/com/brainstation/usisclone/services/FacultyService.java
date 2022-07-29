package com.brainstation.usisclone.services;
import com.brainstation.usisclone.models.Faculty;
import com.brainstation.usisclone.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    public Faculty addFaculty(Faculty course) throws DataIntegrityViolationException {
        return facultyRepository.save(course);
    }

    public Faculty deleteFacultyById(Long id) throws NoSuchElementException {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if(faculty.isPresent()){
            facultyRepository.delete(faculty.get());
            return faculty.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    public Faculty updateFaculty(Faculty faculty)  throws DataIntegrityViolationException,NoSuchElementException{
        if (facultyRepository.findById(faculty.getFaculty_id()).isEmpty()) throw new NoSuchElementException();
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) throws  NoSuchElementException{
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()){
            return faculty.get();
        }else{
            throw new NoSuchElementException();
        }
    }
}
