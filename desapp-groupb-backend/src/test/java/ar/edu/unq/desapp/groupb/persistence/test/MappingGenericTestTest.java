package ar.edu.unq.desapp.groupb.persistence.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ar.edu.unq.desapp.groupb.builders.AbstractBuilder;

public class MappingGenericTestTest {

    private XmlBeanFactory factory;

    protected ClassMetadata classMapping;

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        ClassPathResource resource = new ClassPathResource("/META-INF/spring-persistence-context.xml");
        this.factory = new XmlBeanFactory(resource);
        PropertyPlaceholderConfigurer ppc = (PropertyPlaceholderConfigurer) this.factory
                .getBean("persistence.propertyConfigurer");
        ppc.postProcessBeanFactory(this.factory);

        this.sessionFactory = (SessionFactory) this.factory.getBean("persistence.sessionFactory");

    }

    @Test
    public void mappingTest() {

        for (String key : this.sessionFactory.getAllClassMetadata().keySet()) {

            try {

                String[] names = key.split("\\.");

                String nameBuilder = "ar.edu.unq.desapp.groupb.builders." + names[names.length - 1] + "Builder";

                @SuppressWarnings({ "unchecked", "rawtypes" })
                Class<AbstractBuilder> classBuilder = (Class<AbstractBuilder>) Class.forName(nameBuilder);
                AbstractBuilder<?> builder = classBuilder.getConstructor().newInstance();

                Method method = classBuilder.getDeclaredMethod("anyObject");
                method.setAccessible(true);
                Object toSave = method.invoke(builder);

                // guardano el objeto de dominio
                Session session = this.sessionFactory.openSession();

                session.save(toSave);

                // obteniendo el objeto guardado
                Object savedObject = session.createQuery("SELECT o FROM " + key + " o").uniqueResult();

                assertTrue("Los objetos de clase " + key + " no son iguales",
                        EqualsBuilder.reflectionEquals(toSave, savedObject));

                session.close();

            } catch (Exception e) {
                fail("Fallo para la clase " + key + " : " + e.getMessage());

            }
        }
    }

    public XmlBeanFactory getFactory() {
        return this.factory;
    }

    public void setFactory(final XmlBeanFactory factory) {
        this.factory = factory;
    }

}
