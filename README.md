# Campus Course & Records Manager (CCRM)

## Project Overview

A console-based Java application to manage students, courses, and records.

## How to Run

- JDK version: 8
- Commands:
  - `javac -d out src/edu/ccrm/cli/Main.java`
  - `java -cp out edu.ccrm.cli.Main`

## Evolution of Java

| Version | Release Date | Key Features |
|---|---|---|
| JDK 1.0 | 1996-01-23 | Initial release |
| JDK 1.1 | 1997-02-19 | Inner classes, JDBC, RMI, Reflection |
| J2SE 1.2 | 1998-12-08 | Swing, Collections Framework, JIT Compiler |
| J2SE 1.3 | 2000-05-08 | HotSpot JVM, JNDI |
| J2SE 1.4 | 2002-02-06 | Regular expressions, non-blocking I/O, assertions |
| Java SE 5 | 2004-09-30 | Generics, annotations, autoboxing, enums, varargs |
| Java SE 6 | 2006-12-11 | Performance improvements, scripting support |
| Java SE 7 | 2011-07-28 | try-with-resources, diamond operator, switch on strings |
| Java SE 8 | 2014-03-18 | Lambda expressions, Stream API, new Date-Time API |
| Java SE 9 | 2017-09-21 | Module system (Project Jigsaw), JShell |
| Java SE 10 | 2018-03-20 | Local variable type inference (var) |
| Java SE 11 | 2018-09-25 | Standardized HTTP client, ZGC |
| Java SE 17 | 2021-09-14 | Sealed classes, pattern matching for switch |
| Java SE 21 | 2023-09-19 | Virtual threads, record patterns |

## Java ME vs SE vs EE

| Edition | Full Name | Purpose | Use Cases |
|---|---|---|---|
| Java SE | Standard Edition | Core Java platform for general-purpose applications | Desktop, server, and console applications |
| Java EE | Enterprise Edition | For large-scale, distributed, and web applications | Web services, enterprise applications, and backend systems |
| Java ME | Micro Edition | For resource-constrained and embedded devices | Mobile phones, set-top boxes, and IoT devices |

## JDK/JRE/JVM Explanation

| Component | Full Name | Description |
|---|---|---|
| JVM | Java Virtual Machine | An abstract machine that executes Java bytecode. It provides a platform-independent way of executing code. |
| JRE | Java Runtime Environment | The part of the JDK that provides the minimum requirements for executing a Java application. It includes the JVM, core classes, and supporting files. |
| JDK | Java Development Kit | A software development kit that provides the tools and libraries necessary to develop, compile, and run Java applications. It includes the JRE and development tools like the compiler and debugger. |

## Windows Install Steps

1.  **Download the JDK 8 Installer:**
    *   Go to the [Oracle Java SE 8 Archive Downloads page](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).
    *   Accept the license agreement and download the Windows x64 installer (`.exe` file).
2.  **Run the Installer:**
    *   Double-click the downloaded `.exe` file to start the installation.
    *   Follow the on-screen prompts and accept the default settings.
3.  **Set Environment Variables:**
    *   Open **System Properties** -> **Advanced system settings** -> **Environment Variables**.
    *   Under **System variables**, click **New...**.
    *   Set **Variable name** to `JAVA_HOME` and **Variable value** to the JDK installation path (e.g., `C:\Program Files\Java\jdk1.8.0_XXX`).
    *   Edit the **Path** variable and add `%JAVA_HOME%\bin`.
4.  **Verify Installation:**
    *   Open a new Command Prompt and run `java -version` and `javac -version`.
    *   You should see the installed Java version.

## Eclipse Setup Steps

1.  **Install Eclipse:**
    *   Download and install the "Eclipse IDE for Java Developers" from the [Eclipse website](https://www.eclipse.org/downloads/).
2.  **Create a New Java Project:**
    *   Go to **File** -> **New** -> **Java Project**.
    *   Enter a project name (e.g., `CCRM`).
    *   Select "Use an execution environment JRE" and choose `JavaSE-1.8`.
3.  **Import Existing Source Code:**
    *   Right-click on the `src` folder in the Project Explorer.
    *   Select **Import...** -> **General** -> **File System**.
    *   Browse to the `src` directory of the CCRM project and select the `edu` folder.
4.  **Run the Application:**
    *   Right-click on `Main.java` in the Package Explorer.
    *   Select **Run As** -> **Java Application**.

## Syllabus Topic Mapping

| Syllabus Topic | File/Class/Method |
|---|---|
| Object-Oriented Programming | `Person.java`, `Student.java`, `Course.java`, `Instructor.java` |
| Encapsulation | Private fields and public getters/setters in domain classes |
| Inheritance | `Student` and `Instructor` classes extend `Person` |
| Polymorphism | N/A in this project |
| Abstraction | `Person` class is an abstract representation of a person |
| Interfaces | `StudentService.java`, `CourseService.java`, etc. and their `...Impl` classes |
| Collections Framework | `List`, `ArrayList` used in `Student.java`, `CliMenu.java`, etc. |
| Exception Handling | `try-catch` block in `BackupServiceImpl.java` |
| File I/O | `ImportExportServiceImpl.java`, `BackupServiceImpl.java` |
| Enums | `Semester.java`, `Grade.java`, `StudentStatus.java` |
| Switch Statement | `CliMenu.java` for menu navigation |
| Singleton Pattern | `AppConfig.java` |

## Notes

- To enable assertions, use the `-ea` flag when running the application: `java -ea -cp out edu.ccrm.cli.Main` at source code directory.
