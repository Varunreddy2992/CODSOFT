import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean canRegister() {
        return registeredStudents < capacity;
    }

    public void registerStudent() {
        if (canRegister()) {
            registeredStudents++;
        }
    }

    public void removeStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
        }
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.canRegister()) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println("Course registered: " + course.getTitle());
        } else {
            System.out.println("Course is already full. Registration failed.");
        }
    }

    public void removeCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println("Course removed: " + course.getTitle());
        } else {
            System.out.println("You are not registered in this course.");
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "An introductory course in computer science.", 30, "MWF 10:00 AM - 11:30 AM");
        Course course2 = new Course("MAT201", "Linear Algebra", "A course in linear algebra.", 25, "TTH 1:00 PM - 2:30 PM");

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();

        Student student = new Student(studentID, studentName);

        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Remove a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableCourses(courses);
                    break;
                case 2:
                    registerCourse(student, courses, scanner);
                    break;
                case 3:
                    removeCourse(student, scanner);
                    break;
                case 4:
                    viewRegisteredCourses(student);
                    break;
                case 5:
                    System.out.println("Thank you for using the Course Registration System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void viewAvailableCourses(List<Course> courses) {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + (course.getCapacity() - course.getRegisteredStudents()));
            System.out.println();
        }
    }

    public static void registerCourse(Student student, List<Course> courses, Scanner scanner) {
        viewAvailableCourses(courses);
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.next();

        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            student.registerCourse(selectedCourse);
        } else {
            System.out.println("Course not found.");
        }
    }

    public static void removeCourse(Student student, Scanner scanner) {
        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
            return;
        }

        System.out.println("Registered Courses:");
        for (int i = 0; i < registeredCourses.size(); i++) {
            Course course = registeredCourses.get(i);
            System.out.println((i + 1) + ". " + course.getTitle() + " (" + course.getCourseCode() + ")");
        }

        System.out.print("Enter the number of the course to remove: ");
        int courseNumber = scanner.nextInt();

        if (courseNumber >= 1 && courseNumber <= registeredCourses.size()) {
            Course courseToRemove = registeredCourses.get(courseNumber - 1);
            student.removeCourse(courseToRemove);
        } else {
            System.out.println("Invalid course number.");
        }
    }

    public static void viewRegisteredCourses(Student student) {
        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
            return;
        }

        System.out.println("\nRegistered Courses:");
        for (Course course : registeredCourses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println();
        }
    }
}
