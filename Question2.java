import javax.swing.*;
import java.awt.*;

public class Question2 extends JFrame
{
    JButton jbt1=new JButton("Hello");
    public Question2()
    {
        add(jbt1);
        setTitle("One Frame");
        setSize(500,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[]args)
    {
        new Question2();
    }

}