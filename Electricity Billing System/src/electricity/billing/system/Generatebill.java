package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Generatebill extends JFrame implements ActionListener{
    
    JLabel lblbill;
    Choice chcmonth;
    JTextArea area;
    JButton btnprint,btngenerate,btncancel;
    JPanel pnl1,pnl2;
    String meter;
    Generatebill(String meter)
    {
        this.meter=meter;
        
        setTitle("Generate Bill");
        setSize(500,700);
        setLocation(550,30);
        
        setLayout(new BorderLayout());
        
        pnl1=new JPanel();
        pnl1.setBackground(Color.black);
        
        lblbill = new JLabel("Generate Bill Meter No is : "+meter+" And Month : ");
        lblbill.setForeground(Color.WHITE);
        pnl1.add(lblbill);
        
        chcmonth=new Choice();
        chcmonth.add("January");
        chcmonth.add("February");
        chcmonth.add("March");
        chcmonth.add("April");
        chcmonth.add("May");
        chcmonth.add("June");
        chcmonth.add("Jully");
        chcmonth.add("August");
        chcmonth.add("September");
        chcmonth.add("Auctomer");
        chcmonth.add("November");
        chcmonth.add("December");
        pnl1.add(chcmonth);

        area=new JTextArea(50,15);
        area.setText("\n\n\t---------Click On the---------\n\t Generate Bill Button to get \n\t the bill of the Selected Month");
        area.setFont(new Font("Senserif",Font.ITALIC,18));
        JScrollPane sp=new JScrollPane(area);
        
        pnl2=new JPanel();
        pnl2.setBackground(Color.black);
        
        btnprint = new JButton("Print");
        btnprint.addActionListener(this);
        btnprint.setForeground(Color.WHITE);
        btnprint.setBackground(Color.BLACK);
        btnprint.setVisible(false);
        pnl2.add(btnprint);
        
        btngenerate = new JButton("Generate Bill");
        btngenerate.addActionListener(this);
        btngenerate.setForeground(Color.WHITE);
        btngenerate.setBackground(Color.BLACK);
        pnl2.add(btngenerate);
        
        btncancel = new JButton("Cancel");
        btncancel.addActionListener(this);
        btncancel.setForeground(Color.WHITE);
        btncancel.setBackground(Color.BLACK);
        pnl2.add(btncancel);
        
        add(pnl1, "North");
        add(sp, "Center");
        add(pnl2, "South");
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String month = chcmonth.getSelectedItem();
        
        if(ae.getSource() == btngenerate)
        {
            try{
                Conn c=new Conn();
            
                area.setText("""
                            \n\tGUJRAT POWER LIMITED
                                    ELECTRICITY BILL GENERETED FOR THE
                            \tMONTH OF\t""" +month+ ", 2023");
            
                area.append("\n\t---------------------------------------");

            
                ResultSet rs=c.st.executeQuery("select * from customer where meter_no='"+meter+"'");
            
                if(rs.next())
                {
                    area.append("\n\n\tCustomer Name: "+rs.getString("cust_name"));
                    area.append("\n\tMeter Number : "+rs.getString("meter_no"));
                    area.append("\n\tAddress      : "+rs.getString("address"));
                    area.append("\n\tCity         : "+rs.getString("city"));
                    area.append("\n\tState        : "+rs.getString("state"));
                    area.append("\n\tEmail        : "+rs.getString("email"));
                    area.append("\n\tPhone No     : "+rs.getString("phone_no"));
                    area.append("\n\n\t-------------------------------------");
               
                }
            
                rs=c.st.executeQuery("select * from meter_info where meter_no='"+meter+"'");
            
                if(rs.next())
                {
                    area.append("\n\n\tMeter Number   : "+rs.getString("meter_no"));
                    area.append("\n\tMeter Location : "+rs.getString("meter_location"));
                    area.append("\n\tMeter Type     : "+rs.getString("meter_type"));
                    area.append("\n\tPhase Code     : "+rs.getString("phase_code"));
                    area.append("\n\tBill Type      : "+rs.getString("bill_type"));
                    area.append("\n\tDay            : "+rs.getString("days"));
                    area.append("\n\n\t-------------------------------------");
               
                }
            
                rs = c.st.executeQuery("select * from tax");
                
                if(rs.next())
                {
                    area.append("\n\n\tCost Per Units : - " + rs.getString(1));
                    area.append("\n\tMeter Rent : - " + rs.getString(2));
                    area.append("\n\tService Charge : - " + rs.getString(3));
                    area.append("\n\tService tax  : - " + rs.getString(4));
                    area.append("\n\tSwaccha Bharat case   : - " + rs.getString(5));
                    area.append("\n\tFixed tax  : - " + rs.getString(6));
                    area.append("\n\n\t-------------------------------------");
                }
               
                rs = c.st.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+month+"'");
                
                if(rs.next())
                {
                    area.setVisible(true);
                    area.append("\n\n\tCurrent Month : - " + rs.getString(2));
                    area.append("\n\tUnit Consumed : - " + rs.getString(3));
                    area.append("\n\tTotal Charges : - " + rs.getString(4));
                    area.append("\n\n\t-------------------------------------");
                    area.append("\n\tTotal Payable : - " + rs.getString(4));
                }
                else
                {
                    area.setVisible(false);
                    JOptionPane.showMessageDialog(null, "This Month Bill Is Not Found");
                }
                btnprint.setVisible(true);
                btngenerate.setVisible(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == btnprint)
        {
            try
            {
                area.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == btncancel)
        {
            setVisible(false);
        }
    }
    public static void main(String []args)
    {
        new Generatebill("");
    }
}
