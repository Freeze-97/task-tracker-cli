# 📝 Task Tracker CLI

A simple command-line task manager written in Java.  
It supports adding, updating, deleting, listing, and marking tasks with different statuses.

---

## 🔧 Requirements

- Java 11 or higher installed  
  You can check with:

```bash
java -version
```

## ⚙️ How to Compile

Open a terminal and navigate to the folder containing your Java files. Then compile them:

```bash
javac taskTracker/*.java
java taskTracker.Main
```

## ▶️ How to Use

Run the app with:

```bash
java taskTracker.Main [command] [arguments]
```

## ✅ Command Examples

➕ Add a new task

```bash
java taskTracker.Main add "Buy groceries"
```

### 🛠 Update a task's description

```bash
java taskTracker.Main update 1 "Buy groceries and cook dinner"
```

### 🗑 Delete a task

```bash
java taskTracker.Main delete 1
```

### 🔁 Change task status

```bash
java taskTracker.Main mark-in-progress 1
java taskTracker.Main mark-done 1
```

### 📋 List tasks

```bash
java taskTracker.Main list
java taskTracker.Main list todo
java taskTracker.Main list in-progress
java taskTracker.Main list done
```

### ❓ Show help

```bash
java taskTracker.Main help
```

## 📁 Where are tasks stored?

All tasks are saved in a tasks.json file in the current directory.

## 📌 Notes

- Descriptions must be wrapped in "quotes" if they contain spaces.
- You must compile before running.
- This project uses no external libraries — only built-in Java.
- ⚠️ This project may not be fully complete or perfect.
