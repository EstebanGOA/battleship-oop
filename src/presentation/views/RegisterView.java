package presentation.views;

import presentation.controllers.RegisterController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RegisterView extends JPanel {

    /**
     * Guardamos la referencia del parent para cambiar las ventanas al realizar ciertas interacciones en la ventana actual.
     */
    private final MainView mainView;

    /**
     * Variables globales donde guardaremos la información escrita por el usuario en pantalla.
     */
    private JTextField jEmailField;
    private JPasswordField jPasswordField;
    private JPasswordField jVerifyPasswordField;
    private JTextField jUserField;
    private JImagePanel jImageButton;
    private JLabel jLoginLabel;


    public RegisterView(MainView mainView) {

        this.mainView = mainView;

        this.jEmailField = new JTextField();
        this.jPasswordField = new JPasswordField();
        this.jVerifyPasswordField = new JPasswordField();
        this.jUserField = new JTextField();

        Font font = initializeFont();

        // ------------------------ Background Image ------------------------ //

        JImagePanel backgroundPanel = new JImagePanel("sprites/login_background.png");
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        displayObjects(backgroundPanel, gbc, font);
        add(backgroundPanel);

        // setVisible(true);
    }

    // ------------------------ Display Objects ------------------------ //

    public void displayObjects (JPanel backgroundPanel, GridBagConstraints gbc, Font font) {
        gbc.gridx = 0; gbc.gridy = 0;
        backgroundPanel.add(addSeparator(0,150), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        backgroundPanel.add(usernamePanel(font), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        backgroundPanel.add(addSeparator(0,25), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(emailPanel(font), gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(addSeparator(0,25), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(passwordPanel(font, jPasswordField), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        backgroundPanel.add(addSeparator(0,25), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        backgroundPanel.add(passwordPanel(font, jVerifyPasswordField), gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        backgroundPanel.add(addSeparator(0,50), gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        backgroundPanel.add(buttonPanel(), gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        backgroundPanel.add(addSeparator(0,25), gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        backgroundPanel.add(loginLabel(font), gbc);
    }

    // ------------------------ Login Button ------------------------ //

    public JPanel buttonPanel () {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(200,100));
        buttonPanel.setOpaque(false);

        this.jImageButton = new JImagePanel("sprites/register_button.png");
        this.jImageButton.setPreferredSize(new Dimension(100,100));
        this.jImageButton.setOpaque(false);
        this.jImageButton.setName("register");

        buttonPanel.add(this.jImageButton);

        return buttonPanel;
    }

    // ---------------- Label to change to the login view -------------- //

    public JLabel loginLabel (Font font) {
        this.jLoginLabel = new JLabel("Already registered? Log in");
        this.jLoginLabel.setFont(font);
        this.jLoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.jLoginLabel.setForeground(new Color(200,200,200));
        this.jLoginLabel.setName("return_login");

        return this.jLoginLabel;
    }

    public JPanel emailPanel (Font font) {
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.setPreferredSize(new Dimension(350, 35));
        emailPanel.setOpaque(false);

        JImagePanel emailIco = new JImagePanel("sprites/email_ico.png");
        emailIco.setOpaque(false);
        emailIco.setPreferredSize(new Dimension(35,0));

        this.jEmailField = new JTextField(10);
        this.jEmailField.setOpaque(false);
        this.jEmailField.setFont(font);
        this.jEmailField.setForeground(new Color(255,255,255));
        this.jEmailField.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        this.jEmailField.setBorder(addBottomBorder(this.jEmailField));

        emailPanel.add(emailIco);
        emailPanel.add(addSeparator(20,0));
        emailPanel.add(this.jEmailField);

        return emailPanel;
    }

    public JPanel passwordPanel (Font font, JPasswordField jPasswordField) {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setPreferredSize(new Dimension(350, 35));
        passwordPanel.setOpaque(false);

        JImagePanel passwordIco = new JImagePanel("sprites/lock_ico.png");
        passwordIco.setOpaque(false);
        passwordIco.setPreferredSize(new Dimension(35,0));

        jPasswordField.setOpaque(false);
        jPasswordField.setFont(font);
        jPasswordField.setForeground(new Color(255,255,255));
        jPasswordField.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));
        jPasswordField.setBorder(addBottomBorder(jPasswordField));

        passwordPanel.add(passwordIco);
        passwordPanel.add(addSeparator(20,0));
        passwordPanel.add(jPasswordField);

        return passwordPanel;
    }

    public JPanel usernamePanel (Font font) {
        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);
        usernamePanel.setPreferredSize(new Dimension(350, 35));
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JImagePanel userIco = new JImagePanel("sprites/user_ico.png");
        userIco.setOpaque(false);
        userIco.setPreferredSize(new Dimension(35,0));

        this.jUserField = new JTextField();
        this.jUserField.setFont(font);
        this.jUserField.setOpaque(false);
        this.jUserField.setForeground(new Color(255,255,255));
        this.jUserField.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 20));

        this.jUserField.setBorder(addBottomBorder(this.jUserField));

        usernamePanel.add(userIco);
        usernamePanel.add(addSeparator(20,0));
        usernamePanel.add(this.jUserField);

        return usernamePanel;
    }

    public Border addBottomBorder (JTextField text) {
        Border oldBorder = text.getBorder();
        Border whiteBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
        return BorderFactory.createCompoundBorder(whiteBorder, oldBorder);
    }

    public Font initializeFont () {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Bold.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public JPanel addSeparator (int width, int height) {
        JPanel space = new JPanel();
        space.setLayout(new BoxLayout(space, BoxLayout.Y_AXIS));
        space.setOpaque(false);

        Component rigidArea = Box.createRigidArea(new Dimension(width, height));
        space.add(rigidArea);
        return space;
    }

    /**
     * Añadirá el listener a los componentes de la vista.
     * @param controller Controllador de esta vista.
     */
    public void registerController(MouseListener controller) {
        jImageButton.addMouseListener(controller);
    }

    /**
     * Añadirá el listener a la vista principal
     * @param main Vista principal que controla la vista que se muestra.
     */
    public void registerMasterView(MouseListener main) {
        jLoginLabel.addMouseListener(main);
    }

    /**
     * Cuando acabemos las operaciones en el registro cambiaremos la ventana que mostramos al usuario.
     */
    public void registerCompleted() {
        mainView.switchPanel("login");
    }

    /**
     * Recogerá la información introducida por el usuario en los JComponent.
     * @return Devolverá un HashMap con la información introducida por el usuario.
     */
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", jUserField.getText());
        data.put("password", String.valueOf(jPasswordField.getPassword()));
        data.put("password_verify", String.valueOf(jVerifyPasswordField.getPassword()));
        data.put("email", jEmailField.getText());
        return data;
    }

    /**
     * Abrirá una ventana como popup en el centro dando al usuario un mensaje informativo.
     * @param message Mensaje que se mostrará en la ventana emergente.
     */
    public void popupMessage(String message) {
        if (message.isEmpty()) {
            System.err.println("La ventana emergente se ha cancelado ya que el mensaje está vacío.");
            return;
        }
        new JPopup(message);
    }

    /**
     * Reseteará el contenido de las contraseñas.
     */
    public void cleanPassword() {
        jVerifyPasswordField.setText("");
        jPasswordField.setText("");
    }

    /**
     * Reseteará el contenido del formulario.
     */
    public void cleanFormulary() {
        jUserField.setText("");
        jEmailField.setText("");
        jVerifyPasswordField.setText("");
        jPasswordField.setText("");
    }
}
