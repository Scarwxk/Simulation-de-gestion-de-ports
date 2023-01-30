package fr.scarwxk.object;

import fr.scarwxk.service.GamePanel;
import fr.scarwxk.bean.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends Entity {

    public Heart(GamePanel gp) {
        super(gp);
        this.setName("Heart");
        this.gp = gp;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
            this.image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            this.image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
