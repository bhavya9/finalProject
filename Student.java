package attendance.dao;

public class Student {
    public boolean saveStudent(StudentDetail student);
    
    public StudentDetail getStudentByGrNo(Integer grNo);
    
    public StudentDetail parentsLogin(String mobileNo,Integer srNo);
    
    public List<StudentDetail> getListOfAllSrudent();
    public List<StudentDetail> getListOfStudentByStandard(String standard);


    public List<AttendenceView> getAllRollNumberOfStandard(String standard);

    public List<StudentDetail> getAbsentStudentDetail(String standard,String dateOfAttendance);

    public List<AttendenceView> getAlreadyAttendence(String standard, String dateOfAttendance) ;
    

}
