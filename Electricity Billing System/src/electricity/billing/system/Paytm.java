package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton btnback;
    Paytm(String meter)
    {
        this.meter=meter;
        
        setTitle("Pay With Paytm");
        setSize(850,570);
        setLocation(450,160);
        setLayout(new BorderLayout());
        
        JEditorPane epnl = new JEditorPane();
        epnl.setBackground(new Color(200,220,200));
        epnl.setEditable(false);
        
        try
        {
            epnl.setPage("https://paytm.com/online-payments");
        }
        catch(Exception e)
        {
            epnl.setContentType("text/html");
            epnl.setText("<html>"
                    + "<p style='color:red;margin-top:150px;text-align:center;font-size:20px'>"
                    + "Could Not Load This Page<p>"
                    + "</html>"); 
        }
        
        JScrollPane sp = new JScrollPane(epnl);
        add(sp, "Center");
        
        btnback = new JButton("Back");
        btnback.addActionListener(this);
        btnback.setForeground(Color.WHITE);
        btnback.setBackground(Color.BLACK);
        add(btnback, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Paybill(meter);
    }
          
    public static void main(String []args)
    {
        new Paytm("");
    }
}
