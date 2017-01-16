package com.twh.wsam.data;

import java.util.List;

import com.twh.wsam.data.entity.Student;

public interface StudentsDataSource {
	void saveStudent(Student student);
	void deleteStudent(Student student);
	List<Student> getStudentsByName(String name);
	/**
	 * 根据学号查询
	 * @param studentId
	 * @return
	 */
	List<Student> getStudentsByStudentId(String studentId);
	/**
	 * 根据考试时间查询
	 * @param time
	 * @return
	 */
	List<Student> getStudentsByExamTime(String time);
}
