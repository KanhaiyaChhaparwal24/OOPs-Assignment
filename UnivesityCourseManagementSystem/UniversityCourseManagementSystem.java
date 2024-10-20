import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityCourseManagementSystem {
    private static List<Course> courseCatalog = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<Professor> professors = new ArrayList<>();
    static List<Complaint> allComplaints = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-----------------------------");
        System.out.println("  University Course Management System");
        System.out.println("-----------------------------");
        initializeSampleData();

        boolean running = true;
        while (running) {
            System.out.println("\n-----------------------------");
            System.out.println("1. Enter the Application");
            System.out.println("2. Register as Student");
            System.out.println("3. Register as Professor");
            System.out.println("4. Exit the Application");
            System.out.println("-----------------------------\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Login as:");
                    System.out.println("-----------------------------");
                    System.out.println("1. Student");
                    System.out.println("2. Professor");
                    System.out.println("3. Administrator");
                    System.out.println("-----------------------------\n");
                    System.out.print("Choose an option: ");
                    int role = scanner.nextInt();
                    scanner.nextLine();  

                    switch (role) {
                        case 1:
                            handleStudent(scanner);
                            break;
                        case 2:
                            handleProfessor(scanner);
                            break;
                        case 3:
                            handleAdministrator(scanner);
                            break;
                        default:
                            System.out.println("Invalid option. -- try again --");
                            break;
                    }
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    registerProfessor(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application....");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. -- try again --");
                    break;
            }
        }

    }

    public static void handleStudent(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = findStudent(username, password);
        if (student == null) {
            System.out.println("Invalid. -- try again --");
            return;
        }

        boolean studentRunning = true;
        while (studentRunning) {
            System.out.println("\n-----------------------------");
            System.out.println("Student functionalities:");
            System.out.println("-----------------------------");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Schedule");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Submit a Complaint");
            System.out.println("7. View Complaints");
            System.out.println("8. Logout");
            System.out.println("-----------------------------\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    student.viewCatalogAvailableCourses(courseCatalog);
                    break;

                case 2:
                    Course selectedCourse = selectCourse(scanner);
                    if (selectedCourse != null) {
                        if (student.getCompletedCourses().contains(selectedCourse)) {
                            System.out.println("Course already completed.");
                        } else {
                            student.registerForCourse(selectedCourse);
                        }
                    }
                    else{
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    Course courseToDrop = selectRegisteredCourse(student, scanner);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    }
                    break;
                case 4:
                    student.viewSchedule();
                    break;
                case 5:
                    double score = student.calculateCGPA();
                    System.out.println("Current CGPA: " + score);
                    break;
                case 6:
                    System.out.print("Enter description of complaint: ");
                    String complaintDescription = scanner.nextLine();
                    student.submitComplaint(complaintDescription);
                    break;
                case 7:
                    student.viewComplaints();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    studentRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. -- try again --");
                    break;
            }
        }
    }

    public static void handleProfessor(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Professor professor = findProfessor(username, password);
        if (professor == null) {
            System.out.println("Invalid. -- try again --");
            return;
        }

        boolean professorRunning = true;
        while (professorRunning) {
            System.out.println("\n-----------------------------");
            System.out.println("Professor functionalities:");
            System.out.println("-----------------------------");
            System.out.println("1. View Available Courses");
            System.out.println("2. Update Course Details");
            System.out.println("3. View Enrolled Students");
            System.out.println("4. Logout");
            System.out.println("-----------------------------\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    professor.viewAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter course code to update: ");
                    String courseCode = scanner.nextLine();
                    Course courseToUpdate = findCourseByCode(courseCode);
                    if (courseToUpdate != null) {
                        System.out.println("Enter detail type to update (credits/timings): ");
                        String detailType = scanner.nextLine();
                        System.out.print("Enter new value: ");
                        String newValue = scanner.nextLine();
                        professor.updateCourseDetails(courseToUpdate, detailType, newValue);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter course code: ");
                    String enrolledCourseCode = scanner.nextLine();
                    Course enrolledCourse = findCourseByCode(enrolledCourseCode);
                    if (enrolledCourse != null) {
                        professor.viewEnrolledStudents(enrolledCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out...");
                    professorRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. -- try again --");
                    break;
            }
        }
    }

    public static void handleAdministrator(Scanner scanner){
        System.out.print("Enter password (admin123): ");
        String password = scanner.nextLine();
        if (!password.equals("admin123")) { 
            System.out.println("Invalid password. -- try again --");
            return;
        }

        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n---------------------------");
            System.out.println("Administrator functionalities:");
            System.out.println("---------------------------");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign Professor to Course");
            System.out.println("4. Handle Complaints");
            System.out.println("5. Logout");
            System.out.println("---------------------------\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("1. Add Course");
                    System.out.println("2. Delete Course");
                    System.out.print("Choose an option: ");
                    int catalogAction = scanner.nextInt();
                    scanner.nextLine();
                    if (catalogAction == 1) {
                        System.out.print("Enter course code: ");
                        String code = scanner.nextLine();
                        System.out.print("Enter course title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter credits: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();

                        List<Course> prerequisites = new ArrayList<>();
                        System.out.print("Enter number of prerequisites: ");
                        int numPrerequisites = scanner.nextInt();
                        scanner.nextLine(); 
                        for (int i = 0; i < numPrerequisites; i++) {
                            System.out.print("Enter prerequisite course code: ");
                            String prerequisiteCode = scanner.nextLine();
                            Course prerequisiteCourse = findCourseByCode(prerequisiteCode);
                            if (prerequisiteCourse != null) {
                                prerequisites.add(prerequisiteCourse);
                            } else {
                                System.out.println("Prerequisite course not found: " + prerequisiteCode);
                            }
                        }

                        System.out.print("Enter course timings: ");
                        String timings = scanner.nextLine();

                        Course newCourse = new Course(code, title, credits, prerequisites, timings);
                        manageCourseCatalog(newCourse, "add");
                    } else if (catalogAction == 2) {
                        System.out.print("Enter course code to delete: ");
                        String deleteCode = scanner.nextLine();
                        Course courseToDelete = findCourseByCode(deleteCode);
                        if (courseToDelete != null) {
                            manageCourseCatalog(courseToDelete, "delete");
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;
                case 2:
                    System.out.print("Enter student username: ");
                    String studentusername = scanner.nextLine();
                    Student student = findStudentByusername(studentusername);
                    if (student != null) {
                        System.out.println("1. View Record");
                        System.out.println("2. Update Grade");
                        System.out.print("Enter choice: ");
                        int select = scanner.nextInt();
                        scanner.nextLine();
                        if(select==2){
                            System.out.print("Enter completed course code: ");
                            String completedCode = scanner.nextLine();
                            Course completedCourse = findCourseByCode(completedCode);
                            if (completedCourse != null) {                        
                                System.out.print("Enter grade(AA/AB/BB/BC/CC/FF): ");
                                String grade = scanner.nextLine();
                                CompletedCourse completed = new CompletedCourse(completedCourse, grade);
                                manageStudentRecords(student, completed);                                                            
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                        else if(select==1){
                            System.out.println("Name: " + student.getName());
                            System.out.println("Semester: " + student.getSemester());
                            System.out.println("Registered Courses:");
                            for(Course course : student.getRegisteredCourses()){
                                System.out.println(course.getCourseCode() + " - " + course.getTitle());
                            }
                            System.out.println("Completed Courses:");
                            for(CompletedCourse completedCourse : student.getCompletedCourses()){
                                System.out.println(completedCourse.getCourse().getTitle() + "(" + completedCourse.getCourse().getCourseCode() + ")" + " | Grade: " + completedCourse.getGrade());
                            }
                        }
                        else{
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter professor username: ");
                    String professorusername = scanner.nextLine();
                    Professor professor = findProfessorByusername(professorusername);
                    if (professor != null) {
                        System.out.print("Enter course code to assign: ");
                        String assignCode = scanner.nextLine();
                        Course assignCourse = findCourseByCode(assignCode);
                        if (assignCourse != null) {
                            assignProfessorToCourse(professor, assignCourse);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Professor not found.");
                    }
                    break;
                case 4:
                    System.out.println("Complaints:");
                    for (Complaint complaint : allComplaints) {
                        System.out.println("ID: " + complaint.getId() + " | Description: " + complaint.getDescription() + " | Status: " + complaint.getStatus());
                    }

                    System.out.print("Enter complaint ID to handle: ");
                    int complaintId = scanner.nextInt();
                    scanner.nextLine();
                    Complaint complaint = findComplaintById(complaintId);
                    if (complaint != null) {
                        System.out.print("Enter new status: ");
                        String status = scanner.nextLine();
                        System.out.print("Enter solution: ");
                        String solution = scanner.nextLine();
                        handleComplaint(complaint, status, solution);
                    } else {
                        System.out.println("Complaint not found.");
                    }
                    break;

                case 5:
                    System.out.println("Logging out...");
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. -- try again --");
                    break;
            }
        }
    }

    private static void initializeSampleData() {
        List<Course> prerequisitesForAI201 = new ArrayList<>();
        Course course1 = new Course("AI201", "CO", 4, prerequisitesForAI201, "Mon-Wed 8-10");List<Course> prerequisitesForAI203 = new ArrayList<>();
        Course course2 = new Course("AI203", "DBMS", 4, prerequisitesForAI203, "Wed-Fri 8-10");List<Course> prerequisitesForAI205 = new ArrayList<>();
        Course course3 = new Course("AI205", "DAA", 4, prerequisitesForAI205, "Mon-Wed 10-12");List<Course> prerequisitesForAI207 = new ArrayList<>();
        Course course4 = new Course("AI207", "DM", 4, prerequisitesForAI207, "Wed-Fri 10-12");List<Course> prerequisitesForAI231 = new ArrayList<>();
        Course course5 = new Course("AI231", "OOP", 4, prerequisitesForAI231, "Mon-Fri 2-3");

        courseCatalog.add(course1);
        courseCatalog.add(course2);
        courseCatalog.add(course3);
        courseCatalog.add(course4);
        courseCatalog.add(course5);

        students.add(new Student("Kanhaiya", "kanu", "123", 3));
        students.add(new Student("Divyaksh", "dc", "123", 3));
        students.add(new Student("Harsh", "harsh", "123", 3));
        students.add(new Student("Krupal", "don", "123", 3));

        professors.add(new Professor("Dr. Praveen", "praveen", "123"));
        professors.add(new Professor("Dr. Tanmoy", "tanmoy", "123"));
    }

    private static Student findStudent(String username, String password) {
        for (Student student : students) {
            if (student.getusername().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    private static Professor findProfessor(String username, String password) {
        for (Professor professor : professors) {
            if (professor.getusername().equals(username) && professor.getPassword().equals(password)) {
                return professor;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudentByusername(String username) {
        for (Student student : students) {
            if (student.getusername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    private static Professor findProfessorByusername(String username) {
        for (Professor professor : professors) {
            if (professor.getusername().equals(username)) {
                return professor;
            }
        }
        return null;
    }

    private static Complaint findComplaintById(int id) {
        for (Complaint complaint : allComplaints) {
            if (complaint.getId() == id) {
                return complaint; 
            }
        }
        return null; 
    }

    // Manage course catalog
    public static void manageCourseCatalog(Course course, String action) {
        switch (action.toLowerCase()) {
            case "add" -> {
                courseCatalog.add(course);
                System.out.println("Course " + course.getTitle() + " added to the catalog.");
            }
            case "delete" -> {
                courseCatalog.remove(course);
                System.out.println("Course " + course.getTitle() + " removed from the catalog.");
            }
            default -> System.out.println("Invalid action. Please choose 'add' or 'delete'.");
        }
    }

    public static void manageStudentRecords(Student student, CompletedCourse completedCourse) {
        if (student.getRegisteredCourses().remove(completedCourse.getCourse())) {
            student.addCompletedCourse(completedCourse);
            System.out.println("Updated student " + student.getName() + "'s record for course: " + completedCourse.getCourse().getTitle());
        }
        else{
            System.out.println("Student " + student.getName() + " not enrolled in " + completedCourse.getCourse().getTitle());
        }
    }

    public static void assignProfessorToCourse(Professor professor, Course course) {
        professor.addCourse(course);
        course.setProfessor(professor);
        System.out.println("Assigned Professor " + professor.getName() + " to course " + course.getTitle());
    }

    public static void handleComplaint(Complaint complaint, String status, String solution) {
        complaint.setStatus(status);
        complaint.setsolution(solution);
        System.out.println("Complaint handled: " + complaint.getDescription() + " | Status: " + status + " | solution: " + solution);
    }

    private static Course selectCourse(Scanner scanner) {
        System.out.println("Available courses:");
        for (Course course : courseCatalog) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credits)");
        }
        System.out.print("Enter course code to select: ");
        String selectedCode = scanner.nextLine();
        return findCourseByCode(selectedCode);
    }

    private static Course selectRegisteredCourse(Student student, Scanner scanner) {
        System.out.println("Registered courses:");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle());
        }
        System.out.print("Enter course code to drop: ");
        String droppedCode = scanner.nextLine();
        return student.getRegisteredCourses().stream().filter(c -> c.getCourseCode().equals(droppedCode)).findFirst().orElse(null);
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        int semester = -1;
        while (true) {
            System.out.print("Enter student semester: ");
            semester = scanner.nextInt();
            scanner.nextLine();
            break;
        }

        Student newStudent = new Student(name, username, password, semester);
        students.add(newStudent);
        System.out.println("Student " + name + " registered successfully.");
    }

    private static void registerProfessor(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Professor newProfessor = new Professor(name, username, password);
        professors.add(newProfessor);
        System.out.println("Professor " + name + " registered successfully.");
    }
}