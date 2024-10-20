public class CompletedCourse {
    private Course course;
    private String grade;

    public CompletedCourse(Course course, String grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }
}