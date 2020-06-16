package demoproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class States {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Named("sling:resourceType")
    @Default(values = "No resourceType")
    protected String resourceType;

    @Inject
    @Optional
    private List<Resource> states;

    private List<State> statesList = new ArrayList<>();

    public List<State> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<State> statesList) {
        this.statesList = statesList;
    }

    @PostConstruct
    protected void init() {
        logger.debug("In init of CountriesModel");
        if (states != null && !states.isEmpty()) {
            for (Resource resource : states) {
                State state = resource.adaptTo(State.class);
                statesList.add(state);
            }
        }
    }

}
