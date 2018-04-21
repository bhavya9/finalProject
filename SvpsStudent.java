package attendance.dao.SVPS;

public class SvpsStudent {
    public boolean saveStudent(SvpsStudentDetail student);
    
    public SvpsStudentDetail getStudentByGrNo(Integer grNo);
    
    public SvpsStudentDetail parentsLogin(String mobileNo, Integer srNo);
    
    public List<SvpsStudentDetail> getListOfAllSrudent();
    public List<SvpsStudentDetail> getListOfStudentByStandard(String standard);


    public List<AttendenceView> getAllRollNumberOfStandard(String standard);

    public List<SvpsStudentDetail> getAbsentStudentDetail(String standard, String dateOfAttendance);

    public List<AttendenceView> getAlreadyAttendence(String standard, String dateOfAttendance) ;

}
