package attendance.dao;

public class Staff {
    public StaffDetail staffLogin(String userId,String password);
    
    public StaffDetail staffById(String userId);
    
    public AdminLogin adminLogin(String userId,String password);
    
    public List<StaffDetail> getListOfAllStaff();
    public List<Users> getListOfAllUsers()throws Exception;
    public boolean saveStaffEntry(StaffDetail staffDetail);

    public boolean updatePassword(StaffDetail staffDetail);

}
