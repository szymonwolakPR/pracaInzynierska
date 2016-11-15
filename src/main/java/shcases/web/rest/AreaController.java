package shcases.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "/place/{place}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getBlog(ModelAndView mv, @PathVariable String place) {

        List<Grodek> aa = andrzej(place);

        mv.addObject("place", aa);
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
}
