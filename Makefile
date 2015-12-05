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
	@n=1; \
	while [ $${n} -le 25 ] ; do \
		[ -e ./days/$$n ] && echo Day $$n && ./days/$$n && echo ; \
		n=`expr $$n + 1`; \
	done; \
	true

.PHONY: output
