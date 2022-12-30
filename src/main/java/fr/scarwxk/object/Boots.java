package fr.scarwxk.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends SuperObject{
    public Boots() {
        this.setName("Boots");
        try {
            this.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
