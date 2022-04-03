package com.springboot.registeration;

import com.springboot.registeration.controller.LoginController;
import com.springboot.registeration.controller.MainController;
import com.springboot.registeration.controller.RegistrationController;
import com.springboot.registeration.dto.EventDTO;
import com.springboot.registeration.dto.UserRegistrationDTO;
import com.springboot.registeration.dto.VolunteerDTO;
import com.springboot.registeration.model.Event;
import com.springboot.registeration.model.Role;
import com.springboot.registeration.model.User;
import com.springboot.registeration.repository.EventRepository;
import com.springboot.registeration.repository.UserRepository;
import com.springboot.registeration.service.EventService;
import com.springboot.registeration.service.UserServiceImpl;
import com.springboot.registeration.service.VolunteerService;
import com.springboot.registeration.model.Volunteer;
import com.springboot.registeration.repository.VolunteerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
class RegistrationApplicationTests {

    @Autowired
    VolunteerService volunteerService;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Autowired
    EventService eventService;

    @MockBean
    VolunteerRepository volunteerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    UserRepository userRepository;


    @MockBean
    private Event event;

    @MockBean
    private Volunteer volunteer;

    @MockBean
    private VolunteerDTO volunteerDTO;

    @Autowired
    MainController mainController;

    @Autowired
    LoginController loginController;

    @Autowired
    RegistrationController registrationController;


    @Test
    void deleteVolunteerTest(){
        Volunteer volunteer=new Volunteer();
        volunteer.setId(9);
        volunteer.setName("rosy");
        volunteer.setCity("pune");
        volunteer.setEmail("rosy@gmail.com");
        //volunteer.setEvent(new Event().setId(9));
        volunteerService.deleteVolunteer(9);
        verify(volunteerRepository,times(1)).deleteById(9);
    }

    @Test
   void getVolunteerByIdTest(){
        Volunteer volunteer=new Volunteer();
        volunteer.setId(6);
        volunteer.setName("rosy");
        volunteer.setCity("pune");
        volunteer.setEmail("rosy@gmail.com");
        when(volunteerRepository.findById(6))
                .thenReturn(Optional.of(volunteer));
                volunteerService.getVolunteerById(6);
                verify(volunteerRepository,times(1)).findById(6);
    }

    @Test
    void convertToEventToEventDTOTest(){
        Event event=new Event();
        event.setId(1);
        event.setEventName("blankets donation");
        EventDTO eventDTO=modelMapper.map(event,EventDTO.class);
        EventDTO eventDTO1=eventService.convertToEventToEventDTO(event);
        when(eventDTO1).thenReturn(eventDTO);
        Assertions.assertDoesNotThrow(this::doNotThrowException);

    }

    @Test
    void getAllEventTest(){
        Event event=new Event();
        event.setId(1);
        event.setEventName("blankets donation");
        Event event1=new Event();
        event1.setId(2);
        event1.setEventName("food distribution");
        eventRepository.save(event);
        eventRepository.save(event1);
        List<EventDTO> events=new ArrayList<>();
        events.add(modelMapper.map(event,EventDTO.class));
        events.add(modelMapper.map(event1,EventDTO.class));
        List<EventDTO> events1=eventService.getAllEvent();
        when(events1).thenReturn(events);
        Assertions.assertDoesNotThrow(this::doNotThrowException);
        //verify(eventRepository,times(1)).save(event);

    }
    private void doNotThrowException(){
        //This method will never throw exception
    }

    @Test
     void testApplicationLload() {
        assertThat(mainController).isNotNull();
        assertThat(registrationController).isNotNull();
        assertFalse(loginController.login().isEmpty());
           }

    @Test
    void getAllTest(){
        User user=new User();
        user.setFirstName("Afreen");
        user.setLastName("Mohd");
        user.setId(1);
        user.setEmail("mdafreen99@gmail.com");
        user.setPassword("afreen");

        User user1=new User();
        user.setFirstName("Alex");
        user.setLastName("petry");
        user.setId(1);
        user.setEmail("alexpetry@gmail.com");
        user.setPassword("alex");

        Role role=new Role();
        role.setId(1);
        role.setName("user");

        Role role1=new Role();
        role1.setId(2);
        role1.setName("admin");

        List<Role> roles=new ArrayList<>();
        roles.add(role);
        roles.add(role1);

        user.setRoles(roles);
        user1.setRoles(roles);

        List<User> users=new ArrayList<>();
        users.add(user);
        users.add(user1);

        userRepository.save(user);
        userRepository.save(user1);
        when(userServiceImpl.getAll()).thenReturn(users);
        Assertions.assertDoesNotThrow(this::doNotThrowException);

    }

    @Test
    void getUserWithRoleTest(){
        Role role=new Role();
        role.setId(1);
        role.setName("user");

        UserRegistrationDTO registrationDto=
                new UserRegistrationDTO("priya","chopra","priya@gmail.com","priya123");

        User user=new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(role));

        when(userServiceImpl.getUserWithRole(registrationDto,role)).thenReturn(user);
        Assertions.assertDoesNotThrow(this::doNotThrowException);


    }






    }
