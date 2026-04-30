package bikerepairshop.view.handler;

/**
 * Implemented by all FXML handler classes.
 * ViewManager calls {@link #setController(Object)} before the FXML is loaded,
 * so the handler has access to the application bikerepairshop.controller when initialize() runs.
 *
 * NOTE: the bikerepairshop.controller is typed as Object here so this interface has zero coupling
 * to the bikerepairshop.controller layer. Each handler casts it to the concrete Controller type.
 */
public interface ViewHandler <T>{
    void setController(T controller);
}
