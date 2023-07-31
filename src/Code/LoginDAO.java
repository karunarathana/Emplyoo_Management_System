/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import Interface.OTPCodeInterface;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author N.KARUNARATHNA
 */
   public class LoginDAO {
    
   private String Username;
   private String Password;
   private String Loginpassword;
   private String LoginUsername;
   private String email;
   private String role;
   private String HashinPss="0";
   private String RandomNumber;
   
   OTPCodeInterface otpobj = new OTPCodeInterface();
   public LoginDAO(){};
    /**
     * @return the Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username the Username to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the RandomNumber
     */
    public String getRandomNumber() {
        return RandomNumber;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public void objectequel(OTPCodeInterface obj)
    {
        otpobj = obj;
    }
    
    public void verifyLogin()
    {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="Select * FROM user  WHERE UserName='"+getUsername()+"' ";
            ResultSet rs =stmt.executeQuery(sql);
            
             if(rs.next())
	     {
                 LoginUsername=rs.getString(1);
		 Loginpassword=rs.getString(2);
                 setRole(rs.getString(4));
                 email=rs.getString(5);
                 PasswordHashing();
	     }
             else
             {
               JOptionPane.showMessageDialog(null,"Incorrect Your Please Check Your Username");
             }
                

        } catch (Exception e) {
           
            System.out.println(e);
        }
    }
    public void PasswordHashing() throws NoSuchAlgorithmException
    {
     
        MessageDigest md = MessageDigest.getInstance("MD5");
  
        byte[] messageDigest = md.digest(getPassword().getBytes());

        BigInteger bigInt = new BigInteger(1,messageDigest);
        HashinPss=bigInt.toString(16);
        
        Password_Checking();
    }
     public void Password_Checking()
    {
     
     if(HashinPss.equals(Loginpassword))
     {
        JOptionPane.showMessageDialog(null,"Correct Your UserName And Password");
     }
     else
     {
        JOptionPane.showMessageDialog(null,"Incorrect Your  Password");
     }
     
   
    }
      public  void sendMail(String recepient,String Number)
    {
        Properties properties=new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        String fromUser ="testcom5544@gmail.com";  //Enter sender email id
        String fromUserPassword ="ohsjnusjjgixxngt";

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromUser,fromUserPassword);
            }
        });
        Message message=prepareMessage(session,fromUser,recepient,Number);
        try {
            Transport.send(message);
            otpobj.setVisible(true);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Message prepareMessage(Session session,String fromUser,String recepient,String Number) {
        Message message=new MimeMessage(session);
        try {
            String message1="<h1 style=\"color:crimson;text-align:center;\">Welcome Colombo Insitute</h1>\n" +
"<h1 style=\"color: rgb(0, 0, 0);text-align: center;\">Your OTP Code</h1>\n" +
"<h1 style=\"border: 50px;text-align: center;color: lawngreen;background-color: dimgray;\">'"+Number+"'</h1>\n" +
"<p style=\"text-align: center;text-decoration: underline;font-size: 30px;\">Dear Customer,your OTP(One Time Passcode) Use this Passcode Valid Your Login</p>\n" +
"<p style=\"font-size: 20px;color: rgb(3, 3, 3);\">Thanks</p>";
            message.setFrom(new InternetAddress("fromUser"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Colombo Insitute Of Studies");
            String html=message1;
            //message.setContent("","text/html");
            //return message;
            MimeBodyPart mimeBodyPart=new MimeBodyPart();
            mimeBodyPart.setContent(html,"text/html");

            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            MimeBodyPart atach=new MimeBodyPart();
            atach.attachFile(new File("C:\\Users\\N.KARUNARATHNA\\Desktop\\add-user.png"));
            multipart.addBodyPart(atach);
            message.setContent(multipart);
            return message;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public void RandomNumberGenarator()
    {
        Random rnd = new Random();
        int num=rnd.nextInt(9);
        int num1=rnd.nextInt(9);
        int num2=rnd.nextInt(9);
        int num3=rnd.nextInt(9);
        
        RandomNumber=Integer.toString(num)+Integer.toString(num1)+Integer.toString(num2)+Integer.toString(num3);
        System.out.println(getRandomNumber());
        sendMail(getEmail(), getRandomNumber());//hnddgree2023@gmail.com
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

   
   
   
    
}
