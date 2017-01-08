import javax.swing.JOptionPane;
public class Question1
{
 public static void main(String[]args)
{
int option=JOptionPane.YES_OPTION;

do
{
    String inputStr=JOptionPane.showInputDialog(null,"Please enter a temperature in fahrenheit");
    double fahrenheit=Double.parseDouble(inputStr);
   double celsius=5.0/9.0*(fahrenheit-32);
   
   JOptionPane.showMessageDialog(null, "The temperatur e converted to Celsius "+celsius);
   
   option=JOptionPane.showConfirmDialog(null, "Enter another temperature?");
   //option=JOptionPane.showConfirmDialog(null,"Enter another temperature?","Continue",JOptionPane.YES_NO_OPTION);
}while(option==JOptionPane.YES_OPTION);

}
}
