package ru.geekbrains.senchenko.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonAdvices {

    private final EurekaClient eurekaClient;

    @Autowired
    public CommonAdvices(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @ModelAttribute
    public void pictureServiceUrlAttribute(Model model) {
        InstanceInfo server = eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");
    }
}
