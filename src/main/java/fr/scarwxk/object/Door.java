package fr.scarwxk.object;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.service.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Entity {
    public Door(GamePanel gp) {
        super(gp);
        this.setName("Door");
        try {
            this.down1 = (ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setCollision(true);
    }
}
