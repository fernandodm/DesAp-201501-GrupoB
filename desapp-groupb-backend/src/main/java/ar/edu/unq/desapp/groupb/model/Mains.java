package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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
    	
    	String f = "12/06/2015";
    	List<String> ff= Arrays.asList(StringUtils.split(f, "/"));
    	Calendar g = Calendar.getInstance();
    	g.set(Integer.parseInt(ff.get(2)), Integer.parseInt(ff.get(1)), Integer.parseInt(ff.get(0)));
    	
    	Calendar c = Calendar.getInstance();
  
    	
    	Calendar d = Calendar.getInstance();
    	d.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)-1,c.get(Calendar.DAY_OF_MONTH));
    	java.lang.System.out.println(c.get(Calendar.YEAR));
    	java.lang.System.out.println(c.get(Calendar.MONTH));
    	java.lang.System.out.println(c.get(Calendar.DAY_OF_MONTH));
    	java.lang.System.out.println("------------");

    	java.lang.System.out.println(d.get(Calendar.YEAR));
    	java.lang.System.out.println(d.get(Calendar.MONTH));
    	java.lang.System.out.println(d.get(Calendar.DAY_OF_MONTH));
    	java.lang.System.out.println("------------");
    	DateTime dte = new DateTime();
    	DateTime dte2 = new DateTime(2015,06,5,0,0);
    	
    	java.lang.System.out.println(dte2.minusMonths(6));
    	java.lang.System.out.println(dte2);
    	java.lang.System.out.println(dte.isAfter(dte2));
    
    }

}