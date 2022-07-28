package com.brainstation.usisclone.services;

import com.brainstation.usisclone.models.Course;
import com.brainstation.usisclone.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    public Course deleteCourseById(Long id) throws NoSuchElementException {
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);
        return course;
    }
}
