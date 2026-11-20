# Women Safety Web Application

A Java JSP/Servlet web application built as a NetBeans Ant web project.

## Project contents
- `src/java/` — Java source code (servlets, DAO, models, database utility)
- `web/` — JSP pages, assets, and `WEB-INF` configuration
- `nbproject/` and `build.xml` — NetBeans/Ant project configuration

## Requirements
- JDK compatible with the `javac.source` and `javac.target` settings in `nbproject/project.properties`
- Apache Tomcat configured in NetBeans
- MySQL or XAMPP MySQL
- Database named `womensafety`

## Database configuration
Update `src/java/com/app/womensafety/config/DBUtil.java` with your local MySQL URL, username, and password before running. The current file uses `root` with an empty password, which is only suitable for a default local XAMPP setup.

The application expects the `users`, `contacts`, and `sos_logs` tables. Create/configure these tables in phpMyAdmin before testing registration, contacts, and SOS features.

## Open in NetBeans
1. Extract this repository folder.
2. In NetBeans choose **File → Open Project** and select the `womensafety` folder (the folder containing `build.xml`).
3. Verify the Tomcat server and MySQL Connector/J library are available. The connector JAR is included under `web/WEB-INF/lib/`.
4. Update the database credentials, then Clean and Build and Run.

## GitHub
This archive is prepared as a source project folder. Extract it, open PowerShell in this folder, then run `git init`, `git add .`, `git commit`, add your existing GitHub repository as `origin`, and push the `main` branch.

Do not commit real passwords, API keys, or private user data.
