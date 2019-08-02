package co.id.gooddoctor.patrio.controller;

import co.id.gooddoctor.patrio.entity.Content;
import co.id.gooddoctor.patrio.service.ProcessExtractRSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RSSController {

    @Autowired
    ProcessExtractRSS processExtractRSS;

    @GetMapping("/list")
    public String list(Model model) {

        List<Content> contentList = processExtractRSS.retrieveData();
        model.addAttribute("contentList", contentList);

        return "list"; //view
    }

    @PostMapping("/listDetail")
    public String listDetail(@ModelAttribute(value="content") Content content, Model model) {

        System.out.println("sisi = " + content);
        model.addAttribute("content", content);

        return "listDetail"; //view
    }

    @GetMapping("/reload")
    public String reload(Model model) {

        List<Content> contentList = processExtractRSS.extractRSS();
        model.addAttribute("contentList", contentList);

        return "landing-page"; //view
    }

}
