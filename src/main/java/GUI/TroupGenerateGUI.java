package GUI;

import javax.swing.*;

public class TroupGenerateGUI {
    private static TroupGenerateGUI instance = null;
    private JFrame frame;

    private TroupGenerateGUI(){}
    public static TroupGenerateGUI getInstance() {
        if(instance == null){
            instance = new TroupGenerateGUI();
        }
        return instance;
    }
}
