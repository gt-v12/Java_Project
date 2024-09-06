package Application;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public void displayUsers() {
        System.out.println("\nList of Users:");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName());
        }
    }
}

class Application {
    private UserManager userManager;

    public Application() {
        this.userManager = new UserManager();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n== Application Menu:==\n");
            System.out.println("1. Start Application");
            System.out.println("2. Register User");
            System.out.println("3. Modify User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    startApplication();
                    break;
                case 2:
                    registerUser(scanner);
                    break;
                case 3:
                    modifyUser(scanner);
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 5:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void startApplication() {
        System.out.println("\nApplication started successfully!");
        userManager.displayUsers();
    }

    private void registerUser(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        User existingUser = userManager.findUserById(id);
        if (existingUser != null) {
            System.out.println("User ID already exists. Please try again.");
            return;
        }

        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        User newUser = new User(id, name);
        userManager.addUser(newUser);

        System.out.println("\nUser registered successfully!");
        userManager.displayUsers();
    }

    private void modifyUser(Scanner scanner) {
        System.out.print("Enter user ID to modify: ");
        String id = scanner.nextLine();

        User userToModify = userManager.findUserById(id);
        if (userToModify == null) {
            System.out.println("User not found. Please try again.");
            return;
        }

        System.out.print("Enter new name for user " + id + ": ");
        String newName = scanner.nextLine();

        userToModify.setName(newName);

        System.out.println("\nUser modified successfully!");
        userManager.displayUsers();
    }

    private void deleteUser(Scanner scanner) {
        System.out.print("Enter user ID to delete: ");
        String id = scanner.nextLine();

        User userToDelete = userManager.findUserById(id);
        if (userToDelete == null) {
            System.out.println("User not found. Please try again.");
            return;
        }

        userManager.removeUser(id);

        System.out.println("\nUser deleted successfully!");
        userManager.displayUsers();
    }
}

    class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
    

