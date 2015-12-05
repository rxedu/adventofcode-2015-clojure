# Build Environment
ADVENT_INPUT = input
ADVENT_OUTPUT = output
ADVENT_DAYS = days

all: output

clean:
	@echo Removing $(ADVENT_OUTPUT)/
	@rm -rf $(ADVENT_OUTPUT)

output:
	@mkdir -p $(ADVENT_OUTPUT)

.PHONY: output
