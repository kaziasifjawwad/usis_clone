package com.brainstation.usisclone.services;
import com.brainstation.usisclone.models.Course;
import com.brainstation.usisclone.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) throws DataIntegrityViolationException {
        return courseRepository.save(course);
    }

    public Course deleteCourseById(Long id) throws NoSuchElementException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            courseRepository.delete(course);
            return course;
        }else throw new NoSuchElementException();
    }

    public Course updateCourse(Course course) throws DataIntegrityViolationException,NoSuchElementException{
        if (courseRepository.findById(course.getCourse_id()).isEmpty()) throw new NoSuchElementException();
        return courseRepository.save(course);
    }

    public Course findCourseById(Long id) throws  NoSuchElementException{
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            return optionalCourse.get();
        }else throw new NoSuchElementException();
    }
}
