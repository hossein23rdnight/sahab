package com.sahab.sahab.controller;


import com.sahab.sahab.model.Log;
import com.sahab.sahab.model.Warning;
import com.sahab.sahab.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/v1/Log")
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(/*@RequestParam Warning warning*/) {

        Warning warning = new Warning();
        warning.setAppname("appleTv");
        warning.setInfo("warning");
        warning.setWarnText("bad thing occurred");
        warning.setMsg1("bad thing occurred");
        warning.setMsg2("bad thing occurred");
        warning.setRate("5");

        logRepository.save(warning);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Warning> getAllUsers() {
        return logRepository.findAll();
    }


}
