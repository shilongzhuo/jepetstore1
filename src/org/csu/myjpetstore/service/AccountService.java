package org.csu.myjpetstore.service;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.persistence.AccountDAO;
import org.csu.myjpetstore.persistence.MailUtils;
import org.csu.myjpetstore.persistence.impl.AccountDAOImpl;


public class AccountService {


  private AccountDAO accountMapper;
  
  public AccountService(){
	  accountMapper = new AccountDAOImpl();
  }

  public Account getAccount(String username) {
    return accountMapper.getAccountByUsername(username);
  }

  public Account getAccount(String username, String password) {
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    return accountMapper.getAccountByUsernameAndPassword(account);
  }


  public void insertAccount(Account account) throws Exception {
    accountMapper.insertAccount(account);
    accountMapper.insertProfile(account);
    accountMapper.insertSignon(account);
    // 发送一封激活邮件
    MailUtils.sendMail(account);
  }


  public void updateAccount(Account account) {
    accountMapper.updateAccount(account);
    accountMapper.updateProfile(account);

    if (account.getPassword() != null && account.getPassword().length() > 0) {
      accountMapper.updateSignon(account);
    }
  }

  public Account getAccountByEmail(String email_demo) throws Exception {
    return accountMapper.getAccountByEmail(email_demo);
  }

  public void validateActiveCode(Account account, String code) throws Exception {
    accountMapper.validateActiveCode(account,code);
  }

}
