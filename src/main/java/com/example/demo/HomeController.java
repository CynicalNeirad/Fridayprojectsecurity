package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ResumeRepository resumeStore;
    @Autowired
    SkillsRepository skillsStore;
    @Autowired
    EducationRepository educationStore;
    @Autowired
    ExperienceRepository experienceStore;
    @Autowired
    UserRepository userList;


    @GetMapping("/")
    public String directory(Model model) {
        return "mainpage";
    }

    @GetMapping("/summary")
    public String resumeSummary(Model model) {
        model.addAttribute("summarys", resumeStore.findAll());
        return "summaryhtml";
    }

    @GetMapping("/contact")
    public String contactInfo(Model model) {
        model.addAttribute("contact", new Resume());
        return "contacthtml";
    }

    @GetMapping("/education")
    public String educationInfo(Model model) {
        model.addAttribute("education", new EducationInfo());
        return "educationhtml";
    }

    @GetMapping("/skills")
    public String skillsInfo(Model model) {
        model.addAttribute("skill", new Skills());
        return "skillhtml";
    }

    @GetMapping("/experience")
    public String experienceInfo(Model model) {
        model.addAttribute("experiences", new ExperienceInfo());
        return "experiencehtml";
    }

    @GetMapping("/references")
    public String refrencesInfo(Model model) {
        model.addAttribute("references", resumeStore.findAll());
        return "refrenceshtml";
    }

    @GetMapping("/resume")
    public String fullresume(Model model) {
        model.addAttribute("resume", resumeStore.findAll());
        model.addAttribute("skill", skillsStore.findAll());
        model.addAttribute("education", educationStore.findAll());
        model.addAttribute("experience", experienceStore.findAll());
        return "resumehtml";
    }

    @GetMapping("/coverletter")
    public String coverletter(Model model) {
        model.addAttribute("coverletters", resumeStore.findAll());
        return "coverletter";
    }

    @PostMapping("/processEducation")
    public String processEducation(@Valid EducationInfo education, BindingResult result) {
        if (result.hasErrors()) {
            return "educationhtml";
        }
        educationStore.save(education);
        return "/educationAdded";
    }


    @PostMapping("/processSkill")
    public String processSkill(@Valid Skills skills, BindingResult result) {
        if (result.hasErrors()) {
            return "skillshtml";
        }
        skillsStore.save(skills);
        return "/skillAdded";
    }


    @PostMapping("/processExperience")
    public String processExperience(@Valid ExperienceInfo experience, BindingResult result) {
        if (result.hasErrors()) {
            return "experiencehtml";
        }
        experienceStore.save(experience);
        return "experienceAdded";
    }

    @PostMapping("/processContact")
    public String processContact(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "contacts";
        }
        resumeStore.save(resume);
        return "/contactAdded";
    }

    @GetMapping("/login")
    public String loginpages(Model model) {
        return "login";
    }

    @GetMapping("/addasummary")
    public String addasummary(Model model) {
        model.addAttribute("summary", new Resume());
        return "addsummary";
    }

    @PostMapping("/processsummary")
    public String processsummary(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "addsummary";
        }
        resumeStore.save(resume);
        return "redirect:/summary";
    }

    @GetMapping("/addreference")
    public String addreference(Model model){
        model.addAttribute("reference", new Resume());
        return "refrenceadded";}

    @PostMapping("/processreference")
    public String processreference(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "referenceadded";
        }
        resumeStore.save(resume);
        return "redirect:/references";}


    @GetMapping("/addcoverletter")
    public String addcoverletter(Model model){
        model.addAttribute("resume", new Resume());
        return "coverletteradded";}


    @PostMapping("/processcoverletter")
    public String processcoverletter(@Valid @ModelAttribute("resume") Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "coverletteradded";
        }
        resumeStore.save(resume);
        return "redirect:/coverletter";}

}
