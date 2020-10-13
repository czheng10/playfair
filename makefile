help:
	@echo encoding: make run ARGS="encode plaintext keytext"
	@echo decoding: make run ARGS="decode plaintext keytext"

run: playfair.class
	java playfair $(ARGS)

playfair.class: Rules.class
	javac playfair.java

Rules.class: Rules.java
	javac Rules.java

clean:
	rm *.class
