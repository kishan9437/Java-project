package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class Customerdetails extends JFrame implements ActionListener 
{
    JButton btnprint;
    JTable tbl;
    Customerdetails()
    {
        super("Customer Details");
        setSize(850,570);
        setLocation(450,160);
        setLayout(new BorderLayout());
          
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Meter No");
        model.addColumn("Address");
        model.addColumn("City");
        model.addColumn("State");
        model.addColumn("Email");
        model.addColumn("Phone No");
        
        try
        {
            Conn c = new Conn();
            
            String sql="select * from customer";
            
            ResultSet rs = c.st.executeQuery(sql);
            
            while(rs.next())
            {   
                model.addRow(new Object[]{
                    rs.getString("cust_name"),
                    rs.getString("meter_no"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("email"),
                    rs.getString("phone_no")
                });
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        tbl = new JTable(model);
        tbl.setFont(new Font("Serif",Font.PLAIN,15));
        tbl.setBackground(new Color(250,250,100));
        JTableHeader head = tbl.getTableHeader();
        head.setFont(new Font("Tahoma",Font.BOLD,16));
        head.setBackground(new Color(255,200,0));
        JScrollPane sp = new JScrollPane(tbl);
        add(sp, "Center");

        btnprint = new JButton("Print");
        btnprint.addActionListener(this);
        btnprint.setForeground(Color.WHITE);
        btnprint.setBackground(Color.BLACK);
        add(btnprint, "South");

        setVisible(true); 
        

    }
    public void actionPerformed(ActionEvent ae)
    {
            try
            {
                tbl.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        
    }
    public static void main(String args[])
    {
            new Customerdetails();
    }
}
