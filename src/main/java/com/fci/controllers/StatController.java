package com.fci.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.dao.GuardianshipRepo;
import com.fci.dao.LevelRepo;
import com.fci.dao.StudentRepo;
import com.fci.dao.SubjectRepo;
import com.fci.dao.TeacherRepo;
import com.fci.dao.WorkerRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "school/stat")
public class StatController {

	@Autowired
	private TeacherRepo teacherRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private WorkerRepo workerRepo;

	@Autowired
	private LevelRepo levelRepo;

	@Autowired
	private SubjectRepo subjectRepo;

	@Autowired
	private GuardianshipRepo guardianshipRepo;

	/**
	 * @return: get overall school statistics teachers, students, workers,...
	 */
	@GetMapping(value = { "/", "" })
	public Map<String, Long> getSchoolStat() {
		Map<String, Long> stat = new HashMap<>();
		stat.put("teachers", teacherRepo.count());
		stat.put("students", studentRepo.count());
		stat.put("workers", workerRepo.count());
		stat.put("levels", levelRepo.count());
		stat.put("subject", subjectRepo.count());
		stat.put("guardianships", guardianshipRepo.count());
		return stat;
	}

	/**
	 * @return: get all student statistics example will get statistics with <br>
	 *          [{male,100},{female,99}]
	 */
	@GetMapping("/student")
	public List<Object> getStudentStat() {
		return studentRepo.countTotalStudentByGender();
	}

	@GetMapping("/level")
	public List<Object> getLevelStat() {
		studentRepo.countTotalStudentByLevel().forEach(item -> {
			System.out.println(Arrays.toString(item.getClass().getDeclaredFields()));
//			try {
//				System.out.println(item.getClass().getName());
//			} catch (NoSuchFieldException | SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		});
		return null;
	}

}
