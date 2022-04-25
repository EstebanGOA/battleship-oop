package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShipPanel extends JPanel {

    private final String FONT_BOLD = "fonts/Poppins-Bold.ttf";

    public ShipPanel (String shipName, String shipPath, int imageWidth, int imageHeight) {
        Font font = initializeFont (FONT_BOLD, 15F);

        JImagePanel shipPanel = new JImagePanel("sprites/ship_panel.png");
            shipPanel.setPreferredSize(new Dimension(250,125));
            shipPanel.setLayout(new GridBagLayout());
            shipPanel.setOpaque(false);

            JLabel shipNameText = new JLabel();
                shipNameText.setText(shipName);
                shipNameText.setForeground(Color.white);
                shipNameText.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                shipNameText.setFont(font);

            JImagePanel yourShipImage = new JImagePanel (shipPath);
                yourShipImage.setPreferredSize(new Dimension(imageWidth,imageHeight));
                yourShipImage.setOpaque(false);

        GridBagConstraints gbc_yourShips = new GridBagConstraints();
            gbc_yourShips.gridx = 0; gbc_yourShips.gridy = 0;
            shipPanel.add(shipNameText, gbc_yourShips);

            gbc_yourShips.gridx = 0; gbc_yourShips.gridy = 1;
            shipPanel.add(yourShipImage, gbc_yourShips);

        setBorder(BorderFactory.createEmptyBorder(-15,0,-15,0));
        setOpaque(false);
        add(shipPanel);
    }

    public Font initializeFont (String fontPath, float fontSize) {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
