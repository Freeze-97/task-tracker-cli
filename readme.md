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
javac Task.java TaskManager.java Main.java
```

## ▶️ How to Use

Run the app with:

```bash
java Main [command] [arguments]
```

## ✅ Command Examples

➕ Add a new task

```bash
java Main add "Buy groceries"
```

### 🛠 Update a task's description

```bash
java Main update 1 "Buy groceries and cook dinner"
```

### 🗑 Delete a task

```bash
java Main delete 1
```

### 🔁 Change task status

```bash
java Main mark-in-progress 1
java Main mark-done 1
```

### 📋 List tasks

```bash
java Main list
java Main list todo
java Main list in-progress
java Main list done
```

### ❓ Show help

```bash
java Main help
```

## 📁 Where are tasks stored?

All tasks are saved in a tasks.json file in the current directory.

## 📌 Notes

- Descriptions must be wrapped in "quotes" if they contain spaces.
- You must compile before running.
- This project uses no external libraries — only built-in Java.
- ⚠️ This project may not be fully complete or perfect.
