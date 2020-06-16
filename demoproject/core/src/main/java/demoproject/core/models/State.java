package demoproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Model(adaptables = Resource.class)
public class State {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getState() {
        return state;
    }

    public void seSstate(String state) {
        this.state = state;
    }

    @Inject
    @Optional
    private String state;

    @Inject
    @Optional
    private List<Resource> cities;

    @Optional
    private List<City> citiesList = new ArrayList<>();


    public List<City> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<City> citiesList) {
        this.citiesList = citiesList;
    }


    @PostConstruct
    protected void init() {
        logger.debug("In init method of Country model.");
        if (cities != null && !cities.isEmpty()) {
            for (Resource resource : cities) {
                City city = resource.adaptTo(City.class);
                citiesList.add(city);
            }
        }
    }

}
