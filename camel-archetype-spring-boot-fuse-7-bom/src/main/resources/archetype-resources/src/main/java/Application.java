package ${package};

#if (${cxfSupport} == 'true')
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Value;
#end
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
#if (${cxfSupport} == 'true')
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
#end
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application {

#if (${cxfSupport} == 'true')
    @Value("${cxf.path}")
    private String cxfPath;

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), cxfPath+"/*");
    }
#end

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }




}