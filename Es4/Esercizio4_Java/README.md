**COMPILAZIONE**

----PER COMPILARE LE CLASSI PER LA STRUTTURA DATI Graph NEL PACKAGE graph---
1) posizionarsi in .../Graph/src
2) javac -d ../classes graph/Graph.java

---PER COMPILARE IL PACKAGE graphusagejava---
1) posizionarsi in .../Graph/src
2) javac -d ../classes graphusagejava/GraphUsageJava.java

---PER COMPILARE LE CLASSI PER GLI UNIT TEST NEL PACKAGE graph---
1) posizionarsi in .../graph/src
2) javac -d ../classes -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar' graph/*.java


**ESECUZIONE**

---PER ESEGUIRE GraphUsageJava---
1) posizionarsi in .../Graph/classes
2) java graphusagejava/GraphUsageJava TODO !!!! 


---PER ESEGUIRE graph/GraphJavaTestsRunner---
1) posizionarsi in .../Graph/classes 
2) java -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar'  graph/GraphJavaTestsRunner

