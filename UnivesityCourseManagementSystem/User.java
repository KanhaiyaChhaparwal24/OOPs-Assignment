import java.util.List;

public abstract class User {
    protected String name;
    protected String username;
    protected String password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public abstract void viewAvailableCourses();

    public String getName() {
        return name;
    }

    public String getusername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void viewCatalogAvailableCourses(List<Course> courseCatalog){
        System.out.println("Displaying available courses:");
        boolean hasAvailableCourses = false;

        for (Course course : courseCatalog) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credits)");
            hasAvailableCourses = true;
        }

        if (!hasAvailableCourses) {
            System.out.println("No courses available.");
        }
    }
}
