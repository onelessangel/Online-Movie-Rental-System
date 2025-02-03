package com.pjsh.onlinemovierental.configurations;

import com.pjsh.onlinemovierental.repositories.RentalRepository;
import com.pjsh.onlinemovierental.repositories.UserRepository;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import com.pjsh.onlinemovierental.services.RentalService;
import com.pjsh.onlinemovierental.services.UserService;
import com.pjsh.onlinemovierental.services.VideoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public VideoService videoService(VideoRepository videoRepository) {
        return new VideoService(videoRepository);
    }

    @Bean
    public RentalService rentalService(RentalRepository rentalRepository, VideoRepository videoRepository) {
        return new RentalService(rentalRepository, videoRepository);
    }
}
