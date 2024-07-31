package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class DepositDetails extends JFrame implements ActionListener 
{
    JLabel lblmeterNo,lblmonth;
    Choice chcmeterNo,chcmonth;
    JButton btnsearch,btnprint;
    JTable tbl;
    DepositDetails()
    {
        super("Deposit Details");
        setSize(750,600);
        setLocation(350,100);
        
        setLayout(null);
        getContentPane().setBackground(new Color(200,220,200));
        
        lblmeterNo=new JLabel("Search By Meter Number");
        lblmeterNo.setBounds(20, 20, 150, 20);
        add(lblmeterNo);
        
        chcmeterNo=new Choice();
        
        try
        {
            Conn c=new Conn();
            ResultSet rs=c.st.executeQuery("select meter_no from customer");
            while(rs.next())
            {
                chcmeterNo.add(rs.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        chcmeterNo.setBounds(180, 20, 150, 20);
        add(chcmeterNo);
        
        lblmonth=new JLabel("Search By Month");
        lblmonth.setBounds(360, 20, 100, 20);
        add(lblmonth);
        
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
        chcmonth.setBounds(470, 20, 150, 20);
        add(chcmonth);
        
        btnsearch = new JButton("Search");
        btnsearch.setBounds(220, 80, 90, 25);
        btnsearch.setForeground(Color.WHITE);
        btnsearch.setBackground(Color.BLACK);
        btnsearch.addActionListener(this);
        add(btnsearch);
        
        btnprint = new JButton("Print");
        btnprint.setBounds(360, 80, 90, 25);
        btnprint.setForeground(Color.WHITE);
        btnprint.setBackground(Color.BLACK);
        btnprint.addActionListener(this);
        add(btnprint);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Meter Number");
        model.addColumn("Months");
        model.addColumn("Units");
        model.addColumn("Total Bill");
        model.addColumn("Status");
        
        try
        {
            Conn c = new Conn();
            
            ResultSet rs = c.st.executeQuery("select * from bill");
            
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
        
        tbl = new JTable(model);
        tbl.setFont(new Font("Serif",Font.PLAIN,17));
        tbl.setBackground(new Color(250,250,100));
        JTableHeader head = tbl.getTableHeader();
        head.setFont(new Font("Tahoma",Font.BOLD,17));
        head.setBackground(new Color(255,200,0));
        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(1, 150, 735, 420);
        sp.getViewport().setBackground(new Color(200,220,200));
        add(sp);

        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btnsearch)
        {
            String queary="select * from bill where meter_no='"+chcmeterNo.getSelectedItem()+"' and month = '"+chcmonth.getSelectedItem()+"'";
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Meter Number");
            model.addColumn("Months");
            model.addColumn("Units");
            model.addColumn("Total Bill");
            model.addColumn("Status");
            
            try
            {
                Conn c = new Conn();
                
                ResultSet rs = c.st.executeQuery(queary);
                
                if(rs.next())
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
           
            tbl = new JTable(model);
            tbl.setFont(new Font("Serif",Font.PLAIN,17));
            tbl.setBackground(new Color(250,250,100));
            JTableHeader head = tbl.getTableHeader();
            head.setFont(new Font("Tahoma",Font.BOLD,17));
            head.setBackground(new Color(255,200,0));
            JScrollPane sp = new JScrollPane(tbl);
            sp.setBounds(1, 150, 735, 420);
            sp.getViewport().setBackground(new Color(200,220,200));
            add(sp);
        }
        else if(ae.getSource()==btnprint)
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
    }
    public static void main(String args[])
    {
            new DepositDetails();
    }
}
