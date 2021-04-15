package com.moneylion.assessment;

import com.moneylion.assessment.model.FeaturePermission;
import com.moneylion.assessment.repository.FeaturePermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.moneylion.assessment.model.User;
import com.moneylion.assessment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@ComponentScan("com.moneylion.assessment")
public class AssessmentApplication {

    private static final Logger log = LoggerFactory.getLogger(AssessmentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AssessmentApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, FeaturePermissionRepository featurePermissionRepository) {
        return (args) -> {
            log.info("Initializing user seeding");

            List<String> usersToSeed = Stream.of("junhang@hotmail.com", "smith@gmail.com", "john@gmail.com", "bob@gmail.com", "jane@gmail.com").collect(Collectors.toList());

            for (String email : usersToSeed) {
                userRepository.save(new User(email));
            }

            for (User user : userRepository.findAll()) {
                log.info("seeding features for " + user.toString());

                featurePermissionRepository.save(new FeaturePermission(user, "feature_1", true));
                featurePermissionRepository.save(new FeaturePermission(user, "feature_2", true));
                featurePermissionRepository.save(new FeaturePermission(user, "feature_3", true));

            }

            log.info("verifying that all user features have been seeded");
            for (FeaturePermission featurePermission : featurePermissionRepository.findAll()) {
                log.info("verify seeded feature " + featurePermission.toString());
            }

        };
    }

}
