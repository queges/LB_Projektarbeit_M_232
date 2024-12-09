# LB_Projektarbeit_M_232

## Inhaltsverzeichnis
- [Projektbeschreibung](#projektbeschreibung)
- [Mathematische Funktionen](#mathematische-funktionen)
- [Benutzeroberfläche](#benutzeroberfläche)
- [Einsatz von Java Streams](#einsatz-von-java-streams)
- [Parallelisierungsmöglichkeiten](#parallelisierungsmöglichkeiten)
- [Setup und Installation](#setup-und-installation)

---

## Projektbeschreibung

Der funktionale Taschenrechner bietet grundlegende mathematische Funktionen wie Addition, Subtraktion, Multiplikation und Division. Die grafische Benutzeroberfläche (GUI) ermöglicht eine intuitive Interaktion. Der Fokus des Projekts liegt auf der Anwendung moderner Java-Programmiertechniken wie Streams und Lambda-Ausdrücken.

---

## Mathematische Funktionen

### Implementierte Operationen:
1. **Addition**: Zwei Zahlen werden addiert.
2. **Subtraktion**: Die Differenz zwischen zwei Zahlen wird berechnet.
3. **Multiplikation**: Zwei Zahlen werden multipliziert.
4. **Division**: Eine Zahl wird durch eine andere geteilt (inklusive Fehlerbehandlung bei Division durch 0).

### Erweiterungsmöglichkeiten:
- **Potenzieren**: z. B. \(a^b\).
- **Wurzeln**: Quadratwurzel oder andere Wurzeln.
- **Trigonometrie**: Sinus, Kosinus, Tangens.

---

## Benutzeroberfläche

### Eigenschaften:
- **Eingabefelder**: Für die beiden Zahlen, die berechnet werden sollen.
- **Dropdown-Menü**: Auswahl der mathematischen Operation (Addition, Subtraktion usw.).
- **Berechnungsschaltfläche**: Führt die Berechnung durch.
- **Ergebnisanzeige**: Zeigt das Ergebnis oder eine Fehlermeldung an.


#### Workflow:
1. Der Benutzer gibt zwei Zahlen ein.
2. Wählt die gewünschte mathematische Operation.
3. Klickt auf "Berechnen", und das Ergebnis wird angezeigt.

---

## Einsatz von Java Streams

### Streams in der Anwendung:
1. **Operationen-Zuweisung**:
   - Streams durchsuchen die Map der mathematischen Operationen, um die ausgewählte Funktion auszuführen.
2. **Ergebnisberechnung**:
   - Die Berechnung erfolgt funktional mit `Stream.of()` und `map()`.
3. **Fehlerbehandlung**:
   - Streams filtern ungültige Eingaben und geben eine Fehlermeldung aus.

---

## Parallelisierungsmöglichkeiten

### Potenziale:
1. **Batch-Berechnungen**:
   - Mehrere Berechnungen könnten parallelisiert werden, z. B. wenn eine Liste von Zahlen bearbeitet wird.
2. **Komplexere Algorithmen**:
   - Erweiterte Funktionen wie numerische Integration oder rekursive Berechnungen können von parallelen Streams profitieren.
3. **GUI-Optimierung**:
   - Langlaufende Berechnungen könnten in separaten Threads (z. B. mit `CompletableFuture`) ausgeführt werden, um die Benutzeroberfläche responsiv zu halten.
