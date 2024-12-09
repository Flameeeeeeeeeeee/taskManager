package st10462078;

import javax.swing.JOptionPane;

public class Task {

    public static int TotalTasks = 0;
    private int taskNumb;
    private String taskName;
    private String taskDesc;
    private String devDetails;
    private double taskDuration;
    private String taskID;
    private String taskDetails;
    private String taskStatus;

    // Arrays to store data from all created tasks
    static String[] devDetailsArray = new String[100]; // Assuming a maximum of 100 tasks
    static String[] taskNameArray = new String[100];
    static String[] taskIDArray = new String[100];
    static double[] taskDurationArray = new double[100];
    static String[] taskStatusArray = new String[100];

    // Constructor
    public Task(String name, String desc, String details, double duration, String ID, String printDetails, String status) {
        TotalTasks++;
        this.taskNumb = TotalTasks;
        this.taskName = name;
        this.taskDesc = desc;
        this.devDetails = details;
        this.taskDuration = duration;
        this.taskID = ID;
        this.taskDetails = printDetails;
        this.taskStatus = status;

        // Store data in arrays
        devDetailsArray[TotalTasks - 1] = details;
        taskNameArray[TotalTasks - 1] = name;
        taskIDArray[TotalTasks - 1] = ID;
        taskDurationArray[TotalTasks - 1] = duration;
        taskStatusArray[TotalTasks - 1] = status;
    }

    // Option select method
    public static void menuOptions(boolean isLoggedIn) {
        boolean shouldRun = isLoggedIn;

        while (shouldRun) {
            JOptionPane.showMessageDialog(null, "Welcome to easykanban");
            String option = JOptionPane.showInputDialog(null,
                    "Please choose an option:\n"
                    + "1. Add Tasks\n"
                    + "2. View Tasks\n"
                    + "3. Quit"
            );

            switch (option) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    String subOption = JOptionPane.showInputDialog(null,
                            "Please choose a view option:\n"
                            + "1. Display tasks with status 'Done'\n"
                            + "2. Display task with longest duration\n"
                            + "3. Search for a task by name\n"
                            + "4. Search for tasks by developer\n"
                            + "5. Delete a task by name\n"
                            + "6. Display all tasks"
                    );

                    switch (subOption) {
                        case "1":
                            displayDoneTasks();
                            break;
                        case "2":
                            displayLongestTask();
                            break;
                        case "3":
                            searchTaskByName();
                            break;
                        case "4":
                            searchTasksByDeveloper();
                            break;
                        case "5":
                            deleteTaskByName();
                            break;
                        case "6":
                            displayAllTasks();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid view option selected");
                    }
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Quitting");
                    shouldRun = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected");
            }
        }
    }

    // Add tasks method
    public static void addTasks() {
        // Number of tasks to be created
        String taskNumberString = JOptionPane.showInputDialog("Enter the number of tasks you would like to create:");
        int taskNumber = Integer.parseInt(taskNumberString);

        double totalHrs = 0;

        for (int i = 0; i < taskNumber; i++) {
            // Display the task number before creation
            JOptionPane.showMessageDialog(null, "Creating Task " + (TotalTasks + 1));

            // Input taskName
            String taskName = JOptionPane.showInputDialog("Enter the name of the task:");

            // Task description
            String description;
            boolean isValidDescription;
            do {
                description = JOptionPane.showInputDialog("Please enter a task description (max 50 characters):");
                isValidDescription = checkTaskDescription(description);

                if (!isValidDescription) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                }
            } while (!isValidDescription);

            // Input devDetails
            String devDetails = JOptionPane.showInputDialog("Enter the details of developer assigned to the task:");

            // Duration
            String taskDurationString = JOptionPane.showInputDialog("Please assign an estimated duration for this task (hours):");
            double taskDuration = Double.parseDouble(taskDurationString);
            totalHrs += taskDuration;

            // TaskID
            String taskID = createTaskID(taskName, devDetails, i + 1);
            JOptionPane.showMessageDialog(null, "Task ID: " + taskID);

            // Task Status
            String option = JOptionPane.showInputDialog(null,
                    "Please assign a status to this task:\n"
                    + "1. To do\n"
                    + "2. Done\n"
                    + "3. Doing"
            );

            String taskStatus;
            switch (option) {
                case "1":
                    taskStatus = "To Do";
                    break;
                case "2":
                    taskStatus = "Done";
                    break;
                case "3":
                    taskStatus = "In Progress";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid task status.");
                    continue;
            }

            // Create Task object and store data in arrays
            Task newTask = new Task(taskName, description, devDetails, taskDuration, taskID, printTaskDetails(description, devDetails, taskID, i + 1, taskDuration, taskStatus, taskName), taskStatus);
            JOptionPane.showMessageDialog(null, newTask.getTaskDetails());

        }
        JOptionPane.showMessageDialog(null, "Total hours: " + totalHrs);
    }

    // Method to check task description length
    public static boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    // Method to create the taskID
    public static String createTaskID(String taskName, String devDetails, int taskNumber) {
        String taskPrefix = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
        String devSuffix = devDetails.substring(Math.max(devDetails.length() - 3, 0)).toUpperCase();
        return taskPrefix + ":" + taskNumber + ":" + devSuffix;
    }

    // Method to print task details
    public static String printTaskDetails(String description, String devDetails, String taskID, int taskNumber, double taskDuration, String taskStatus, String taskName) {
        return "Status: " + taskStatus + "\nDeveloper Details: " + devDetails + "\nTask Number: " + taskNumber + "\nTask name: " + taskName + "\nDescription: " + description + "\nTask ID: " + taskID + "\nTask Duration: " + taskDuration + " hours";
    }

    // Method to get task details
    public String getTaskDetails() {
        return taskDetails;
    }

    // Method to display tasks with status 'Done'
    public static void displayDoneTasks() {
        StringBuilder result = new StringBuilder("Tasks with status 'Done':\n");
        boolean found = false;

        for (int i = 0; i < TotalTasks; i++) {
            if ("Done".equals(taskStatusArray[i])) {
                result.append("Developer: ").append(devDetailsArray[i])
                        .append(", Task Name: ").append(taskNameArray[i])
                        .append(", Task Duration: ").append(taskDurationArray[i])
                        .append(" hours\n");
                found = true;
            }
        }

        if (!found) {
            result.append("No tasks with status 'Done'.");
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to display the task with the longest duration
    public static void displayLongestTask() {
        if (TotalTasks == 0) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < TotalTasks; i++) {
            if (taskDurationArray[i] > taskDurationArray[longestIndex]) {
                longestIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, "Developer: " + devDetailsArray[longestIndex]
                + ", Task Duration: " + taskDurationArray[longestIndex] + " hours");
    }

    // Method to search for a task by name
    public static void searchTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        boolean found = false;

        for (int i = 0; i < TotalTasks; i++) {
            if (taskNameArray[i].equals(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Name: " + taskNameArray[i]
                        + ", Developer: " + devDetailsArray[i]
                        + ", Task Status: " + taskStatusArray[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    // Method to search for all tasks assigned to a developer
    public static void searchTasksByDeveloper() {
        String developer = JOptionPane.showInputDialog("Enter the developer name to search tasks:");
        StringBuilder result = new StringBuilder("Tasks assigned to " + developer + ":\n");
        boolean found = false;

        for (int i = 0; i < TotalTasks; i++) {
            if (devDetailsArray[i].equals(developer)) {
                result.append("Task Name: ").append(taskNameArray[i])
                        .append(", Task Status: ").append(taskStatusArray[i])
                        .append("\n");
                found = true;
            }
        }

        if (!found) {
            result.append("No tasks assigned to this developer.");
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to delete a task by name
    public static void deleteTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        boolean found = false;

        for (int i = 0; i < TotalTasks; i++) {
            if (taskNameArray[i].equals(taskName)) {
                // Shift elements to remove the task
                for (int j = i; j < TotalTasks - 1; j++) {
                    taskNameArray[j] = taskNameArray[j + 1];
                    devDetailsArray[j] = devDetailsArray[j + 1];
                    taskIDArray[j] = taskIDArray[j + 1];
                    taskDurationArray[j] = taskDurationArray[j + 1];
                    taskStatusArray[j] = taskStatusArray[j + 1];
                }
                TotalTasks--;
                found = true;
                JOptionPane.showMessageDialog(null, "Task deleted.");
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    // Method to display a report of all captured tasks
    public static void displayAllTasks() {
        StringBuilder report = new StringBuilder("All Captured Tasks:\n");

        for (int i = 0; i < TotalTasks; i++) {
            report.append("Task Name: ").append(taskNameArray[i])
                    .append(", Developer: ").append(devDetailsArray[i])
                    .append(", Task ID: ").append(taskIDArray[i])
                    .append(", Duration: ").append(taskDurationArray[i])
                    .append(" hours, Status: ").append(taskStatusArray[i])
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Main method
    public static void main(String[] args) {
        // Example usage:
        menuOptions(true);
    }
}
