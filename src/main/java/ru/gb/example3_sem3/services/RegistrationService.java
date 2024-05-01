package ru.gb.example3_sem3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.example3_sem3.domain.User;

@Service
public class RegistrationService {

    @Autowired
    private  DataProcessingService dataProcessingService;
    private UserService userService;
    private NotificationService notificationService;

    public RegistrationService(DataProcessingService dataProcessingService, UserService userService, NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public UserService getUserService() {
        return userService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
    //Поля UserService, NotificationService

    public String processRegistration(String name, int age, String email){
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUser(user);
        return notificationService.notifyUser(user);
    }
}
