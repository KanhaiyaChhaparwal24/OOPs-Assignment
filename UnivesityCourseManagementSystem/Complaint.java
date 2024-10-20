public class Complaint {
    private String description;
    private String status;
    private String solution;
    private static int idCounter = 1; 
    private int id;

    public Complaint(String description) {
        this.id = idCounter++;
        this.description = description;
        this.status = "Pending";
        this.solution = null;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getsolution() {
        return solution;
    }

    public void setsolution(String solution) {
        this.solution = solution;
    }
}
