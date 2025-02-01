build:
	make -C app build

run-dist:
	make -C app run-dist

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

clean:
	make -C app clean

install:
	make -C app clean install

.PHONY: build