SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_121\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\anton\Desktop\U\Segundo Semestre 2018\Compi 2\Proyecto2\Proyecto2_Compi2\src\proyecto2_compi2\Elementos_Ejecucion_Draco
java -jar c:\Fuentes\java-cup-11a.jar -parser sintactico_Draco -symbols sym sintactico_Draco.cup
pause