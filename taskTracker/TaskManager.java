package taskTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	static final String FILE_NAME = "tasks.json";
	
	public static void saveTasks(List<Task> tasks) {
	    try (FileWriter writer = new FileWriter(FILE_NAME)) {
	        writer.write("[\n");

	        for (int i = 0; i < tasks.size(); i++) {
	            writer.write("  " + tasks.get(i).toJSON());
	            if (i < tasks.size() - 1) {
	                writer.write(",");
	            }
	            writer.write("\n");
	        }

	        writer.write("]");
	        // No need to call writer.close() — it's handled by try-with-resources
	    } catch (IOException e) {
	        System.out.println("Error saving tasks: " + e.getMessage());
	    }
	}
	
    public static boolean fileExists() {
        return new File(FILE_NAME).exists();
    }
    
    public static List<Task> readTasks() {
        List<Task> tasks = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String content = jsonBuilder.toString().trim();

            // Check for valid array brackets
            if (!content.startsWith("[") || !content.endsWith("]")) {
                System.out.println("Invalid JSON format.");
                return tasks;
            }

            // Strip brackets and handle empty array
            content = content.substring(1, content.length() - 1).trim();
            if (content.isEmpty()) {
                return tasks; // empty list
            }

            // Split safely on '},' but preserve each full object
            List<String> taskStrings = new ArrayList<>();
            int depth = 0;
            int start = 0;

            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '{') depth++;
                else if (c == '}') depth--;

                if (depth == 0 && (i + 1 < content.length() && content.charAt(i + 1) == ',')) {
                    taskStrings.add(content.substring(start, i + 1).trim());
                    start = i + 2; // skip comma and space
                }
            }

            // Add the final object
            taskStrings.add(content.substring(start).trim());

            for (String taskJson : taskStrings) {
                Task task = Task.fromJSON(taskJson);
                if (task != null) {
                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
