package electricity.billing.system;
import javax.swing.*;

public class splash extends JFrame implements Runnable
{
    splash()
    {
        super("Electricity Billing System");
        Thread t;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/splash.jpg"));
        JLabel image=new JLabel(i1);
        add(image);

        setVisible(true);

        int x=1;
        for(int i=2; i < 750; i+=4,x+=1)
        {
            setSize(i+x,i);
            setLocation(770-((i+x)/2),400-(i/2));
            
            try
            {
                Thread.sleep(9);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        t.start();
        setVisible(true);
    }
    public void run()
    {
        try
        {
            Thread.sleep(1000);
            setVisible(false);
            
            new Login();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String []args)
    {
        new splash();
    }
    
}
