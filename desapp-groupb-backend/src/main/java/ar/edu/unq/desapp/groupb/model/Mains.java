package ar.edu.unq.desapp.groupb.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unq.desapp.groupb.services.DiagnosticService;
import ar.edu.unq.desapp.groupb.services.GeneralService;

public class Mains {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-services-context.xml","META-INF/spring-persistence-context.xml");
        GeneralService gs = (GeneralService) context.getBean("services.general");
        DiagnosticService ps = gs.getDiagnosticService();
        java.lang.System.out.println(ps.retriveAll().isEmpty());

    }

}