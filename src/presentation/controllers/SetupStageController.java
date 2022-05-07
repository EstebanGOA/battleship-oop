package presentation.controllers;

import presentation.views.SetupStageView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetupStageController implements MouseListener {

    private SetupStageView setupStageView;


    public SetupStageController(SetupStageView setupStageView) {
        this.setupStageView = setupStageView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((JComponent) e.getSource()).getName()) {
            case "start_attack_button" -> setupStageView.gameView();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
