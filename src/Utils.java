import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author akhil
 */
public class Utils {
    static void exit(JFrame currentFrame) {
        JOptionPane.showMessageDialog(currentFrame, "Exiting!");
        System.exit(0);
    }

    static void showMessage(JFrame currentFrame, String message) {
        JOptionPane.showMessageDialog(currentFrame, message);
    }

    /**
     *
     * @param s String to be encrypted
     * Each Character of the String is XOR'd with the length of the String
     * @return String with each character XOR'd with length
     */
    static String encrypt(String s)
    {
        String n = "";
        int l = s.length();
        for (int i=0;i<l;i++)
        {
            char c = s.charAt(i);
            int t = (int)c;
            t = t ^ l;
            c = (char)(t);
            n += c;
        }
        return n;
    }

    static String decrypt(String s) {
        return encrypt(s);
    }

}
