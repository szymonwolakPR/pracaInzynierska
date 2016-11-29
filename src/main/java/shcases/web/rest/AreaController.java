package shcases.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shcases.config.apidoc.SwaggerConfiguration;
import shcases.domain.Grodek;
import shcases.repository.GrodekRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SzymonW on 2016-11-03.
 */
@RestController
public class AreaController {
    @Inject
    GrodekRepository grodekRepository;

    private final Logger log = LoggerFactory.getLogger(AreaController.class);

    @RequestMapping(value = "/place/{place}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getBlog(ModelAndView mv, @PathVariable String place) {

        log.warn("WAREN!");

        List<Grodek> aa = andrzej(place);
        String photoName = getPhoto().toLowerCase();

        mv.addObject("place", aa);
        mv.addObject("photoName", place );
        mv.setViewName("area");


        return mv;
    }

    public List<Grodek> andrzej(String a)
    {
        List<Grodek> allGrodekObjects = grodekRepository.findAll();


        return allGrodekObjects.stream()
                    .filter(s -> s.getPlace().equals(a))
                    .collect(Collectors.toList());
    }

    public String getPhoto() {
        List<Grodek> allGrodekObjects = grodekRepository.findAll();
        String photo = null;
        for (Grodek g : allGrodekObjects) {
            photo = g.getPlace();
        }
        return photo;
    }
}
