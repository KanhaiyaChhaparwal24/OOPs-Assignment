import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private List<Course> courses;

    public Professor(String name, String email, String password) {
        super(name, email, password);
        this.courses = new ArrayList<>();
    }

    @Override
    public void viewAvailableCourses() {
        System.out.println("Courses assigned to Professor " + getName() + ":");
        for (Course course : courses) {
            System.out.println(course.getTitle() + " - " + course.getCourseCode());
        }
    }

    public void updateCourseDetails(Course course, String detailType, String newValue) {
        if (courses.contains(course)) {
            switch (detailType.toLowerCase()) {
                case "credits":
                    course.setCredits(Integer.parseInt(newValue));
                    System.out.println("Updated credits for course " + course.getTitle());
                    break;
                case "timings":
                    course.setTimings( newValue);
                    System.out.println("Updated timings for course " + course.getTitle());
                    break;
                default:
                    System.out.println("Detail type not recognized.");
            }
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }

    public void viewEnrolledStudents(Course course) {
        if (courses.contains(course)) {
            System.out.println("Students enrolled in " + course.getTitle() + ":");
            for (Student student : course.getEnrolledStudents()) {
                System.out.println(student.getName() + " - Semester " + student.getSemester());
            }
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course " + course.getTitle() + " assigned to Professor " + getName());
    }
}