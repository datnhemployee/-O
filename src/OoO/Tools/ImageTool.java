package OoO.Tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import javax.imageio.ImageIO;

public final class ImageTool {

    public static final String imgFolderDir
            = FileSystems.getDefault().getPath("").toAbsolutePath().toString()
            + "\\src\\OoO\\img\\";

    public static final int defaultWidth = 100;
    public static final int defaultHeight = 100;

    public ImageTool() {}

    public static Image getScaledImage(
            String fileName,
            int width,
            int height) 
            {
        try {
            File tempFile = new File(imgFolderDir + fileName);
            Image tempImage = ImageIO.read(tempFile);
            return tempImage.getScaledInstance(
                    width, 
                    height, 
                    Image.SCALE_DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
