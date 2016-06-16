.PHONY: all package run clean dist-clean

all: package

package:
	./mvnw package

run: package
	java -jar target/contatoscard-0.1.0-SNAPSHOT.jar

clean:
	./mvnw clean
	rm -f *.vcf

dist-clean: clean
	rm -f .mvn/wrapper/maven-wrapper.jar
