package taskTracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
	static List<Task> allTasks = new ArrayList<>();
	
	public static void main(String[] args) {
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
		    System.out.println("Usage:");
		    System.out.println("  add \"description\"");
		    System.out.println("  update <id> \"new description\"");
		    System.out.println("  delete <id>");
		    System.out.println("  mark-in-progress <id>");
		    System.out.println("  mark-done <id>");
		    System.out.println("  list [status]");
		    System.exit(0);
		}
		
		allTasks = TaskManager.readTasks(); // Read all tasks from JSON file to List
		
		if(args[0].equals("add") && args.length > 1) {
			addTask(args[1]);
		}
		else if (args[0].equals("update") && args.length > 1) {
		    try {
		        int id = Integer.parseInt(args[1]);
		        updateTask(id, args[2]);
		    } 
		    catch (NumberFormatException e) {
		        System.out.println("Invalid ID: must be an integer.");
		    }
		}
		else if (args[0].equals("delete") && args.length > 1) {
		    try {
		        int id = Integer.parseInt(args[1]);
		        deleteTask(id);
		    } 
		    catch (NumberFormatException e) {
		        System.out.println("Invalid ID: must be an integer.");
		    }
		}
		else if(args[0].equals("mark-in-progress") && args.length > 1) {
			try {
				int id = Integer.parseInt(args[1]);
				markStatus(id, Task.Status.IN_PROGRESS);
			}
			catch (NumberFormatException e) {
		        System.out.println("Invalid ID: must be an integer.");
		    }
		}
		else if(args[0].equals("mark-done") && args.length > 1) {
			try {
				int id = Integer.parseInt(args[1]);
				markStatus(id, Task.Status.DONE);
			}
			catch (NumberFormatException e) {
		        System.out.println("Invalid ID: must be an integer.");
		    }
		}
		else if(args[0].equals("list") && args.length == 1) {
			listTasks("none");
		}
		else if(args[0].equals("list") && args[1].equals("done")) {
			listTasks("done");
		}
		else if(args[0].equals("list") && args[1].equals("todo")) {
			listTasks("todo");
		}
		else if(args[0].equals("list") && args[1].equals("in-progress")) {
			listTasks("in-progress");
		}
	}
	
	public static void addTask(String description) {
		// Create new task
		int id = 1;
		for (Task t : allTasks) {
		    if (t.getId() >= id) {
		        id = t.getId() + 1;
		    }
		}
		Task.Status status = Task.Status.TODO;
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = LocalDateTime.now();
		
		Task newTask = new Task(id, description, status, createdAt, updatedAt);
		allTasks.add(newTask);
		TaskManager.saveTasks(allTasks); // Save all tasks to file
		System.out.println("Task added successfully (ID: " + id + ")");
	}
	
	public static void updateTask(int id, String newDescription) {
		// Look up task by id
		for(Task task : allTasks) {
			if(task.getId() == id) {
				task.setDescription(newDescription);
				LocalDateTime updatedAt = LocalDateTime.now();
				task.setUpdatedAt(updatedAt);
				TaskManager.saveTasks(allTasks);
				System.out.println("Task has been updated (ID: " + id + ")");
				return;
			}
		}
		System.out.println("Could not find task with id " + id);
	}
	
	public static void deleteTask(int id) {
		Iterator<Task> iterator = allTasks.iterator();
		while(iterator.hasNext()) {
			Task task = iterator.next();
			if(task.getId() == id) {
				iterator.remove();
				TaskManager.saveTasks(allTasks);
				System.out.println("Task has been deleted (ID: " + id + ")");
				return;
			}
		}
		System.out.println("Could not find task with id " + id);
	}
	
	public static void markStatus(int id, Task.Status status) {
		for(Task task : allTasks) {
			if(task.getId() == id) {
				task.setStatus(status);
				TaskManager.saveTasks(allTasks);
				System.out.println("Task status has been changed (ID: " + id + ")");
				return;
			}
		}
		System.out.println("Could not find task with id " + id);
	}
	
	public static void listTasks(String filter) {
		if(filter.equals("none")) {
			for(Task task : allTasks) {
				System.out.println("Task id: " + task.getId());
				System.out.println("Description: " + task.getDescription());
				System.out.println("Status: " + task.getStatus().toString());
				System.out.println("Created at: " + task.getCreatedAt().toString());
				System.out.println("Updated at: " + task.getUpdatedAt().toString());
				System.out.println();
			}
		}
		// Filter being used
		Task.Status chosenStatus = null;
		if(filter.equals("done")) {
			chosenStatus = Task.Status.DONE;
		}
		else if(filter.equals("todo")) {
			chosenStatus = Task.Status.TODO;
		}
		else if(filter.equals("in-progress")) {
			chosenStatus = Task.Status.IN_PROGRESS;
		}
		
		for(Task task : allTasks) {
			if(task.getStatus() == chosenStatus) {
				System.out.println("Task id: " + task.getId());
				System.out.println("Description: " + task.getDescription());
				System.out.println("Status: " + task.getStatus().toString());
				System.out.println("Created at: " + task.getCreatedAt().toString());
				System.out.println("Updated at: " + task.getUpdatedAt().toString());
				System.out.println();
			}
		}
	}
}
