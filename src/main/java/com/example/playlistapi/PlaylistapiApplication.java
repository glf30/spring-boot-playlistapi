package com.example.playlistapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlaylistapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistapiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner preloadData(PlaylistRepository playlistRepository) {
//		return args -> {
//			playlistRepository.save(new Playlist("Driving Playlist"));
//			playlistRepository.save(new Playlist("Chill vibes", "the chillest"));
//		};
//	}
}
