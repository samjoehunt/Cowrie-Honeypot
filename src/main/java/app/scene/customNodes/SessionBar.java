package app.scene.customNodes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Controller class for SessionBar, a custom node representing a horizontal bar containing
 * information about a session present in the uploaded JSON file. The bar is clickable and
 * has a hover effect.
 */
public class SessionBar extends HBox {
    /**
     * Label containing the session's timestamp
     */
    @FXML
    private Label sessionTimeLabel;

    /**
     * Label containing the session's source IP
     */
    @FXML
    private Label sourceIPLabel;

    /**
     * Label containing the session's time frame (how long it lasted)
     */
    @FXML
    private Label sessionTimeFrameLabel;

    /**
     * Session's unique ID
     */
    private String sessionID;

    /**
     * A {@code Consumer} that handles click events on this SessionBar.
     * This callback receives the current SessionBar instance when invoked.
     */
    private Consumer<SessionBar> onClick;

    /**
     * Constructor method for a SessionBar.
     * Loads the FXML file containing the SessionBar's structure, and sets the max width
     * of the SessionBar node.
     */
    public SessionBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.scene/customNodes/sessionBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.75);
    }

    /**
     * Sets the callback to be invoked when this SessionBar is clicked.
     * @param onClick A {@code Consumer} that accepts this SessionBar instance
     *                and defines what to do on click events
     */
    public void setOnClick(Consumer<SessionBar> onClick) {
        this.onClick = onClick;
    }

    /**
     * Handles mouse click events on this SessionBar.
     * If an onClick callback has been set, it will be invoked with this SessionBar as argument.
     * @param event The mouse event that triggered the handler
     */
    @FXML
    private void handleClick(MouseEvent event) {
        if (onClick != null) {
            onClick.accept(this);
        }
    }

    /**
     * Setter method to set the text in the sessionTimeLabel
     * @param sessionTime Timestamp of this session's start
     */
    public void setSessionTimeLabel(String sessionTime) {
        sessionTimeLabel.setText(sessionTime);
    }

    /**
     * Setter method to set the text in the sourceIPLabel
     * @param sourceIP Source IP of this session
     */
    public void setSourceIPLabel(String sourceIP) {
        sourceIPLabel.setText(sourceIP);
    }

    /**
     * Setter method to set the text in the sessionTimeFrameLabel
     * @param sessionTimeFrame Time between the start and end of this session
     */
    public void setSessionTimeFrameLabel(String sessionTimeFrame) {
        sessionTimeFrameLabel.setText(sessionTimeFrame);
    }

    /**
     * Getter method to return the text contained in the sessionTimeLabel
     * @return Timestamp of the session's start
     */
    public String getSessionTime() {
        return sessionTimeLabel.getText();
    }

    /**
     * Getter method to return the text contained in the sourceIPLabel
     * @return Source IP of the session
     */
    public String getSourceIP() {
        return sourceIPLabel.getText();
    }

    /**
     * Getter method to return the text contained in the sessionTimeFrameLabel
     * @return Time between start and end of the session
     */
    public String getSessionTimeFrame() {
        return sessionTimeFrameLabel.getText();
    }

    /**
     * Setter method to set the session ID
     * @param sessionID Unique ID of the session
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Getter method to return the session ID
     * @return Unique ID of the session
     */
    public String getSessionID() {
        return sessionID;
    }
}
