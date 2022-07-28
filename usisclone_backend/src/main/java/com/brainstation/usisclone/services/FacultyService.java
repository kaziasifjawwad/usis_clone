package com.brainstation.usisclone.services;



import com.brainstation.usisclone.models.Faculty;
import com.brainstation.usisclone.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id){
        Faculty faculty = facultyRepository.findById(id).get();
        if(faculty==null) throw new NoSuchElementException();
        return faculty;
    }

    public Faculty addFaculty(Faculty faculty) throws Exception{
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFacultyById(Long id){
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.delete(faculty);
        return faculty;
    }

}
