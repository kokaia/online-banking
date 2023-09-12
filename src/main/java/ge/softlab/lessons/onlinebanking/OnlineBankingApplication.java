package ge.softlab.lessons.onlinebanking;

import ge.softlab.lessons.onlinebanking.test.TestStream;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", in = SecuritySchemeIn.COOKIE, scheme = "basic" )
@OpenAPIDefinition(
    tags = {
        @Tag(name="Persons", description = "პიროვნებების მართვა"),
        @Tag(name="Transactions", description = "გადარიცხვები")
    },
    security = {
        @SecurityRequirement(name = "BasicAuth")
    },
    info = @Info(
        title= "სოფტლაბის პროგრამა",
        version = "1.0.0",
        description = "მაგალითი პროგრამა",
        contact = @Contact(
            name = "Softlab Support",
            url = "http://softlab.ge"
        ),
        license = @License(
            name = "GNU GPL v3",
            url = "https://www.gnu.org/licenses/gpl-3.0.en.html"
        )
    )
)
@SpringBootApplication
public class OnlineBankingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OnlineBankingApplication.class);
	}

	@PostConstruct
	public void init(){
        TestStream.run();
	}

}
