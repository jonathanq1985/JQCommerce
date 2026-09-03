package com.jqdigitalsolutions.jqcommerce.configuracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Ing_JQC: Clase principal del microservicio de configuración
// Tecnología: Spring Boot
// Finalidad: Punto de entrada de ms-configuracion

@SpringBootApplication
public class MsConfiguracionApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                MsConfiguracionApplication.class,
                args
        );

    }

}