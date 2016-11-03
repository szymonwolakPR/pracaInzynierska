package shcases.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shcases.repository.GrodekRepository;

import javax.inject.Inject;

/**
 * Created by SzymonW on 2016-11-03.
 */
@RestController
public class AreaController {
    @Inject
    GrodekRepository grodekRepository;

    @RequestMapping(value = "{details}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getBlog(ModelAndView mv, @PathVariable String details) {
        grodekRepository.findOne(details);
        mv.addObject("details", grodekRepository.findOne(details));
        mv.setViewName("details");
        return mv;
    }
}
