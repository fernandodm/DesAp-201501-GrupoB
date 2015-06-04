package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class Mains {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-services-context.xml","META-INF/spring-persistence-context.xml");
//        GeneralService gs = (GeneralService) context.getBean("services.general");
//        DiagnosticService ps = gs.getDiagnosticService();
//        java.lang.System.out.println(ps.retriveAll().isEmpty());

    	
    	List<String> ar =  new ArrayList<String>();
    	List<String> br =  new ArrayList<String>();
    	ar.add("tos");
    	ar.add("estornudos");
    	ar.add("diarrea");
    	br.add("tos");
    	br.add("diarrea");
    	java.lang.System.out.println(ar.containsAll(br));
    }

}