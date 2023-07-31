/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author N.KARUNARATHNA
 */
public class AtendentManagement {
    
    
    public  void QrCode(String text,int width,int h,String Filepath) throws WriterException
    {
        QRCodeWriter qc=new QRCodeWriter ();
        BitMatrix bm=qc.encode(text, BarcodeFormat.QR_CODE, width, h);
        Path pobj=FileSystems.getDefault().getPath(Filepath);
        try {
            MatrixToImageWriter.writeToPath(bm, "PNG", pobj);
            JOptionPane.showMessageDialog(null,"Your QR Code is Gearated");
            
        }
        catch(IOException ex){
        Logger.getLogger(AtendentManagement.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    public void QRCode_Genarator(String Nic) throws WriterException
    {
        String messande=Nic;  
        String Filepath="C:\\Users\\N.KARUNARATHNA\\Desktop\\'"+Nic+"'.png";        
        QrCode(messande, 1250, 1250,Filepath);
    }
     public void Attendancy_Insert(String NIC)
    {
        
        String url="jdbc:mysql://localhost:3306/colombo_i_o_s";
        String user="root";
        String paswword="";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String Date=date.toString();
        String Time=time.toString();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection(url,user,paswword);
            Statement stmt =conn.createStatement();
            String sql="Insert into attendancy (NIC,Date,Time) values('"+NIC+"','"+Date+"','"+Time+"')";
            int rs =stmt.executeUpdate(sql);

            if(rs==1)
            {
                JOptionPane.showMessageDialog(null,"Successfully Data Inserted");
            }            
            else{
              JOptionPane.showMessageDialog(null,"unsuccessfully Data Inserted");
            }

           

        } catch (Exception e) {                                                                                 
            System.out.println(e);
        }
    }
   
   
}
