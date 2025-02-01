build:
	@./gradlew clean build

run-dist:
	@./build/install/app/bin/app

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

lint:
	@./gradlew checkstyleMain checkstyleTest

clean:
	@./gradlew clean

install:
	@./gradlew clean installDist

.PHONY: build