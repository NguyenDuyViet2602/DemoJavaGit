package common;

import db.InventoryUtils;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Manh
 */
public class OpenPdf {

    public static void OpenById(String id) {
        try {
            if ((new File(InventoryUtils.billPath + id + ".pdf")).exists()) {
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + InventoryUtils.billPath + "" + id + ".pdf");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "File không tồn tại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
