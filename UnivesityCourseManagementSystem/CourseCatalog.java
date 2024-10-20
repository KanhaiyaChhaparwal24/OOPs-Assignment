import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
    private List<Course> courses;

    public CourseCatalog() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course " + course.getTitle() + " added to the catalog.");
    }

    public void removeCourse(String courseCode) {
        Course courseToRemove = null;
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            System.out.println("Course " + courseToRemove.getTitle() + " removed from the catalog.");
        } else {
            System.out.println("Course with code " + courseCode + " not found in the catalog.");
        }
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        System.out.println("Course with code " + courseCode + " not found.");
        return null;
    }

    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available in the catalog.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courses) {
                System.out.println(course.getCourseCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credits)");
            }
        }
    }
}