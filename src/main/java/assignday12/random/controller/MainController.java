package assignday12.random.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import assignday12.random.exception.RandomNumberException;
import assignday12.random.model.noGen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
       // @Autowired
        //private Generate generate;
        private static final Logger logger = LoggerFactory.getLogger(MainController.class);

        @GetMapping("/getgen")
        public String showForm(Model model) {
            noGen generate = new noGen();
            generate.setNumberVal(0);
            model.addAttribute("generate",generate);
            return "form";
        }

        @PostMapping("/getgen")
        public String numberGen(@ModelAttribute noGen generate, Model model){
            logger.info(" from the form" + generate.getNumberVal());
            int numRndNum = generate.getNumberVal();
            
            if(numRndNum >10){
                throw new RandomNumberException();
            
            }
            
            String [] imgNumbers = {"1.png", "2.png", "3.png", "4.png", "5.png","6.png","7.png","8.png","9.png","10.png"};
            List<String> selectedImg = new ArrayList<String>();
            Random randNum = new Random();
            Set<Integer> set = new LinkedHashSet <Integer>();
            while(set.size() < numRndNum){
                Integer resultRanNum = randNum.nextInt(generate.getNumberVal() +1);
                set.add(resultRanNum); 
            }

            Iterator<Integer> it = set.iterator();
            Integer currentElem = null;int i =0;
            while(it.hasNext()){
                currentElem = it.next(); 
                //logger.info("current elem >" + currentElem);
                selectedImg.add(imgNumbers[currentElem.intValue()]);
                logger.info(selectedImg.get(i) + i); i++;
            }
            model.addAttribute("selectedarray", selectedImg.toArray());
            return "result";
        }
        
}
