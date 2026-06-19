# JavaFX Essentials

> A hands-on JavaFX learning and reference project — practical samples of FXML views, controllers, UI controls, and JDBC data access, built around a small desktop application (FreeNAC).

![Java](https://img.shields.io/badge/Java-8-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-8-1F8AC0?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Connector%2FJ-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## Overview

This repository collects working JavaFX samples that demonstrate the essential
building blocks of a desktop Java application: the FXML-based MVC pattern,
controllers wired to the scene graph, a range of JavaFX UI controls, custom
table cells, scene navigation, and database-backed CRUD via JDBC.

The samples are organized around **FreeNAC**, a small JavaFX desktop app used as
the running example (a network-access management UI with login, user/profile
management, and a complaints/"reclamations" module). The point of the repo is
educational — to show how the pieces of a JavaFX app fit together — rather than
to ship a finished product. Expect example-quality code, a few half-finished
branches, and learning-in-progress patterns.

> Note: this is a learning project. It is not hardened for production — for
> example, several database queries are built by string concatenation, which is
> shown here as-is for reference rather than as recommended practice.

## What's Inside

Concepts and JavaFX features actually demonstrated in the code:

**Application & scene setup**
- `Application` lifecycle — `start(Stage)`, `launch(args)`, building a `Scene` and `Stage` (`sample/Main.java`)
- Loading views from FXML with `FXMLLoader` and `getClass().getResource(...)`
- Scene navigation — swapping scenes, opening new `Stage` windows, closing the current window

**FXML & MVC**
- FXML view files paired with controllers implementing `Initializable`
- `@FXML`-injected fields and event handlers (`onAction`, `onMouseClicked`, `onDragDetected`)
- Separation of concerns: `Gui/` (FXML) · `Controller/` · `Entity/` · `Service/` · `Helpers/`

**UI controls**
- `TextField`, `PasswordField`, `TextArea`, `Label`, `Button`, `ComboBox`, `Separator`, `Tooltip`, `Alert` (confirmation dialogs), `Image` / `ImageView`
- `TableView` with `TableColumn` and `PropertyValueFactory` for property binding
- Custom `TableCell` / `cellFactory` rendering: image thumbnails in cells, a `ProgressBarTableCell`, and per-row action buttons (edit / delete)
- Live table filtering and sorting with `FilteredList` + `SortedList` driven by a search field
- Drag-and-drop file handling onto an `ImageView` (`DragEvent`, `Dragboard`, `TransferMode`)
- `FileChooser` for picking and copying image files
- Toast-style notifications via ControlsFX (`org.controlsfx.control.Notifications`)
- `WebView` / `WebEngine` references for embedded web content

**Data access (JDBC)**
- Singleton MySQL `Connection` helper using `DriverManager` (`Helpers/database.java`)
- Service layer implementing generic CRUD interfaces (`intService/`) over entities
- `Statement` and `PreparedStatement` usage; `ResultSet` mapping to entity objects
- CRUD operations: add / read / update / delete, find-by-id, find-by-email

**Supporting patterns**
- POJO entities with getters/setters and `toString()` (`Entity/`)
- A simple singleton user-session holder (`Helpers/UserSession.java`)
- Sending email via the JavaMail API over SMTP (`Helpers/javaMail.java`)
- External CSS styling for the scene graph (`Css/`)

## Tech Stack

- **Language:** Java 8 (JDK 1.8 / Amazon Corretto 8)
- **UI framework:** JavaFX 8 (FXML, Scene Builder–style views, CSS)
- **Database:** MySQL via MySQL Connector/J 8.0.23 (JDBC)
- **UI libraries:** ControlsFX 8.40.11, JFoenix 8.0.10 (Material-style controls)
- **Email:** JavaMail (javax.mail) 1.6.2
- **IDE:** IntelliJ IDEA (project files included)

## Getting Started

> JavaFX 8 ships bundled with the Java 8 JDK, so no separate JavaFX SDK is
> required when using JDK 8.

**Prerequisites**
- JDK 8 (the project is configured for JDK 1.8 / Corretto 8)
- A running MySQL server
- The third-party JARs listed above (ControlsFX, JFoenix, MySQL Connector/J, JavaMail) on the classpath

**1. Clone**
```bash
git clone https://github.com/MuhamedHabib/JavaFX-Essentials.git
cd JavaFX-Essentials
```

**2. Database**
- Create a MySQL database (the helper connects to a schema named `freenac` on `localhost:3306`).
- Adjust the connection URL / credentials in `src/Helpers/database.java` to match your local MySQL setup.
- Create the tables the services reference (e.g. `responsable`, `reclamation`, `serialport`) based on the entity fields in `src/Entity/`.

**3. Open & run**
- Open the project in IntelliJ IDEA (an `.iml` module and `.idea/` config are included).
- Ensure the libraries above are attached to the module.
- Run `sample/Main.java` (the `Application` entry point).

> Configuration values such as the database URL/credentials and the SMTP
> sender used by the email helper are placeholders — replace them with your own
> before running. Do not commit real credentials.

## Notes

- **Learning repository.** Code is sample-grade and meant for study and
  reference. Some modules are partial or experimental (for example a serial-port
  service and a couple of `test/` controllers), and a few values are hardcoded
  for demonstration.
- **Original language.** Parts of the source (UI text, comments, identifiers)
  are in French, reflecting the project's origin; this README presents the
  concepts in English.
- **Build.** There is no Maven/Gradle build script — the project is wired
  through IntelliJ module/library settings, so dependencies are added in the
  IDE rather than via a dependency manager.
- **Security.** Treat any credentials found in the source as placeholders to be
  replaced; never store real secrets in the repository.

---
<p align="center">Built by <b>Mohamed Habib Khattat</b> — <a href="https://github.com/MuhamedHabib">GitHub (@MuhamedHabib)</a> · <a href="https://www.linkedin.com/in/mohamed-habib-khattat-2b206a173">LinkedIn</a></p>
