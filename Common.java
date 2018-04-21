package attendance.dao.SVPS;

public class Common {
	@Autowired
    StaffDao staffDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CommonDao commonDao;

    // Used for forget password
    @RequestMapping(value = "/forget_password/userId={userId}&&userType={userType}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> resetPassword(@PathVariable("userId") String userId, @PathVariable String userType) throws Exception {
        CustomValidator customValidator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {


            StaffDetail staffDetail;
            StudentDetail studentDetail;

            if (userType.equals("Staff") || userType.equals("Principal")) {
                staffDetail = staffDao.staffById(userId);
                if (staffDetail == null) {
                    customValidator.addError("User", "With This Id Does Not Exist !");
                    response.setErrorLists(customValidator.errorLists);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                String smsContent = "Your Forgotten Password Is : " + staffDetail.getPassword();
                String message = SmsService.sendSms(staffDetail.getMobileNo(), smsContent);
                response.setMessage("Password Is Sent To Your Registered Mobile No..");

            } else if (userType.equals("Parents")) {
                studentDetail = studentDao.getStudentByGrNo(Integer.parseInt(userId));
                if (studentDetail == null) {
                    customValidator.addError("User", "With This Id Does Not Exist !");
                    response.setErrorLists(customValidator.errorLists);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                String smsContent = "Your Forgotten Password Is : " + studentDetail.getGrNo();
                String message = SmsService.sendSms(studentDetail.getMobileNo(), smsContent);
                response.setMessage("Password Is Sent To Your Registered Mobile No..");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            customValidator.addError("Exception", ex.getMessage());
            response.setErrorLists(customValidator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/password/update/userId={userId}&&password={password}&&newPassword={newPassword}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<?> changePassword(@PathVariable("userId") String userId, @PathVariable("password") String password, @PathVariable("newPassword") String newPassword) throws Exception {

        CustomValidator customValidator = new CustomValidator();
        PostResponse response = new PostResponse();


        try {

            StaffDetail staffDetail = staffDao.staffLogin(userId, password);
            if (staffDetail == null) {
                customValidator.addError("", " Old Password Does Not Match !");
                response.setErrorLists(customValidator.errorLists);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            staffDetail.setPassword(newPassword);
            staffDao.updatePassword(staffDetail);
            response.setMessage("Password Has Been Changed Successfully...!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            customValidator.addError("Email", ex.getMessage());
            response.setErrorLists(customValidator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/diary/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<?> saveDiaryDetail(@RequestBody Diary diary) throws Exception {
        CustomValidator customValidator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {
            commonDao.saveDiary(diary);
            response.setMessage("Data Stored Successfully....!");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            customValidator.addError("Exception", ex.getMessage());
            response.setErrorLists(customValidator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/diary/viewAll", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAllDiary() throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();
        try {
            List<Diary> diaryList = commonDao.getListDiary("","fromdate", "todate");
            return new ResponseEntity<>(diaryList, HttpStatus.OK);
        } catch (Exception ex) {
            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/diary/viewByStaff/{standard}&&{fromDate}&&{toDate}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getViewDiaryByParents(@PathVariable String standard,@PathVariable String fromDate, @PathVariable String toDate) throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();
        try {
            List<Diary> diaryList = commonDao.getListDiary(standard,fromDate, toDate);
            return new ResponseEntity<>(diaryList, HttpStatus.OK);
        } catch (Exception ex) {
            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/diary/viewByPatents/{mobileNo}&&{fromDate}&&{toDate}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getViewDiaryByStaff(@PathVariable String mobileNo,@PathVariable String fromDate, @PathVariable String toDate) throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();
        try {
            List<Diary> diaryList = commonDao.getListDiaryByParents(mobileNo,fromDate, toDate);
            return new ResponseEntity<>(diaryList, HttpStatus.OK);
        } catch (Exception ex) {
            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

}
