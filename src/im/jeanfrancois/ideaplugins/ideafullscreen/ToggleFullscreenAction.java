package im.jeanfrancois.ideaplugins.ideafullscreen;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.IdeFrame;

import javax.swing.*;

/**
 * Action that toggles the fullscreen state of the IDE
 *
 * @author jfim
 */
public class ToggleFullscreenAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        // Retrieve the AWT window
        IdeFrame ideFrame = anActionEvent.getData(IdeFrame.KEY);
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ideFrame.getComponent());

        frame.dispose();

        // If in fullscreen
        if(frame.isUndecorated()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setUndecorated(false);
                    frame.setBounds(frame.getGraphicsConfiguration().getBounds());
                    frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
                    frame.setVisible(true);
                }
            });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setUndecorated(true);
                    frame.setBounds(frame.getGraphicsConfiguration().getBounds());
                    frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);
                    frame.setVisible(true);
                }
            });
        }
    }
}
