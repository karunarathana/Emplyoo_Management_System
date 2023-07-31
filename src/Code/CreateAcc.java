/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author N.KARUNARATHNA
 */
public class CreateAcc {
    public String hashpassword;
    private String Username;
    private String Password;
    private String Confpass;
    private String Status;
    private String Role;
    private String Email;

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
     * @return the Confpass
     */
    public String getConfpass() {
        return Confpass;
    }

    /**
     * @param Confpass the Confpass to set
     */
    public void setConfpass(String Confpass) {
        this.Confpass = Confpass;
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

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the Role
     */
    public String getRole() {
        return Role;
    }

    /**
     * @param Role the Role to set
     */
    public void setRole(String Role) {
        this.Role = Role;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void checkpss()
    {
        if(getPassword().equals(getConfpass()))
        {
            try {
                PasswordHashing();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CreateAcc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"!Your Password Does\'t Match!");
        }
    }
    public void PasswordHashing() throws NoSuchAlgorithmException
 {
     
       MessageDigest md = MessageDigest.getInstance("MD5");
  
        byte[] messageDigest = md.digest(getPassword().getBytes());

        BigInteger bigInt = new BigInteger(1,messageDigest);
        hashpassword=bigInt.toString(16);
       
 }
     public void NewAcount_Insert()
    {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="Insert into user (UserName,Password,Status,Role,Email) values('"+getUsername()+"','"+hashpassword+"','"+getStatus()+"','"+getRole()+"','"+getEmail()+"')";
            int rs =stmt.executeUpdate(sql);

            if(rs==1)
            {
                JOptionPane.showMessageDialog(null,"!Data Insert Database!");
            }            
            else{
               JOptionPane.showMessageDialog(null,"!Data Not Insert Database!");
            }

           

        } catch (Exception e) {                                                                                 
            System.out.println(e);
        }
    }
      public void Account_Update(String username)
     {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";
  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="UPDATE user SET Password='"+hashpassword+"',Status='"+getStatus()+"',Role='"+getRole()+"',Email='"+getEmail()+"' WHERE UserName='"+username+"'";
            boolean rs =stmt.execute(sql);
       
            if(rs==false)
            {
               JOptionPane.showMessageDialog(null,"!Update Database!");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"!ERROR Database Not Update!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }
      public void Acc_Delete(String search1)
     {
         String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
         String user="root";
         String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="DELETE user department  WHERE UserName='"+search1+"'";
            boolean rs =stmt.execute(sql);

            if(rs==false)
            {
               JOptionPane.showMessageDialog(null,"!Successfull Delete !");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"!EROR !");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }

  
    
}
