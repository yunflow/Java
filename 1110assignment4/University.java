import java.util.*;


/**
 * Represents a University with students and courses.
 * The university class will maintain an index of students and courses using Maps.
 *   - The keys for the students' map is the Student's ID
 *   - The keys for the courses' map is the Course's Code
 *
 * Your task in this class is to finish the constructor and implement the methods flagged with TODO.
 * I've [Juliano] moved all methods already implemented under the ones you have to implement.
 * You can re-organize the code if you want.
 */
public class University {
    private Map<Integer, Student> studentBody;
    private Map<String, Course> availableCourses;
    private String universityName;
    private String universityMotto;


    public University(String universityName, String universityMotto) {
        this.universityName = universityName;
        this.universityMotto = universityMotto;
        studentBody = new HashMap<>();
        availableCourses = new HashMap<>();
    }

    /**
     * Adds a student to the university roster. Student's cannot be added twice.
     * @param student the student to be added
     * @return true if the student was added, false if the student was already on the map
     */
    public boolean addStudent(Student student){
        //TODO: Implement the method according to the javadoc
        if(studentBody.containsKey(student.getIdNumber())){
            return false;
        }else{
            studentBody.put(student.getIdNumber(), student);
            return true;
        }
    }

    /**
     * Gets a student from the university
     * @param idNumber the student's ID.
     * @return the student object or null if not found
     */
    public Student getStudent(int idNumber){
        //TODO: Implement this getter
        if(studentBody.containsKey(idNumber)){
            return studentBody.get(idNumber);
        }else{
            return null;
        }
    }

    /**
     * Returns a list containing all students from the university
     * You will have to work with maps and lists on this method.
     * Referer to the document on how to ITERATE over maps. You CANNOT use foreach directly with maps
     * What is the best List type to use? ArrayList or LinkedList? Why?
     *
     * @return A list of all students (the list will be empty if the university is empty)
     */
    public List<Student> getStudents(){
        //TODO: Implement this method

        //I think ArrayList is enough to solve this problem,
        //LinkedList is more suitable for scenarios that require frequent insertion or removal
        List<Student> studentList = new ArrayList<Student>(studentBody.values());
        return studentList;
    }

    /**
     * Add a course to the courses offered by the university
     * @param course the course object to be added
     * @return true if the course was added, false if the course was already on the university
     */
    public boolean addCourse(Course course){
        //TODO: Implement this method according to the javadocs
        if(availableCourses.containsKey(course.getCourseCode())){
            return false;
        }else{
            availableCourses.put(course.getCourseCode(), course);
            return true;
        }
    }

    /**
     * Adds a course as a pre-requisite to another course. Both courses must already exist
     * in the university's list of offered courses.
     * @param courseID the id of the course you want to add the pre-requisite to
     * @param preReqCourseID the id of the pre-requisite course
     * @return false if either the courseID or preReqCourseID are invalid, true after adding the pre-requisite
     */
    public boolean addRequisiteToCourse(String courseID, String preReqCourseID){
        //TODO: Implement this method
        if(availableCourses.containsKey(courseID) && availableCourses.containsKey(preReqCourseID)){
            availableCourses.get(courseID).getPreRequisites().add(availableCourses.get(preReqCourseID));
            return true;
        }else {
            return false;
        }
    }

    /**
     * Gets a course from the university
     * @param courseCode the course code.
     * @return the course object associated with the code or null if not found
     */
    public Course getCourse(String courseCode){
        //TODO: implement this method
        if(availableCourses.containsKey(courseCode)){
            return availableCourses.get(courseCode);
        }else{
            return null;
        }
    }

    /**
     * Get a list containing all courses offered by the university.
     * You will have to work with maps and lists on this method.
     * Referer to the document on how to ITERATE over maps. You CANNOT use foreach directly with maps
     * What is the best List type to use? ArrayList or LinkedList? Why?
     *
     * @return the list of courses offered by the university
     */
    public List<Course> getCourses(){
        //TODO: Implement this method
        List<Course> courseList = new ArrayList<Course>(availableCourses.values());
        return courseList;
    }

    /**
     * Enroll a student in a course IF the student has already passed the pre-requisites of the course.
     * Note to 1110 students: remember that students keep two lists of courses.
     *
     * The method should add the student to the course's list of students and add the course to the student's
     * list of current courses.
     *
     * @param studentID the id of the student
     * @param courseCode the course code for enrollment.
     * @return false if studentID, courseCode are incorrect, false if the student does not have the pre-requisites
     *         true if the student was enrolled in the course.
     *
     * NOTE2: See how false is representing three different issues? Here the "modern" approach would be to use
     *        exceptions (throw an exception) for the wrong id and code problems.
     */
    public boolean enrollStudentInCourse(int studentID, String courseCode){
        //TODO: Implement this method
        if(studentBody.containsKey(studentID) && availableCourses.containsKey(courseCode)){

            //check that all courses the student has taken include the required pre-requisite courses
            if(studentBody.get(studentID).getPreviousCourses().containsAll(availableCourses.get(courseCode).getPreRequisites())){
                studentBody.get(studentID).getEnrolledCourses().add(availableCourses.get(courseCode));
                availableCourses.get(courseCode).getEnrolledStudents().add(studentBody.get(studentID));
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }
    }

    /**
     * Removes a student from a course IF the student is already enrolled in it.
     * @param studentID the student ID
     * @param courseCode the course code
     * @return false if studentID, courseCode are incorrect, false if the student is not enrolled in the course
     *        true if the student was removed from the course.
     */
    public boolean removeStudentFromCourse(int studentID, String courseCode){
        //TODO: Implement this method
        if(studentBody.containsKey(studentID) && availableCourses.containsKey(courseCode)){

            //check if this student is enrolled in this course
            if(availableCourses.get(courseCode).getEnrolledStudents().contains(studentBody.get(studentID))){
                studentBody.get(studentID).getEnrolledCourses().remove(availableCourses.get(courseCode));
                availableCourses.get(courseCode).getEnrolledStudents().remove(studentBody.get(studentID));
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }
    }

    /**
     * Removes a student from the university.
     * The student will be removed from the university index AND from the list of students of every course that the
     * student was already enrolled.
     *
     * There are a couple of ways to solve this method. If you iterate over the student's own courses,
     *  you have to be careful not to change the collection while you are iterating (unless you remove with the iterator)
     *
     * @param studentID the id of the student to remove
     * @return false if the studentID is not in the index. True if the student was removed from the index and courses
     */
    public boolean removeStudentFromUniversity(int studentID){
        //TODO: Implement this method
        if(studentBody.containsKey(studentID)){

            //iterate through all the courses that already exist, then remove their student
            Iterator iteratorCourse = availableCourses.entrySet().iterator();
            while(iteratorCourse.hasNext()){
                Map.Entry entry = (Map.Entry)iteratorCourse.next();
                Course value = (Course)entry.getValue();
                value.getEnrolledStudents().remove(studentBody.get(studentID));
            }

            studentBody.remove(studentID);
            return true;

        }else {
            return false;
        }
    }

    /**
     * Removes a course from the university.
     * This method should remove the course from the university offered courses
     *     AND from each student's current courses.
     *
     * The method must remove the course from any other courses that have it as a pre-requisite
     *
     * @param courseCode the course code
     * @return false if the course code does not match any offered course. True after the course was removed
     */
    public boolean removeCourseFromUniversity(String courseCode){
        //TODO: Implement this method
        if(availableCourses.containsKey(courseCode)){

            //iterate through all the courses and students that already exist, then remove their course
            Iterator iteratorCourse = availableCourses.entrySet().iterator();
            while(iteratorCourse.hasNext()){
                Map.Entry entry = (Map.Entry)iteratorCourse.next();
                Course value = (Course)entry.getValue();
                value.getPreRequisites().remove(availableCourses.get(courseCode));
            }
            Iterator iteratorStudent = studentBody.entrySet().iterator();
            while(iteratorStudent.hasNext()){
                Map.Entry entry = (Map.Entry)iteratorStudent.next();
                Student value = (Student)entry.getValue();
                value.getEnrolledCourses().remove(availableCourses.get(courseCode));
                value.getPreviousCourses().remove(availableCourses.get(courseCode));
            }

            availableCourses.remove(courseCode);
            return true;

        }else{
            return false;
        }
    }

    public String getUniversityMotto() {
        return universityMotto;
    }

    public String getUniversityName() {
        return universityName;
    }

    /**
     * Add a new student using name and ID
     * @param name the student's name
     * @param studentID the student's ID between 0 and 999999
     * @return true if the student was added, false if the student was already on the university
     */
    public boolean addStudent(String name, int studentID){
        return addStudent(new Student(name,studentID));
    }

    public boolean addCourse(String name, String courseID){
        return addCourse(new Course(name,courseID));
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\nNumber of Students: %d\nNumber of Courses %d",
                universityName,universityMotto,studentBody.size(),availableCourses.size());
    }

}
