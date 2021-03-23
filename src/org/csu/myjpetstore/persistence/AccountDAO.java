package org.csu.myjpetstore.persistence;

import org.csu.myjpetstore.domain.Account;

public interface AccountDAO {

	    void validateActiveCode(Account account, String code) throws Exception;


		Account getAccountByEmail(String email_demo) throws Exception;
		String getAccountByEmail = "SELECT * FROM ACCOUNT WHERE EMAIL = ?";


		Account getAccountByUsername(String username);
		String getAccountByUsernameString = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,"
				+ "ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,"
				+ "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,"
				+ "ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,"
				+ "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,"
				+ "PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, "
				+ " BANNERDATA WHERE ACCOUNT.USERID = ? "
				+ " AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID"
				+ " AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
		
		Account getAccountByUsernameAndPassword(Account account);
		String getAccountByUsernameAndPasswordString="SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME ,ACCOUNT.TESTCODE, ACCOUNT.RE FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID =? AND SIGNON.PASSWORD =? AND SIGNON.USERNAME = ACCOUNT.USERID  AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";



	void insertAccount(Account account);
	    String insertAccountString = "INSERT INTO ACCOUNT"
			  + "(EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID, TESTCODE, RE)"
			  + "VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?)";

//	    String insertAccountDemo = "INSERT INTO ACCOUNT (CONDITION) VALUES (?)";
//        String insertAccountDemo = "INSERT INTO ACCOUNT (TESTCODE, CONDITION, registerTime) VALUES (?,?,?)";

		void insertProfile(Account account);
		String insertProfileString="INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?,?,?)";
		
		void insertSignon(Account account);
		String insertSignonString = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?,?)";
		
		void updateAccount(Account account);
		String updateAccountString = "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,"
				+ "ADDR1 = ?,ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ?,TESTCODE = ?,RE = ? WHERE USERID = ?";
		
		void updateProfile(Account account);
		String updateProfileString = "UPDATE PROFILE SET LANGPREF = ?,FAVCATEGORY = ? WHERE USERID = ?";
		
		void updateSignon(Account account);
		String updateSignonString = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
}
