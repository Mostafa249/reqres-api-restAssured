package models;

public class User {

    private String name;
    private String job;
    private int age;

    public User(String name, String job, int age) {
        this.name = name;
        this.job = job;
        this.age = age;
    }

    public String getName() { return name; }
    public String getJob() { return job; }
    public int getAge() { return age; }

    public void setJob(String job) { this.job = job; }
}
