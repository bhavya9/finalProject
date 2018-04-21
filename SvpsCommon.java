package attendance.dao.SVPS;

public class SvpsCommon {
    public boolean saveDiary(SvpsDiary diary)throws Exception;
    public List<SvpsDiary> getListDiary(String standard, String fromDate, String toDate)throws Exception;
    public List<SvpsDiary> getListDiary()throws Exception;
    public List<SvpsDiary> getListDiaryByParents(String mobileNo, String fromDate, String toDate) throws Exception ;

}
