package bikerepairshop.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import bikerepairshop.view.handler.ViewHandler;

import java.io.IOException;

/**
 * Manages bikerepairshop.view loading and injects the application bikerepairshop.controller into each FXML handler.
 * Lives entirely in the bikerepairshop.view layer — knows nothing about what Controller does internally.
 */
public class ViewManager {

    private final Object controller; // your real Controller — kept as Object to avoid import

    public ViewManager(Object controller) {
        this.controller = controller;
    }

    /**
     * Loads an FXML file and injects the bikerepairshop.controller into the handler.
     * The FXML handler class must implement {@link ViewHandler}.
     *
     * @param fxmlPath classpath-relative path, e.g. "/fxml/receptionist.fxml"
     * @return the loaded Parent node
     */
    public FXMLLoader loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(handlerClass -> {
                try {
                    Object handler = handlerClass.getDeclaredConstructor().newInstance();
                    if (handler instanceof ViewHandler) {
                        ((ViewHandler) handler).setController(controller);
                    }
                    return handler;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create handler: " + handlerClass.getName(), e);
                }
            });
            loader.load();
            return loader;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        }
    }



    @SuppressWarnings("unchecked")
    public <T> T loadInto(String fxmlPath, StackPane container) {
        FXMLLoader loader = loadView(fxmlPath);
        container.getChildren().setAll((Parent) loader.getRoot());
        return (T) loader.getController();
    }
}
