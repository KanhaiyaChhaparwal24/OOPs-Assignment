import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator extends  User {
    Scanner scanner = new Scanner(System.in);
public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

public void addCourse(){
    
    System.out.print("Enter course code: ");
    String code = scanner.nextLine();
    System.out.print("Enter course title: ");
    String title = scanner.nextLine();
    System.out.print("Enter credits: ");
    int credits = scanner.nextInt();
    scanner.nextLine();  // Consume newline

    // Get prerequisites
    List<Course> prerequisites = new ArrayList<>();
    System.out.print("Enter number of prerequisites: ");
    int numPrerequisites = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    for (int i = 0; i < numPrerequisites; i++) {
        System.out.print("Enter prerequisite course code: ");
        String prerequisiteCode = scanner.nextLine();
        Course prerequisiteCourse = findCourseByCode(prerequisiteCode, prerequisites);
        if (prerequisiteCourse != null) {
            prerequisites.add(prerequisiteCourse);
        } else {
            System.out.println("Prerequisite course not found: " + prerequisiteCode);
        }
    }
    // Get course timings
    System.out.print("Enter course timings: ");
    String timings = scanner.nextLine();

    Course newCourse = new Course(code, title, credits, prerequisites, timings);
    manageCourseCatalog(newCourse, "add");
}

private void manageCourseCatalog(Course newCourse, String string) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'manageCourseCatalog'");
}

public void deleteCourse() {
    System.out.print("Enter course code to delete: ");
    String deleteCode = scanner.nextLine();
    Course courseToDelete = findCourseByCode(deleteCode, null);
    if (courseToDelete != null) {
        manageCourseCatalog(courseToDelete, "delete");
    } else {
        System.out.println("Course not found.");
    }
}

public void updateStudentGrades() {
    System.out.print("Enter completed course code: ");
    String completedCode = scanner.nextLine();
    Course completedCourse = findCourseByCode(completedCode, null);
    if (completedCourse != null)
         {
            System.out.print("Enter grade: ");
            String grade = scanner.nextLine();
            CompletedCourse completed = new CompletedCourse(completedCourse, grade);
            Object student = null;
            manageStudentRecords(student, completed);
        }
}
private void manageStudentRecords(Object student, CompletedCourse completed) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'manageStudentRecords'");
}

public void getStudentRecords() {
    User student = null;
    System.out.println("Name: " + student.getName());
    System.out.println("Registered Courses:");
}

public void manageProfs(){
    System.out.print("Enter professor email: ");
    String professorEmail = scanner.nextLine();
    Professor professor = findProfessorByEmail(professorEmail);
    if (professor != null) {
        System.out.print("Enter course code to assign: ");
        String assignCode = scanner.nextLine();
        Course assignCourse = findCourseByCode(assignCode, null);
        if (assignCourse != null) {
            assignProfessorToCourse(professor, assignCourse);
        } else {
            System.out.println("Course not found.");
        }
    } else {
        System.out.println("Professor not found.");
    }
}
private void assignProfessorToCourse(Professor professor, Course assignCourse) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'assignProfessorToCourse'");
}

private Professor findProfessorByEmail(String professorEmail) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findProfessorByEmail'");
}

public void manageComplaints(){
    System.out.println("Complaints:");
    Complaint[] allComplaints = null;
    for (Complaint complaint : allComplaints) {
        System.out.println("ID: " + complaint.getId() + " | Description: " + complaint.getDescription() + " | Status: " + complaint.getStatus());
    }
    System.out.print("Enter complaint ID to handle: ");
    int complaintId = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    Complaint complaint = findComplaintById(complaintId);
    if (complaint != null) {
        System.out.print("Enter new status: ");
        String status = scanner.nextLine();
        System.out.print("Enter resolution: ");
        String resolution = scanner.nextLine();
        handleComplaint(complaint, status, resolution);
    } else {
        System.out.println("Complaint not found.");
    }
}

    private void handleComplaint(Complaint complaint, String status, String resolution) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handleComplaint'");
}

    private Complaint findComplaintById(int complaintId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findComplaintById'");
}

    @Override
    public void viewAvailableCourses() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private static Course findCourseByCode(String courseCode, List<Course> courseCatalog) {
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }


}