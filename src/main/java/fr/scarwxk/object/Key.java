package fr.scarwxk.object;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.service.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends Entity {
    public Key(GamePanel gp) {
        super(gp);
        this.setName("Key");

        try {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
