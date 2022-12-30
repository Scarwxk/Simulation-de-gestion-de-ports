package fr.scarwxk.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject{
    public Chest() {
        this.setName("Chest");
        try {
            this.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
