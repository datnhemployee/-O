package OoO.Tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JList;

public final class FontTool{
    public FontTool(){}
    
    public static Font getFont(String name,float Size) {
    Font font = null;
    if (name == null) {
        return new Font("Dialog", Font.BOLD, 100);
    }
    try {
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] arrFont = ge.getAllFonts();
        for(Font RESULT: arrFont){
            if(RESULT.getFamily().equals(name)){
                return RESULT.deriveFont(Size);
            }
        }
    } 
    catch (Exception ex) {
        return new Font("Dialog", Font.BOLD, 100);
    }
    return font;
}
}