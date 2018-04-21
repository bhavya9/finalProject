package attendance.dao.SVPS;

public class SvpsStaff {
    public SvpsStaffDetail staffLogin(String userId, String password);
    
    public SvpsStaffDetail staffById(String userId);
    
    public SvpsAdminLogin adminLogin(String userId, String password);
    
    public List<SvpsStaffDetail> getListOfAllStaff();

    public boolean saveStaffEntry(SvpsStaffDetail staffDetail);

    public boolean updatePassword(SvpsStaffDetail staffDetail);

}
