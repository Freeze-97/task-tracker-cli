package taskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in a task list
 */
public class Task {
	public static enum Status {
		TODO,
		IN_PROGRESS,
		DONE
	}
	
	private int id;
	private String description;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Task(int id, String description, Status status, 
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	// Setters
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// Getters
	public int getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	
	// Create JSON style string
	 public String toJSON() {
	        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	        return String.format(
	            "{ \"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\" }",
	            id,
	            escape(description),
	            status.toString(),
	            createdAt.format(formatter),
	            updatedAt.format(formatter)
	        );
	 }

	 private String escape(String s) {
	      return s.replace("\"", "\\\"");
	 }
	 
	 public static Task fromJSON(String json) {
		    try {
		        json = json.replace("{", "").replace("}", "");
		        String[] parts = json.split(",");

		        int id = 0;
		        String description = "";
		        Task.Status status = Task.Status.TODO;
		        LocalDateTime createdAt = null;
		        LocalDateTime updatedAt = null;

		        for (String part : parts) {
		            String[] pair = part.split(":", 2);
		            if (pair.length < 2) continue;

		            String key = pair[0].trim().replace("\"", "");
		            String value = pair[1].trim().replace("\"", "");

		            switch (key) {
		                case "id":
		                    id = Integer.parseInt(value);
		                    break;
		                case "description":
		                    description = value;
		                    break;
		                case "status":
		                    status = Task.Status.valueOf(value);
		                    break;
		                case "createdAt":
		                    createdAt = LocalDateTime.parse(value);
		                    break;
		                case "updatedAt":
		                    updatedAt = LocalDateTime.parse(value);
		                    break;
		            }
		        }

		        return new Task(id, description, status, createdAt, updatedAt);
		    } catch (Exception e) {
		        System.out.println("Error parsing task: " + e.getMessage());
		        return null;
		    }
		}
}
