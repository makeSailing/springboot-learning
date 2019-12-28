package com.makesailing.neo.controller;

import com.makesailing.neo.domain.StudentEntity;
import com.makesailing.neo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/23 21:43
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public List<StudentEntity> getStudentList() {
        return studentRepository.findAll();
    }

    @PostMapping("/add")
    public Long saveStudent(@RequestBody StudentEntity studentEntity) {
        StudentEntity entity = studentRepository.save(studentEntity);
        return entity.getId();
    }

    @PutMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.delete(id);
    }
}
