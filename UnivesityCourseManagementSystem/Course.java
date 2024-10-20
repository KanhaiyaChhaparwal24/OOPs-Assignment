import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String title;
    private Professor professor;
    private int credits;
    private List<Course> prerequisites;
    private List<Student> enrolledStudents;
    private String timings;

    public Course(String courseCode, String title, int credits, List<Course> prerequisites, String timings) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.timings = timings;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
        System.out.println("Student " + student.getName() + " enrolled in course: " + title);
    }

    public void dropEnrolledStudent(Student student){
        enrolledStudents.remove(student);
        System.out.println("Student " + student.getName() + " dropped from course: " + title);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        System.out.println("Professor " + professor.getName() + " assigned to course: " + title);
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }
}