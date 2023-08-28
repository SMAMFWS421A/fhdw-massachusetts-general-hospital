# Deployment

## Docker
Wir verwenden Docker, um unsere Anwendung in einer isolierten Umgebung auszuführen. 

Docker hat viele Vorteile:
* Isolatioen: Die Anwendung ist unabhängig von der Hardware, dem Betriebssystem und anderen Anwendungen, auf der sie läuft
* Wartbarkeit: Die Anwendung kann einfach aktualisiert bzw neu gebaut werden
* Effizienz: Die Anwendung benötigt weniger Ressourcen als z.B. eine virtuelle Maschine
* Sicherheit: Die Anwendung ist isoliert und kann nicht auf das Host-System zugreifen
* Entwicklungseffizienz: Die Anwendung kann einfach auf jedem unserer Systeme ausgeführt werden, ohne dass viele Tools etc. installiert werden müssen

#### Unseere Anwendung
Um die Docker Images für unser Backend und Frontend zu bauen sind Dockerfiles in den jeweiligen Verzeichnissen vorhanden.

Zusätzlich gibt es eine Docker Compose Datei, welche die komplette Anwendung bestehend aus Frontend, 
Backend und Datenbank in Containern ausführen kann und die nötigen Einstellungen trifft.

#### Vorraussetzungen und Einsatz
Auf dem System muss [Docker](https://docs.docker.com/engine/install/) und [Docker Compose](https://docs.docker.com/compose/install/) installiert sein. 
Danach kann man die komplette Anwendung mit dem Befehl `docker-compose up` aus dem deployment Verzeichnis starten.