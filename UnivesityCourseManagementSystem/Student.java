import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private int semester;
    private List<Course> registeredCourses;
    private List<CompletedCourse> completedCourses;
    private List<Complaint> complaints;

    public Student(String name, String username, String password, int semester) {
        super(name, username, password);
        this.semester = semester;
        this.registeredCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.complaints = new ArrayList<>();
    }

    @Override
    public void viewAvailableCourses() {
        System.out.println("Displaying all available courses:");
        CourseCatalog catalog = new CourseCatalog();
        catalog.displayCourses();
    }

    public boolean registerForCourse(Course course) {
        if (canRegister(course)) {
            course.enrollStudent(this);
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
            return true;
        } else {
            System.out.println("Cannot register for course: " + course.getTitle());
            return false;
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropEnrolledStudent(this);
            registeredCourses.remove(course);
            System.out.println("Course dropped: " + course.getTitle());
        } else {
            System.out.println("Course not found in registered courses.");
        }
    }

    public void viewSchedule() {
        System.out.println("Schedule for semester: " + semester);
        for (Course course : registeredCourses) {
            System.out.println(course.getTitle() + " - " + course.getTimings());
        }
    }

    public double calculateCGPA() {
        double totalCredits = 0;
        double totalPoints = 0;

        for (CompletedCourse completed : completedCourses) {
            double gradePoints = getGradePoints(completed.getGrade());
            totalCredits += completed.getCourse().getCredits();
            totalPoints += gradePoints * completed.getCourse().getCredits();
            System.out.println(completed.getCourse().getTitle() + ": Grade: " + completed.getGrade());
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    private double getGradePoints(String grade) {
        switch (grade) {
            case "AA": return 10.0;
            case "AB": return 9.0;
            case "BB": return 8.0;
            case "BC": return 7.0;
            case "CC": return 6.0;
            case "FF": return 1.0;
            default: return 0.0;
        }
    }

    public void submitComplaint(String description) {
        Complaint newComplaint = new Complaint(description);
        complaints.add(newComplaint);
        UniversityCourseManagementSystem.allComplaints.add(newComplaint);
        System.out.println("Complaint submitted: " + description);
    }


    public void viewComplaints() {
        System.out.println("Complaints:");
        for (Complaint complaint : UniversityCourseManagementSystem.allComplaints) {
            if (complaint.getStatus().equals("Pending")) {
                System.out.println("ID: " + complaint.getId() + " | Description: " + complaint.getDescription() + " | Status: " + complaint.getStatus());
            }
            else{
                System.out.println("ID: " + complaint.getId() + " | Description: " + complaint.getDescription() + " | Status: " + complaint.getStatus() + " | solution: " + complaint.getsolution());
            }
        }

    }

    private boolean canRegister(Course course) {
        int currentCredits = registeredCourses.stream().mapToInt(Course::getCredits).sum();
        return currentCredits + course.getCredits() <= 20;  
    }

    public int getSemester() {
        return semester;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public List<CompletedCourse> getCompletedCourses(){
        return completedCourses;
    }

    public void addCompletedCourse(CompletedCourse completedCourse) {
        completedCourses.add(completedCourse);
        System.out.println("Completed course added: " + completedCourse.getCourse().getTitle());
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void getAttandence(){
        System.out.println("Enter total number of lectures : ");
        Scanner sc =  new Scanner(System.in);
        int  totalLectures = sc.nextInt();  
        System.out.println("Enter number of lectures attended : ");
        int  attendedLectures = sc.nextInt();
        double attendance = ((double)attendedLectures/(double)totalLectures)*100;
        System.out.println("Attendance percentage : "+attendance+" %");
        if (attendance>=75){
            System.out.println("You are eligible for the next semester");
        } else{
            System.out.println("You are not eligible for the next semester");
        }
    }
}