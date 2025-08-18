package cz.alexbotkova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * UI for creating and operating integer arrays.
 */
public class WelcomeScreen {
    /** To read user input from standard input.*/
    private final BufferedReader reader;
    /** Whether the main loop should terminate.*/
    private boolean exit;
    /**Whether another array is to be created at the next loop iteration.*/
    private boolean addArray;
    /** Message printed when the user enters an invalid choice.*/
    private static final String INVALID_CHOICE_WARNING_MSG = "Invalid choice.";
    /**Manages all created arrays and applies operation to the selected array.*/
    private final PFArrayManager manager;
    /**All operation options the user can choose to apply.*/
    private static final String[] MENU_OPTIONS = new String[]{
            "print the array",
            "append a number to the array",
            "find the biggest number in the array",
            "find the smallest number in the array",
            "remove a number from the array (first occurrence)",
            "remove a number from the array (all occurrences)",
            "sum the items of the array",
            "delete the array",
            "add an array",
            "regenerate the array",
            "change the selected array",
            "get name of the array with the biggest sum of items",
            "get name of the array with the smallest sum of items",
            "exit"
    };

    public WelcomeScreen() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.manager = new PFArrayManager();
        this.exit = false;
        this.addArray = false;
    }

    /**
     * Reads a line from the standard input.
     * @return read line
     */
    private String readLine() {
        try {
            String line = reader.readLine();
            if (line == null) {
                return "";
            } else {
                return line.trim() ;
            }
        } catch (IOException _) {
            throw new InvalidInputException("Failed to read input.");
        }
    }

    /**
     * Parses a read line as an Integer
     * @return int
     */
    private int readInt() {
        String input = readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException _) {
            System.out.println("Not an integer. Enter an integer.");
            return readInt();
        }
    }

    /**
     * Handles creation of the array based on the user's choice.
     * @param arrayName name of the array to create/regenerate
     * @param arrayCreationChoice user's choice: '1' for random array creation, '2' for manual
     * @param regenerate whether to regenerate an existing array instead of creating a new one
     */
    private void createArray(String arrayName, int arrayCreationChoice, boolean regenerate) {
        PFArray array;
        if (regenerate) {
            array = manager.arrays.get(arrayName);
        }
        else {
            array = new PFArray(arrayName);
        }
        switch (arrayCreationChoice) {
            case 1:
                array.createRandomly();
                if (!regenerate) {
                    manager.add(array);
                }
                return;
            case 2:
                System.out.println("Enter comma-separated integers.");
                String csv = readLine();
                if (csv.trim().isEmpty()) {
                    array.createEmpty();
                } else {
                    csv = csv.trim();
                    String[] parts = csv.split("\\s*,\\s*");
                    array.createManually(parts);
                    if (!regenerate) {
                        manager.add(array);
                    }
                }
                return;
            default:
                System.out.println(INVALID_CHOICE_WARNING_MSG);
        }
    }

    /**
     * Applies an operation to the selected array or to arrays managed by the PFArrayManager from the MENU_OPTIONS.
     * @param arrayOperationChoice user's choice which operation to apply
     */
    private void applyArrayOperations(int arrayOperationChoice) {
        PFArray array = manager.selected;
        switch (arrayOperationChoice) {
            case 1:
                array.printArray();
                break;
            case 2:
                System.out.println("Enter a number to append.");
                int numberToAppend = readInt();
                array.appendNumber(numberToAppend);
                break;
            case 3:
                System.out.println(array.getMax());
                break;
            case 4:
                System.out.println(array.getMin());
                break;
            case 5:
                System.out.println("Enter a number to remove.");
                int numberToRemove = readInt();
                array.removeNumber(numberToRemove, false);
                break;
            case 6:
                System.out.println("Enter a number to remove.");
                int numberToRemoveAll = readInt();
                array.removeNumber(numberToRemoveAll, true);
                break;
            case 7:
                System.out.println(manager.selected.sumItems());
                break;
            case 8:
                array.deleteArray();
                break;
            case 9:
                addArray = true;
                break;
            case 10:
                int arrayCreationChoice = getArrayCreationChoice();
                createArray(manager.selected.getName(), arrayCreationChoice, true);
                break;
            case 11:
                manager.selected = null;
                break;
            case 12:
                System.out.println(manager.findBiggestSumArray());
                break;
            case 13:
                System.out.println(manager.findSmallestSumArray());
                break;
            case 14:
                exit = true;
                break;
            default:
                System.out.println(INVALID_CHOICE_WARNING_MSG);
                break;
        }
    }

    /**
     * Prompts the user to choose how to create an array.
     * @return '1' for creating an array randomly, '2' for manual creation
     */
    private int getArrayCreationChoice() {
        System.out.println("Type '1' to create a random array. Type '2' to create an array manually.");
        int arrayCreationChoice = readInt();
        if (arrayCreationChoice == 1 || arrayCreationChoice == 2) {
            return arrayCreationChoice;
        } else {
            System.out.println(INVALID_CHOICE_WARNING_MSG);
            return getArrayCreationChoice();
        }
    }

    /**
     * Prompts the user for a valid array name.
     * @return a valid array name
     */
    private String getArrayName() {
        System.out.println("Enter the name of the array you would like to create.");
        String arrayName = readLine();
        if (manager.isNameValid(arrayName)) {
            return arrayName;
        } else {
            return getArrayName();
        }
    }

    /**
     * Allows the user to select the current working array either by typing its name or by listing available arrays.
     */
    private void selectWorkingArray() {
        System.out.println("Select the working array. Press '1' to type in the name of the array. Press '2' to list the names of the saved arrays.");
        int typeOrListChoice = readInt();
        switch (typeOrListChoice) {
            case 1:
                System.out.println("Type in the name of the array.");
                String arrayName = readLine();
                if (manager.arrayExists(arrayName)) {
                    manager.selected = manager.arrays.get(arrayName);
                } else {
                    System.out.println(INVALID_CHOICE_WARNING_MSG);
                }
                break;
            case 2:
                manager.listPFArrayNames();
                break;
            default:
                System.out.println(INVALID_CHOICE_WARNING_MSG);
                break;
        }
    }

    /**
     * Prints menu options with their associated number.
     */
    private void printMenuOptions() {
        System.out.println();
        int optionNumber = 1;
        for (String option : MENU_OPTIONS) {
            System.out.printf("To %s, press '%d'.%n",  option, optionNumber++);
        }
    }

    /**
     *  Runs the interactive menu until the user chooses to exit.
     */
    public void run() {
        while (!exit) {
            if (manager.arrays.isEmpty() || addArray) {
            addArray = false;
            int arrayCreationChoice = getArrayCreationChoice();
            String arrayName = getArrayName();
            createArray(arrayName, arrayCreationChoice, false);
            } else if (manager.selected == null) {
                selectWorkingArray();
            } else {
                printMenuOptions();
                int arrayOperationChoice = readInt();
                applyArrayOperations(arrayOperationChoice);
            }
        }
        exit = false;
    }
}