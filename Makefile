all: exec

build: *.java
	javac $^

exec: build
	java -cp . Solver

clean:
	rm *.class
