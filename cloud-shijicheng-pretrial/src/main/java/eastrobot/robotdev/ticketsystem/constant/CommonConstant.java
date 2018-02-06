package eastrobot.robotdev.ticketsystem.constant;

public interface CommonConstant {
	
	public static final String SERVER_HTTP_PATH="222.85.230.14";
	public static final String SERVER_HTTP_PORT="13146";
	public static final String FILE_PATH="upload";
	public static final boolean ENV = true;//false为test;true为product
	
	
	//流动人口中，申请人的婚姻状态maritalStatus
	public static final int MARITAL_STATUS_UNMARRIED = 0;
	public static final int MARITAL_STATUS_MARRIED = 1;
	//表单类型
	public static final int FORM_TYPE_NOTIFY_LDRK = 4;
	public static final int FORM_TYPE_NOTIFY_SZBJ = 5;
	public static final String MALE_ID_FRONT = "MALE_ID_FRONT";
	public static final String FEMALE_ID_FRONT = "FEMALE_ID_FRONT";
	public static final String FEMALE_ID_BACK = "FEMALE_ID_BACK";
	public static final String MALE_ID_BACK = "MALE_ID_BACK";

	public static final String MODE_CODE_RECRUIT = "RECRUIT";//招聘

	public static final String JOB_COMPANY = "JOB_COMPANY";//公司招聘

	public static final String JOB_PERSON = "JOB_PERSON";//个人求职
	

}
