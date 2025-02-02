package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.entities.users.AbstractUser;
import com.pjsh.onlinemovierental.entities.users.Admin;
import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.TVShowSeason;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.repositories.UserRepository;
import com.pjsh.onlinemovierental.services.RentalService;
import com.pjsh.onlinemovierental.services.UserService;
import com.pjsh.onlinemovierental.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MovieRentalSystem implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    private Admin loggedInAdmin = null;
    private Customer loggedInCustomer = null;
    private boolean userIsLoggedIn = false;
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private VideoService videoService;

    private enum Command {
        LOGIN, REGISTER, EXIT,
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to the Online Movie Rental System!");

        while (true) {
            if (!userIsLoggedIn) {
                System.out.println("""
                        Available commands:
                            1. Login
                            2. Register
                            3. Exit
                        Enter command number:""");
                String command = scanner.nextLine().trim();

                switch (command) {
                    case "1" -> handleLogin();
                    case "2" -> handleRegister();
                    case "3" -> handleExit();
                    default -> System.out.println("Invalid command! Please try again.");
                }
            } else {
                if (loggedInAdmin != null) {
                    System.out.println("""
                            Available commands:
                                1. Add video
                                2. Remove video
                                3. View all videos
                                4. View all customers
                                5. View all rentals
                                6. Exit
                            Enter command:""");
                    String command = scanner.nextLine().trim();

                    switch (command) {
                        case "1" -> addVideo();
                        case "2" -> removeVideo();
                        case "3" -> viewAllVideos();
                        case "4" -> viewAllCustomers();
//                        case "5" -> rentalService.viewAllRentals();
                        case "6" -> handleExit();
                        default -> System.out.println("Invalid command! Please try again.");
                    }
                }
            }
        }
    }

    private void viewAllCustomers() {
        List<Customer> customers = userService.viewAllCustomers();
        customers.forEach(customer ->
                System.out.println(customer.getUsername() + " - " + customer.getEmail() + " - " + customer.getMembershipType()));
    }

    private void viewAllVideos() {
        List<Video> videos = videoService.viewAllMovies();
        StringBuilder sb = new StringBuilder();

        for (Video video : videos) {
            sb.append(video.getTitle());

            if (video instanceof TVShowSeason) {
                sb.append(" Season ").append(((TVShowSeason) video).getSeasonNumber());
            }

            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private void removeVideo() {
        System.out.println("Enter video title: ");
        String title = scanner.nextLine().trim();

        System.out.println("Is this a TV show? (yes/no): ");
        String isTVShow = scanner.nextLine().trim();

        Integer seasonNumber = null;
        if (isTVShow.equalsIgnoreCase("yes")) {
            System.out.println("Do you want to delete a specific season? (season number/all): ");
            String seasonNumberString = scanner.nextLine().trim();
            if (!seasonNumberString.equalsIgnoreCase("all")) {
                seasonNumber = Integer.parseInt(seasonNumberString);
            } else {
                seasonNumber = -1;
            }
        }

        videoService.removeVideo(title, seasonNumber);
        System.out.println("Video removed successfully!");
    }

    private void addVideo() {
        System.out.println("""
                Enter video type:
                    1. Movie
                    2. TV Show""");
        String videoType = scanner.nextLine().trim();

        System.out.println("Enter title: ");
        String title = scanner.nextLine().trim();
        System.out.println("Enter genre: ");
        String genre = scanner.nextLine().trim();
        System.out.println("Enter release year: ");
        String releaseYear = scanner.nextLine().trim();
        int duration = -1, season = -1, episodes = -1, episodeDuration = -1;

        if (videoType.equals("1")) {
            System.out.println("Enter movie duration: ");
            duration = Integer.parseInt(scanner.nextLine().trim());
        } else {
            System.out.println("Enter season number: ");
            season = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Enter number of episodes per season: ");
            episodes = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Enter episode duration: ");
            episodeDuration = Integer.parseInt(scanner.nextLine().trim());
        }

        System.out.println("Enter number of available copies: ");
        int copies = Integer.parseInt(scanner.nextLine().trim());

        switch (videoType) {
            case "1" -> videoService.addMovie(title, genre, releaseYear, duration, copies);
            case "2" -> videoService.addTVShow(title, genre, releaseYear, season, episodes, episodeDuration, copies);
            default -> System.out.println("Invalid video type! Please try again.");
        }

        System.out.println("Video added successfully!");
    }

    private void handleExit() {
        if (!userIsLoggedIn) {
            System.exit(0);
        }

        userIsLoggedIn = false;
        loggedInAdmin = null;
        loggedInCustomer = null;
    }

    private void handleRegister() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        userService.createCustomer(username, email, password);
        System.out.println("Successfully registered!");
    }

    private void handleLogin() {
//        System.out.println("Enter username: ");
//        String username = scanner.nextLine().trim();
//        System.out.println("Enter password: ");
//        String password = scanner.nextLine().trim();

//        AbstractUser user = userService.getUser(username);
        AbstractUser user = userService.getUser("teo");
//        if (user != null && user.getPassword().equals(password)) {
            userIsLoggedIn = true;
//            if (username.equals("admin")) {
//                loggedInAdmin = (Admin) user;
//            } else {
                loggedInCustomer = (Customer) user;
//            }
//            System.out.println("Successfully logged in!");
//        } else {
//            System.out.println("Invalid username or password! Please try again.");
//        }
    }
}
