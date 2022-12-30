package fr.scarwxk.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends SuperObject{
    public Door() {
        this.setName("Door");
        try {
            this.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.setCollision(true);
    }
}
