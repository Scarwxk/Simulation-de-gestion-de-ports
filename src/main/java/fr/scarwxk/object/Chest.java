package fr.scarwxk.object;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.service.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends Entity {
    public Chest(GamePanel gp) {
        super(gp);
        this.setName("Chest");
        try {
            this.down1 = (ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
