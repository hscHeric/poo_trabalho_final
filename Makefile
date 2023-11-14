all: exec

build: *.java
	javac $^

exec: build
	java -cp . Program

clean:
	rm *.class
	clear
