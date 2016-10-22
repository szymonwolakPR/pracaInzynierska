package shcases.concretepage.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import shcases.concretepage.bean.Writer;
import shcases.web.rest.vm.LoggerVM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlogController {
	@Autowired
	private Writer writer;
	@RequestMapping(value = "/blog")
	 public ModelAndView getBlog(ModelAndView mv) {
		mv.addObject("currentDate", new Date());
		mv.addObject("writers", writer.getWriters());
	    mv.setViewName("myblog");
	    return mv;
	}


        @RequestMapping(value = "/koks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ModelAndView getList(ModelAndView mv) {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            mv.setViewName("myblog");
            List<Logger>  a =  context.getLoggerList();
            mv.addObject("dupa", a);
            return mv;
        }
}
