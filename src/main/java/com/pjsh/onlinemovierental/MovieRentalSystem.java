package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.entities.users.AbstractUser;
import com.pjsh.onlinemovierental.entities.users.Admin;
import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.repositories.UserRepository;
import com.pjsh.onlinemovierental.services.UserService;
import com.pjsh.onlinemovierental.services.RentalService;
import com.pjsh.onlinemovierental.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//                        case "2" -> rentalService.removeMovie();
//                        case "3" -> rentalService.viewAllMovies();
//                        case "4" -> userService.viewAllCustomers();
//                        case "5" -> rentalService.viewAllRentals();
                        case "6" -> handleExit();
                        default -> System.out.println("Invalid command! Please try again.");
                    }
                }
            }
        }
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
        System.out.println("Enter release date: ");
        String releaseDate = scanner.nextLine().trim();

        if (videoType.equals("1")) {
            System.out.println("Enter movie duration: ");
            String duration = scanner.nextLine().trim();
        } else {
            System.out.println("Enter number of seasons: ");
            String seasons = scanner.nextLine().trim();
            System.out.println("Enter number of episodes per season: ");
            String episodes = scanner.nextLine().trim();
            System.out.println("Enter episode duration: ");
            String episodeDuration = scanner.nextLine().trim();
        }
        System.out.println("Enter number of available copies: ");
        String copies = scanner.nextLine().trim();

        switch (videoType) {
            case "1":
                System.out.println("Enter movie duration: ");
                String copies = scanner.nextLine().trim();

                videoService.addMovie();
                break;
            case "2" -> videoService.addTVShow();
            default -> System.out.println("Invalid video type! Please try again.");
        }
        rentalService.addMovie();
        System.out.println("Movie added successfully!");
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
        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        AbstractUser user = userService.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            userIsLoggedIn = true;
            if (username.equals("admin")) {
                loggedInAdmin = (Admin) user;
            } else {
                loggedInCustomer = (Customer) user;
            }
            System.out.println("Successfully logged in!");
        } else {
            System.out.println("Invalid username or password! Please try again.");
        }
    }
}
