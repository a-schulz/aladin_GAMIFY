*English version below*
# Bachelorarbeit Alexander Schulz

[Link zur Bachelorarbeit](./docs/Bachelorarbeit_Exemplarische_Entwicklung_von_Gamification_Elementen_in_der_Lernplattform_ALADIN.pdf)

## Überblick

Um die Lernplattform ALADIN zu gamifizieren wurde die [SkillTree Platform](https://skilltreeplatform.dev/) verwendet.
Zusätzlich mussten weitere Services erstellt werden, die ein möglichst automatisiertes System ermöglichen.

### Notes
Im Repository zusätzlich befindet sich ein ["Debug-Frontend"](./aladin-gamify), dieses wurde während der Entwicklung verwendet um die Funktionen zu testen.

Außerdem befinden sich wichtige [Grafiken](./diagrams) im Repository, welche hauptsächlich mittels PlantUML generiert wurden.

## Services

### SkillTree Service

Auschließlich in der [docker-compose.yml](./docker-compose.yml) vorhanden. Der Service ist für die Verwaltung der
SkillTree Platform zuständig.

### SkillTree Authenticator

[Authenticator](./authenticator) ist ein Service, der für die Authentifizierung der Nutzer zuständig und wird für ein
separates Frontend benötigt (ALADIN CARPET).
Dieser Service kann [hier](./authenticator/src/main/java/skills/examples/utils/SkillsConfig.java) konfiguriert werden.

### SkillTree Dataimporter

[Dataimporter](./dataimporter) ist ein Service, der für das Importieren von Daten in die SkillTree Platform zuständig
ist.
Die Struktur der Projekte, Subjects, Skills und Levels kann [hier](./dataimporter/src/main/resources/projects.json)
definiert werden.
Ser Service selbst kann [hier](./dataimporter/src/main/java/skills/examples/utils/SkillsConfig.java) konfiguriert
werden.

## Tools

### Insomnia

Insomnia ist ein Programm, das es ermöglicht, HTTP-Requests zu erstellen und zu testen. Es ist möglich, die Requests in
Collections zu gruppieren und diese zu exportieren. Die Collections können dann in Insomnia importiert werden.
[Hier](./.insomnia) sind die Requests vorhanden, die während der Entwicklung verwendet wurden.

## Setup

Verwendung von .env Dateien. Die .env Dateien müssen im Root-Verzeichnis des Projekts liegen. Eine demo.env ist
vorhanden und kann als Vorlage verwendet werden.
Sobald diese vorhanden ist, kann mittels `docker compose up` das Projekt gestartet werden.

Sobald die Container laufen, kann auf den service unter `http://localhost:8080` zugegriffen werden.

---

# Bachelor thesis Alexander Schulz

[Link to the bachelor thesis](./docs/Bachelorarbeit_Exemplarische_Entwicklung_von_Gamification_Elementen_in_der_Lernplattform_ALADIN.pdf)

## Overview

To gamify the learning platform ALADIN the [SkillTree Platform](https://skilltreeplatform.dev/) was used. In addition, further services had to be created to enable a system that is as automated as possible.
## Notes

In the repository there is also a ["debug frontend"](./aladin-gamify), which was used during the development to test the functions.

Furthermore there are important [graphics](./diagrams) in the repository, which were mainly generated using PlantUML.
## Services

### SkillTree Service

Exclusively present in the [docker-compose.yml](./docker-compose.yml). The service is responsible for managing the SkillTree Platform.

###SkillTree Authenticator

[Authenticator](./authenticator) is a service responsible for authenticating users and is required for a separate frontend (ALADIN CARPET). This service can be configured [here](./authenticator/src/main/java/skills/examples/utils/SkillsConfig.java).

### SkillTree Dataimporter

[Dataimporter](./dataimporter) is a service responsible for importing data into the SkillTree Platform. The structure of projects, subjects, skills and levels can be defined [here](./dataimporter/src/main/resources/projects.json). The service itself can be configured [here](./dataimporter/src/main/java/skills/examples/utils/SkillsConfig.java).

##  Tools
### Insomnia

Insomnia is a program that allows to create and test HTTP requests. It is possible to group the requests into collections and export them. The collections can then be imported into Insomnia. [Here](./.insomnia) are the requests that were used during development.

## Setup

Use of .env files. The .env files must be located in the root directory of the project. A demo.env is available and can be used as a template. Once this is in place, `docker compose` up can be used to start the project.

Once the containers are running, the service can be accessed at `http://localhost:8080`.
