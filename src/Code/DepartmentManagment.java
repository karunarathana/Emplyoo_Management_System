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
public class DepartmentManagment {
    private int DepId;
    private String DepName;
    private String Designation;
    private String Telephone;
    private String vacancy;
    /**
     * @return the DepId
     */
    public int getDepId() {
        return DepId;
    }

    /**
     * @param DepId the DepId to set
     */
    public void setDepId(int DepId) {
        this.DepId = DepId;
    }

    /**
     * @return the DepName
     */
    public String getDepName() {
        return DepName;
    }

    /**
     * @param DepName the DepName to set
     */
    public void setDepName(String DepName) {
        this.DepName = DepName;
    }

    /**
     * @return the Designation
     */
    public String getDesignation() {
        return Designation;
    }

    /**
     * @param Designation the Designation to set
     */
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    /**
     * @return the Telephone
     */
    public String getTelephone() {
        return Telephone;
    }

    /**
     * @param Telephone the Telephone to set
     */
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    /**
     * @return the vacancy
     */
    public String getVacancy() {
        return vacancy;
    }

    /**
     * @param vacancy the vacancy to set
     */
    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }
    public void Department_Insert()
    {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="Insert into department (DepID,DepName,Designation,Telephone,Vacancy) values('"+getDepId()+"','"+getDepName()+"','"+getDesignation()+"','"+getTelephone()+"','"+getVacancy()+"')";
            int rs =stmt.executeUpdate(sql);

            if(rs==1)
            {
                JOptionPane.showMessageDialog(null,"Data Insert Succsessfully");
                
            }            
            else{
                JOptionPane.showMessageDialog(null,"Data Insert unsuccsessfully");
            }

           

        } catch (Exception e) {                                                                                 
            System.out.println(e);
        }
    }
      public void Department_Update(int depId)
     {
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";
  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="UPDATE department SET DepName='"+getDepName()+"',Designation='"+getDesignation()+"',Telephone='"+getTelephone()+"',Vacancy='"+getVacancy()+"' WHERE DepID='"+depId+"'";
            boolean rs =stmt.execute(sql);
       
            if(rs==false)
            {
                System.out.println("Succsufully Update this Database row");
            }
            else
            {
                System.out.println("!Erorr!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }
     public void Department_Delete(int search1)
     {
         String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
         String user="root";
         String paswword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="DELETE FROM department  WHERE DepID='"+search1+"'";
            boolean rs =stmt.execute(sql);

            if(rs==false)
            {
                System.out.println("Succsufully Delete this Database row");
            }
            else
            {
                System.out.println("!Erorr!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
     }
    

}
