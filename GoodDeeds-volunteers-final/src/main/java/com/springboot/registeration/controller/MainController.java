package com.springboot.registeration.controller;


import com.springboot.registeration.service.EventService;
import com.springboot.registeration.service.VolunteerService;
import com.springboot.registeration.dto.EventDTO;
import com.springboot.registeration.dto.VolunteerDTO;
import com.springboot.registeration.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/volunteers")
public class MainController {

    private VolunteerService volunteerService;
    private EventService eventService;

    private  int currentPage = 1;
    private  int pageSize = 5;

    private static String volunteers="redirect:/volunteers";

    @Autowired
    public MainController(VolunteerService volunteerService, EventService eventService) {
        this.volunteerService = volunteerService;
        this.eventService = eventService;
    }

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<VolunteerDTO> volunteerPage = volunteerService.getAllVolunteer(pageable);

        model.addAttribute("volunteerPage", volunteerPage);

        int totalPages = volunteerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "volunteers";
    }


    @GetMapping(value = "/add")
    public String addVolunteer(@Valid Model model) {
        List<EventDTO> eventList = eventService.getAllEvent();
        model.addAttribute("volunteer", new VolunteerDTO());
        model.addAttribute("eventList", eventList);

        return "addvolunteer";
    }


    @PostMapping(value = "/saveAdd")
    public String saveAdd(VolunteerDTO volunteerDTO) {
        volunteerService.saveAddVolunteer(volunteerDTO);

        return volunteers;
    }
    @PostMapping(value = "/saveEdit")
    public String saveEdit(Volunteer volunteer) {
        volunteerService.saveVolunteer(volunteer);

        return volunteers;
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteVolunteer(@PathVariable("id") Integer id) {
        volunteerService.deleteVolunteer(id);

        return volunteers;
    }

    @GetMapping(value = "/edit/{id}")
    public String editVolunteer(@PathVariable("id") Integer id,Model model) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteerById(id);
        List<EventDTO> eventList = eventService.getAllEvent();
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("eventList", eventList);

        return "editvolunteer";
    }


}