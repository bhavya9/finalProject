package attendance.dao.SVPS;

public class SvpsAttendance {
    public boolean saveAttendence(SvpsAttendence attendence);

    public boolean updateAttendence(SvpsAttendence attendence);
    
    // public List<AttendenceView> getMonthList(String fromDate, String toDate);
     public List<AttendenceView> getAllAttendenceByMonth(String month, String standard);

    public List<AttendenceView> getAllAttendenceByChild(String month, String mobileNo);
    
    public List<AttendenceView> getAllAttendenceByDate(String fromMonth, String toMonth);

    public List<AttendenceView> getAllAttendenceOfChild(String mobileNo);



    public SvpsAttendence attendenceAlreadyExists(Integer rollNo, String standard, Date dateOfAttendance);

}
