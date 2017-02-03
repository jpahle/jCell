<IMG ALIGN="RIGHT" ALT="Bild: jCell.gif" SRC="classes/jCell.gif" HEIGHT=100 WIDTH=100>

# ZELLULARAUTOMAT: *jCell*

Ein Zellularautomat besteht aus einem regelmäßigen Raster, in dem viele gleichartige Zellen angeordnet sind. Jede einzelne Zelle stellt dabei einen eigenständigen endlichen Automaten dar. Dieser ändert seinen Zustand abhängig davon, in welchen Zuständen sich benachbarte Zellen befinden. D.h. global gesehen wird für Berechnungen nur lokal verfügbare Information herangezogen. Dieses von der Biologie inspirierte Modell für massiv parallele Berechnungen ist insbesondere mindestens turing-mächtig und kann sogar als Basis für selbstreproduzierende Strukturen dienen. Praktische Anwendungen von Zellularautomaten reichen von der Stauforschung bis hin zu Simulationen von chemischen oder physikalischen Reaktionen.

Aufgabe dieses Projektes war es, einen Simulator für Zellularautomaten zu entwerfen. Dieser sollte auf Java basierend über das Web erreichbar sein und dem interessierten Anfänger eine einfach zu bedienende Umgebung zur Erforschung von Zellularautomaten bieten. Andererseits sollte das zu entwerfende System flexibel genug sein, um spätere Erweiterungen zuzulassen.

Bei der Implementierung wurde kein großer Wert auf Geschwindigkeit gelegt. Der Schwerpunkt lag auf der einfachen und intuitiven Bedienbarkeit.

***
Das Applet *jCell* kann auf [jpahle.github.io/jCell](https://jpahle.github.io/jCell) ausprobiert werden.

***

## Zur Bedienung:

Das Programm *jCell* kann sowohl als Application, als auch als Applet aufgerufen werden. Die Startklasse ist

    Simulator.class

Beim Aufruf als Applet wird zuerst ein Startknopf angezeigt. Durch Anklicken dieses Knopfes wird ein Kontrollfenster geöffnet, das alle notwendigen Bedienelemente beinhaltet. Dieses Kontrollfenster erscheint beim Aufruf als Application sofort. Das Programm nimmt keine Kommandozeilenparameter entgegen.  
Ein angezeigtes Kontrollfenster teilt sich in fünf Bereiche:

<OL type=i>
<LI>Eine Reihe Buttons ganz oben.</LI>
<LI>Ein Bereich in dem Berechnungsvorgänge gestartet werden können.</LI>
<LI>Ein Bereich, in dem Informationen für den Benutzer angezeigt werden.</LI>
<LI>Ein Panel zum Neuinitialisieren des ZA und zum Einstellen einer Verzögerung der graphischen Ausgabe.</LI>
<LI>Ein Textfeld ganz unten, in dem Meldungen über Aktionen des Benutzers bzw. über Fehler ausgegeben werden.</LI>
</OL>

Anfangs ist nur der Load-Button, der New-Button und der Exit-Button aktiviert. Mit dem Exit-Button kann das Programm jederzeit beendet werden. Der Load-Button verursacht ein Laden eines Zellularautomaten aus einer Datei. Bei der Ausführung von *jCell* innerhalb eines Browsers ist dieser Button wegen Sicherheitsrestriktionen nicht aktiv! Mit dem New-Button kann ein neuer Zellularautomat erzeugt werden. Wurde ein Zellularautomat erfolgreich geladen oder erzeugt, so werden die übrigen Buttons aktiv. Beim Anklicken des Buttons "Show" wird der aktuelle Zustand des Zellularautomaten in einem eigenen Fenster graphisch dargestellt. Der Show-Button wird dadurch zum Hide-Button, mit dessen Hilfe die graphische Ausgabe wieder beendet werden kann. Die Zustände der Zellen des Zellularautomaten können auf einfache Weise verändert werden, indem man die entsprechende Zelle in der graphischen Ausgabe anklickt. Dabei werden alle möglichen Zustände  zyklisch durchlaufen. Man kann aber auch mit dem Reset-Button alle Zustände im Zellularautomat auf Null setzen oder mit dem Random-Button alle Zustände auf zufällige Werte setzen. Ist der angezeigte Bereich kleiner als der ganze Zellenraum, kann man mit den entsprechenden Scrollbars die Anzeige verschieben. Mit dem Save-Button wird der aktuelle Zellularautomat in einer Datei abgespeichert. Dieser Save-Button ist wie der Load-Button bei der Ausführung als Applet nicht aktiviert.  
Die eigentliche Berechnung wird durch die Buttons "Run" und "Step" angestoßen. Mit "Step" wird genau ein Übergang berechnet. Durch Drücken des Buttons "Run" wird die im Textfeld "Steps" angegebene Anzahl von Übergängen abgearbeitet. Das Anklicken des Buttons "Stop" hält den Berechnungsvorgang an. Der Zähler der jeden durchgeführten Übergang mitzählt, wird durch Betätigen des Buttons "Reset Counter" auf Null zurückgesetzt. Möchte man einen Zellularautomaten verändern, so kann man dazu mit Hilfe des Edit-Button ein entsprechendes Fenster öffnen.

***

## Angaben zum Programm:

Das Programm *jCell* besteht aus ca. 2400 Zeilen Quellcode. Das entspricht ungefähr 64 KByte. Die Entwicklungszeit betrug ca. 2 bis 3 Wochen. Auf einen modularen Entwurf wurde Wert gelegt, um spätere Erweiterungen zuzulassen. Denkbar wäre z.B. eine leistungsfähige Möglichkeit Zellularautomaten in einer Art Programmiersprache in einer Datei zu definieren oder geladene Zellularautomaten zu editieren.  
Die [Programmdokumentation](https://jpahle.github.io/jCell/doc/Package-jCell.html) ist hier einsehbar.  
Das System *jCell* ist auch als [zip-Archiv](https://github.com/jpahle/jCell/archive/master.zip) downloadbar.  
Bitte beachten sie die in den Dateien LICENSE und COPYRIGHT angegebenen rechtlichen Bestimmungen zur Benutzung und Weitergabe dieses Programmes.

***

<ADDRESS>
[Mail an den Autor J. Pahle, Universität Karlsruhe, E-Post: jCell@pahle.de](mailto: jCell@pahle.de)  
Letzte Änderung am 17. August, 1999, Version 0.99
</ADDRESS>  
Copyright © by J. Pahle 1999. Alle Rechte vorbehalten.
