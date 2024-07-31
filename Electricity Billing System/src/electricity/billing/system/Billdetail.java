package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;

public class Billdetail extends JFrame {

    Billdetail(String meter)
    {
        super("Bill Detail");
        setBounds(400,150,700,650);
        
        getContentPane().setBackground(Color.WHITE);
        
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("Meter Number");
        model.addColumn("Month");
        model.addColumn("Units");
        model.addColumn("Total Bill");
        model.addColumn("Status");
        
        try
        {
           Conn c=new Conn();
          
           ResultSet rs=c.st.executeQuery("select * from bill where meter_no='"+meter+"'");
           
           while(rs.next())
           {
               model.addRow(new Object[]{
                   rs.getString("meter_no"),
                   rs.getString("month"),
                   rs.getString("units"),
                   rs.getString("total_bill"),
                   rs.getString("status")
               });
           }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JTable tbl = new JTable(model);
        tbl.setFont(new Font("Serif",Font.PLAIN,17));
        tbl.setBackground(new Color(250,250,100));
        JTableHeader head = tbl.getTableHeader();
        head.setFont(new Font("Tahoma",Font.BOLD,17));
        head.setBackground(new Color(255,200,0));
        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(1, 100, 735, 463);
        sp.getViewport().setBackground(new Color(200,220,200));
        add(sp);
        
        setVisible(true);
        setResizable(false);
    }
    public static void main(String []args)
    {
        new Billdetail("");
    }
}
