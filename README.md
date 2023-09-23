# Bachelorarbeit Alexander Schulz

[Link zur Bachelorarbeit](https://htwonline-my.sharepoint.com/:w:/g/personal/s82105_htwonline_onmicrosoft_com/EdRJPq4B1ZJIufXnwarxFU4B-RtDTSeKG54-U039xEIhlA)

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
