package SearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Search {
    public static Scanner scanner =  new Scanner(System.in);
    public static SearchEngine s = new SearchEngine();
    public static void main(String[] args) {
        File file = new File(args[1]);
        addPeople(file);
        int action;
        do {
            printMenu();
            action = Integer.parseInt(scanner.nextLine());
            if (action >= 0 && action <= 2) {
                switch (action) {
                    case 1:
                        System.out.println("Select a matching strategy: ALL, ANY, NONE");
                        String strategy = scanner.nextLine().toUpperCase();
                        switch (strategy){
                            case "ANY":
                                System.out.println("Enter a name or email to search all suitable people.");
                                String dataAny = scanner.nextLine();
                                if (!dataAny.equals("@")) {
                                    if (searchAnyPeople(dataAny).size() != 0) {
                                        searchAnyPeople(dataAny).forEach(System.out::println);
                                } else {
                                    System.out.println("No matching people found.");
                                }
                            } else {
                                System.out.println("No matching people found.");
                            }
                            break;
                            case "ALL":
                                System.out.println("Enter a name or email to search all suitable people.");
                                String dataAll = scanner.nextLine();
                                if (!dataAll.equals("@")) {
                                    if (searchAllPeople(dataAll).size() != 0) {
                                        searchAllPeople(dataAll).forEach(System.out::println);
                                    } else {
                                        System.out.println("No matching people found.");
                                    }
                                } else {
                                    System.out.println("No matching people found.");
                                }
                                break;
                            case "NONE":
                                System.out.println("Enter a name or email to search all suitable people.");
                                String dataNone = scanner.nextLine();
                                if (!dataNone.equals("@")) {
                                    if (searchNonePeople(dataNone).size() != 0) {
                                        searchNonePeople(dataNone).forEach(System.out::println);
                                    } else {
                                        System.out.println("No matching people found.");
                                    }
                                } else {
                                    System.out.println("No matching people found.");
                                }
                                break;
                        }
                        break;
                    case 2:
                        printPeople();
                        break;

                }
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        } while (action != 0);

        System.out.println("Bye!");
    }
    public static void addPeople(File file) {
        int i = 1;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                s.addToList(i, reader.nextLine());
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    public static List<String> searchAnyPeople(String data) {
        List<String> results = new ArrayList<>();
        for (Map.Entry<Integer, String> entry :  s.returnData().entrySet()) {
            String[] split = data.split("\\s+");
            int i = split.length - 1;
            while (i >= 0) {
                if (entry.getValue().toLowerCase().contains(split[i].toLowerCase())) {
                    results.add(entry.getValue());
                    break;
                } else {
                    i--;
                }
            }
        }
        return results;
    }
    public static List<String> searchAllPeople(String data) {
        List<String> results = new ArrayList<>();
        for (Map.Entry<Integer, String> entry :  s.returnData().entrySet()) {
                if (entry.getValue().equalsIgnoreCase(data)) {
                    results.add(entry.getValue());
                    break;
                }
            }
        return results;
    }
    public static List<String> searchNonePeople(String data) {
        List<String> results = new ArrayList<>();
        for (Map.Entry<Integer, String> entry :  s.returnData().entrySet()) {
            String[] split = data.split("\\s+");
            int i = split.length - 1;
            boolean bl = true;
            while (i >= 0) {
                if (!entry.getValue().toLowerCase().contains(split[i].toLowerCase())) {
                    i--;
                } else {
                    bl = false;
                    break;
                }
            }
            if (bl) {
                results.add(entry.getValue());
            }
        }
        return results;
    }
    public static void printPeople() {
        System.out.println("=== List of people ===");
        for (Map.Entry<Integer, String> entry :  s.returnData().entrySet()) {
            System.out.println(entry.getValue());
        }
}
    public static void printMenu() {
        System.out.println("=== Menu ===\n" + "1. Find a person\n" + "2. Print all people\n" + "0. Exit");
    }
}