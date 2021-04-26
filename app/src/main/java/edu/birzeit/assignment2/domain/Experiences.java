package edu.birzeit.assignment2.domain;

public class Experiences {
    private String education;
    private String work;

    public Experiences() {
        super();
    }

    public Experiences(String education, String work) {
        this.education = education;
        this.work = work;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Experiences{" +
                "education='" + education + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
