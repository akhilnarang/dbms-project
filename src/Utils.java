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
}
