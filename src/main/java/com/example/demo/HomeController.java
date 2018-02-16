package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "mainpage"; }

    @GetMapping("/summary")
    public String resumeSummary(Model model){
        model.addAttribute("summary", resumeStore.findAll());
        return "summaryhtml";}

    @GetMapping("/contact")
    public String contactInfo(Model model){
        model.addAttribute("contact", new Resume());
        return "contacthtml";
    }

    @GetMapping("/education")
    public String educationInfo(Model model){
        model.addAttribute("education", new Education());
        return "educationhtml";
    }

    @GetMapping("/skills")
    public String skillsInfo(Model model){
        model.addAttribute("skill", new Skills());
        return "skillhtml";
    }

    @GetMapping("/experience")
    public String experienceInfo(Model model){
        model.addAttribute("experiences", new Experience());
        return "experiencehtml";
    }

    @GetMapping("/refrences")
    public String refrencesInfo(Model model){
        return "refrenceshtml";
    }

    @GetMapping("/resume")
    public String fullresume(Model model){
        model.addAttribute("resume", resumeStore.findAll());
        model.addAttribute("skill",skillsStore.findAll());
        model.addAttribute("education", educationStore.findAll());
        model.addAttribute("experience", experienceStore.findAll());
        return "resumehtml";
    }

    @GetMapping("/coverletter")
    public String coverletter(Model model){
        model.addAttribute("coverLetter",resumeStore.findAll());
        return "coverletter";
    }

    @PostMapping("/processEducation")
    public String processEducation(@Valid Education education, BindingResult result) {
        if (result.hasErrors()) {
            return "educationhtml";
        }
        educationStore.save(education);
        return "redirect:/educationAdded";}


    @PostMapping("/processSkill")
    public String processSkill(@Valid Skills skills, BindingResult result) {
        if (result.hasErrors()) {
            return "skillshtml";
        }
        skillsStore.save(skills);
        return "redirect:/skillAdded";}


    @PostMapping("/processExperience")
    public String processExperience(@Valid Experience experience, BindingResult result) {
        if (result.hasErrors()) {
            return "experiencehtml";
        }
        experienceStore.save(experience);
        return "redirect:/experienceAdded";}

    @PostMapping("/processContact")
    public String processContact(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "contacts";
        }
        resumeStore.save(resume);
        return "/contactAdded";}

    @GetMapping("/editLetter")
    public String addCoverLetter(Model model){
        model.addAttribute("coverletter", new Resume());
        return "coverlettereditor";}

    @PostMapping("/processLetter")
    public String processletter(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "coverlettereditor";
        }
        resumeStore.save(resume);
        return "/coverletter";}

    @GetMapping("/login")
    public String loginpages(Model model){
        return "login";
    }

}
