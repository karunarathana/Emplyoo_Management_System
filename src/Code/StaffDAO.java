/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author N.KARUNARATHNA
 */
public class StaffDAO  {
    
       private String name;
       private String gender;
       private String mobile;
       private String nic;
       private String epf;
       private String status;
       private String email;
       private String department;
       private String designation;
       private String Search;
       private int currentStatus;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the epf
     */
    public String getEpf() {
        return epf;
    }

    /**
     * @param epf the epf to set
     */
    public void setEpf(String epf) {
        this.epf = epf;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the Search
     */
    public String getSearch() {
        return Search;
    }

    /**
     * @param Search the Search to set
     */
    public void setSearch(String Search) {
        this.Search = Search;
    }

    /**
     * @return the currentStatus
     */
    public int getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }
     public void Staffmangement_Insert()
    {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="Insert into employee (FullName,Gender,Mobile,NIC,EPF,Status,Email,Department,Designations) values('"+getName()+"','"+getGender()+"','"+getMobile()+"','"+getNic()+"','"+getEpf()+"','"+getStatus()+"','"+getEmail()+"','"+getDepartment()+"','"+getDesignation()+"')";
            int rs =stmt.executeUpdate(sql);

            if(rs==1)
            {
                JOptionPane.showMessageDialog(null,"Row Inseted Succsussfully");
            }            
            else{
              JOptionPane.showMessageDialog(null,"!Database Not Inserted!");
            }

           

        } catch (Exception e) {                                                                                 
            System.out.println(e);
        }
    }
       public void Staffmangement_Update()
     {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";
  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="UPDATE employee SET FullName='"+getName()+"',Gender='"+getGender()+"',Mobile='"+getMobile()+"',NIC='"+getNic()+"',EPF='"+getEpf()+"',Status='"+getStatus()+"',Email='"+getEmail()+"',Department='"+getDepartment()+"',Designations='"+getDesignation()+"' WHERE NIC='"+getSearch()+"'";
            boolean rs =stmt.execute(sql);

       
            if(rs==false)
            {
                JOptionPane.showMessageDialog(null,"Succsufully Update this Database row");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"!Erorr!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }
     public void Staffmangement_Delete(int search1)
     {
         String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
         String user="root";
         String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="DELETE FROM employee  WHERE NIC='"+search1+"'";
            boolean rs =stmt.execute(sql);

            if(rs==false)
            {
                JOptionPane.showMessageDialog(null,"Succsufully Delete this Database row");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"!Erorr!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }
       
       
}
