package org.csu.myjpetstore.persistence;

import com.sun.mail.util.MailSSLSocketFactory;
import org.csu.myjpetstore.domain.Account;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


/**
 * 邮件发送工具类
 * @author aibinxiao
 * @date 2020年11月16日 上午1:16:59
 */
public class MailUtils {
	public static final String HOST = "smtp.qq.com"; // QQ邮件服务器主机
    public static final String FROM = "3472693603@qq.com"; // QQ发件人的email
    public static final String PWD = "gqmgesikvoocchba"; // QQ发件人授权码
	// public static final String HOST = "smtp.163.com"; // 163邮件服务器主机
	// public static final String FROM = "xxx@163.com"; // 163发件人的email  
    // public static final String PWD = "xxx"; // 163发件人授权码
    
    /**
     * 获取Session
     * @return
     */
    public static Session getSession() {
    	Properties props = new Properties();
		props.put("mail.smtp.host", HOST); // 设置服务器地址  
        props.put("mail.smtp.auth" , true); 
        
        // QQ邮箱使用了SSL需要下面这段代码，163邮箱不需要
		MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
        
        Authenticator authenticator =  new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		return session;
    }
    
    /**
     * 设置邮件内容
     * @param account
     * @return
     */
    public static String getContent(Account account) {
    	///邮件的内容  
        StringBuffer sb = new StringBuffer("<h3>From the activation email of CSU_JPETSTORE website, click the link below to activate the account.Please activate it as soon as possible!</h3></br>");
        sb.append("<a href=\"http://localhost:8080/myjpetstore/ActiveServlet?email=");
        sb.append(account.getEmail());
        sb.append("&code=");   
        sb.append(account.getActcode());
        sb.append("\">http://localhost:8080/myjpetstore/ActiveServlet?email=");
        sb.append(account.getEmail());
        sb.append("&code=");  
        sb.append(account.getActcode());
        sb.append("</a>"); 
        return sb.toString();
    }
	
	/**
	 * 发送邮件
	 * @param account 激活邮件接收者
	 * @throws Exception
	 */
	public static void sendMail(Account account) throws Exception {
		// 1.创建连接对象，连接到邮箱服务器
		Session session = getSession();
		// 打开调试，会打印与邮箱服务器回话的内容  
        session.setDebug(true);   
		// 2.创建邮件对象
		Message message = new MimeMessage(session);
		// 2.1设置发件人
		message.setFrom(new InternetAddress(FROM));
		// 2.2设置收件人
		InternetAddress[] address = {new InternetAddress(account.getEmail())};
		message.setRecipients(RecipientType.TO, address);
		// 2.3设置邮件的主题
		message.setSubject("From the activation email of CSU_JPETSTORE website");
		// 2.4设置邮件的正文
		String content = getContent(account);
		System.out.println("--send--" + content);
		message.setContent(content, "text/html;charset=UTF-8");
		// 2.5设置发送时间
		message.setSentDate(new Date());
		// 3.发送一封激活邮件
		Transport.send(message);
	}
	
	/**
	 * 获取最后激活时间
	 * @param registerTime
	 * @return
	 */
	public static Long getLastActivateTime(Date registerTime) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(registerTime);  
        cal.add(Calendar.DATE , 2);    
        return cal.getTimeInMillis();  
    }  

}
