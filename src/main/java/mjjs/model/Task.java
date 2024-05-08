package mjjs.model;

public class Task {
    private String teacherName;
    private int numCopies;
    private String receptionDate;
    private String document;

    // Constructor
    public Task(String teacherName, int numCopies, String receptionDate, String document) {
        this.teacherName = teacherName;
        this.numCopies = numCopies;
        this.receptionDate = receptionDate;
        this.document = document;
    }

    // Getters and setters
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}

