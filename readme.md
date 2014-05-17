#Compile (win and unix)
javac -cp "lib/ojdbc6.jar" test/OracleConnectionTest.java

#Execute (win)
java -cp .;lib/ojdbc6.jar test/OracleConnectionTest

#Execute (unix)
java -cp ".:lib/*" test.OracleConnectionTest