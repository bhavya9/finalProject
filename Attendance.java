package attendance.dao;

@Controller
@RequestMapping("Svps/student")
public class Attendance {
	@Autowired
    SvpsStudentDao studentDao;

    // Save Suggetion Make By Doctor Or Laboratorian
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<?> saveStudent(@RequestBody SvpsStudentDetail studentDetail) throws Exception {
        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {

            if (studentDetail.getSrNo() != null) {
                studentDao.saveStudent(studentDetail);
                response.setMessage("Student Detail Updated Successfully...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                SvpsStudentDetail studDetail = studentDao.getStudentByGrNo(studentDetail.getGrNo());
                if (studDetail != null) {
                    validator.addError("Error", "Student With This GR NO Already Exists..!");
                    response.setErrorLists(validator.errorLists);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                response.setMessage("Student Detail Saved Successfully...");
                studentDao.saveStudent(studentDetail);

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception ex) {
            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login/mobileNo={mobileNo}&&srNo={srNo}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getParentsLogin(@PathVariable String mobileNo, @PathVariable String srNo) throws Exception {
        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {

            if (!srNo.matches("^[0-9]+$")) {
                validator.addError("Please", " Enter Valid Roll No!");
                response.setErrorLists(validator.errorLists);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            SvpsStudentDetail student = studentDao.parentsLogin(mobileNo, Integer.parseInt(srNo));

            if (student == null) {
                validator.addError("UserId", " Or Password Is Wrong..!");
                response.setErrorLists(validator.errorLists);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAllStudent() throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {

            List<SvpsStudentDetail> studentList = studentDao.getListOfAllSrudent();
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception ex) {

            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/view/{grNo}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getStudentByGrNos(@PathVariable Integer grNo) throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {
            SvpsStudentDetail studDetail = studentDao.getStudentByGrNo(grNo);
            return new ResponseEntity<>(studDetail, HttpStatus.OK);
        } catch (Exception ex) {

            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/viewAll/standard={standard}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getAllStudentByStandard(@PathVariable String standard) throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();
        try {
            List<SvpsStudentDetail> studentList = studentDao.getListOfStudentByStandard(standard);
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception ex) {

            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/rollNoListByStandard/standard={standard}&&dateOfAttendance={dateOfAttendance}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<?> getRollNoByStandard(@PathVariable String standard, @PathVariable String dateOfAttendance) throws Exception {

        CustomValidator validator = new CustomValidator();
        PostResponse response = new PostResponse();

        try {

            List<AttendenceView> atView = studentDao.getAlreadyAttendence(standard, dateOfAttendance);

            if (atView.size() > 0) {
                return new ResponseEntity<>(atView, HttpStatus.OK);
            }

            List<AttendenceView> rollNoList = studentDao.getAllRollNumberOfStandard(standard);
            return new ResponseEntity<>(rollNoList, HttpStatus.OK);
        } catch (Exception ex) {

            validator.addError("Exception", ex.getMessage());
            response.setErrorLists(validator.errorLists);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

}
