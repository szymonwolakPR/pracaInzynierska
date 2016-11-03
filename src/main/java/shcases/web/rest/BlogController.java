package shcases.web.rest;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shcases.concretepage.bean.Writer;
import shcases.domain.Grodek;
import shcases.domain.User;
import shcases.repository.GrodekRepository;
import shcases.repository.UserRepository;
import shcases.web.rest.util.PaginationUtil;
import shcases.web.rest.vm.LoggerVM;
import shcases.web.rest.vm.ManagedUserVM;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlogController {
    @Autowired
    private Writer writer;

    @Inject
    UserRepository userRepository;
    @Inject
    GrodekRepository grodekRepository;

    @RequestMapping(value = "details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ModelAndView getBlog(ModelAndView mv, @PathVariable String name) {
        //List<Writer> wr = grodekRepository.findAll()
        mv.addObject("writers", grodekRepository.findAll());

        //mv.addObject("writers", writer.getWriters());
        String ad = grodekRepository.findOne("580fde0d6a056e13207162ca").getDescription();
        mv.addObject("andrzej", grodekRepository.findOne("580fde0d6a056e13207162ca"));
        mv.addObject("currentDate", "afsf");
        mv.setViewName("myblog");
        return mv;
    }

//    @Timed
//    public List<Grodek> getAllGrodeks() {
//        List<Grodek> grodeks = grodekRepository.findAll();
//        return grodeks;
//    }





//    @RequestMapping(value = "/myblog",
//        method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<List<ManagedUserVM>> getAllUsers(Pageable pageable)
//        throws URISyntaxException {
//        Page<User> page = userRepository.findAll(pageable);
//        List<ManagedUserVM> managedUserVMs = page.getContent().stream()
//            .map(ManagedUserVM::new)
//            .collect(Collectors.toList());
//        return new ResponseEntity<>(managedUserVMs, HttpStatus.OK);
//    }




//        @RequestMapping(value = "/koks",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//        @Timed
//        public ModelAndView getList(ModelAndView mv) {
//            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//            mv.setViewName("myblog");
//            List<Logger>  a =  context.getLoggerList();
//            mv.addObject("dupa", a);
//            return mv;
//        }
}
