package fr.scarwxk.object;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.service.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends Entity {
    public Boots(GamePanel gp) {
        super(gp);
        this.setName("Boots");
        try {
            this.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
