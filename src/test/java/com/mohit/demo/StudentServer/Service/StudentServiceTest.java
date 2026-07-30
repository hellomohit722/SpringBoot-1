package com.mohit.demo.StudentServer.Service;

import com.mohit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.mohit.demo.StudentServer.DTO.UpdateStudentRequestDTO;
import com.mohit.demo.StudentServer.Entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mohit.demo.StudentServer.Repository.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//
//@SpringBootTest
//class StudentServiceTest {
//    @Autowired
//    private StudentService studentService;
//    @Test
//    void getStudentByIdTest() {
//        CreateStudentRequestDTO dto = new CreateStudentRequestDTO();
//        dto.setName("Mohit");
//        dto.setAge(22);
//        dto.setDepartment("CSE");
//        var response = studentService.studentValidate(dto);
//        Student student = studentService.getStudentById(response.getId());
//        Assertions.assertNotNull(student);
//        Assertions.assertEquals("Mohit", student.getName());
//        Assertions.assertEquals(22, student.getAge());
//        Assertions.assertEquals("CSE", student.getDepartment());
//    }
//
//    @Test
//    void updateStudentTest() {
//
//        CreateStudentRequestDTO dto = new CreateStudentRequestDTO();
//        dto.setName("Rahul");
//        dto.setAge(20);
//        dto.setDepartment("IT");
//
//        var saved = studentService.studentValidate(dto);
//
//        UpdateStudentRequestDTO update = new UpdateStudentRequestDTO();
//        update.setName("Mohit");
//        update.setAge(25);
//
//        studentService.studentUpdate(saved.getId(), update);
//
//        Student student = studentService.getStudentById(saved.getId());
//
//        Assertions.assertEquals("Mohit", student.getName());
//        Assertions.assertEquals(25, student.getAge());
//    }
//
//    @Test
//    void deleteStudentTest() {
//
//        CreateStudentRequestDTO dto = new CreateStudentRequestDTO();
//        dto.setName("Amit");
//        dto.setAge(21);
//        dto.setDepartment("ECE");
//
//        var saved = studentService.studentValidate(dto);
//
//        Student deleted = studentService.deleteStudent(saved.getId());
//
//        Assertions.assertNotNull(deleted);
//
//        Assertions.assertThrows(Exception.class, () -> {
//            studentService.getStudentById(saved.getId());
//        });
//    }
//}
//
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getStudentByIdTest() {
        Student student = new Student();
        student.setId(1);
        student.setName("Mohit");
        student.setAge(22);
        student.setDepartment("CSE");

        when(studentRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(student));


        Student result = studentService.getStudentById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Mohit", result.getName());
        assertEquals(22, result.getAge());
        assertEquals("CSE", result.getDepartment());

        verify(studentRepository).findById(1);
    }
}