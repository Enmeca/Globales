package com.glob.ufriends.apis;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
//Controlador temporal
@RequestMapping("/api/v1/home")
@RestController
public class HomeController {

    /* Obtener fecha, para probar conexion con el servidor desde el frontend
    @author: Luis Villalobos
    */
    @GetMapping("/date")
    public String getServerDate() {
        try {
            Thread.sleep(1000);
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        } catch (InterruptedException e) {
            return "Error";
        }
    }
}
