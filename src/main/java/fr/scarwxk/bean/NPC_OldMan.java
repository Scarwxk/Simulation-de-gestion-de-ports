package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        setDirection("down");
        speed = 1;

        getImage();
    }

    private void getImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_up_1.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_down_1.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_down_1.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_down_1.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_left_1.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_right_1.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_right_1.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/npc/oldman_right_1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Random 1-100
            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75) {
                setDirection("right");
            }

            actionLockCounter = 0;
        }
    }
}
