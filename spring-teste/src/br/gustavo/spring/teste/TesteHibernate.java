//package br.gustavo.spring.teste;
//
//import java.util.List;
//
//import br.gustavo.spring.dao.StudentDao;
//import br.gustavo.spring.model.Student;
//
//public class TesteHibernate {
//	
//	public static void main(String[] args) {
//		
//		Student student = new Student();
//		
//		student.setFirstName("Gustavo Martins");
//		student.setLastName("Marinelli");
//		student.setCountry("Brasil");
//		
//		StudentDao dao = new StudentDao();
//		
//		dao.saveStudent(student);
//		
//		List<Student> students = dao.getStudents();
//		students.forEach(s -> System.out.println(s.getFirstName()));
//	}
//
//}
