package klicelab.web;

import klicelab.model.Experiment;
import klicelab.persistence.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hasee on 2017/4/23.
 */
@Controller
@RequestMapping("/")
public class ExperimentController {

    @Autowired
    ExperimentRepository experimentRepository;

    @RequestMapping(method= RequestMethod.GET)
    public String Index(Model model){
        List<Experiment> experiments = experimentRepository.getPagedExperiments((long) 0,100);
        model.addAttribute("experiments",experiments);
        return "ExperimentIndex";
    }
}
