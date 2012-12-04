GCC=/usr/bin/gcc
#OPTIMISE="-03"
IFILES="main.c"
OFILES=main
OPTIONS=--verbose
ODIR=build

all build:
	mkdir ${ODIR} > /dev/null 2>&1
	${GCC} ${OPTIMISE} ${IFILES} -o ${ODIR}/${OFILES} ${OPTIONS}
clean:
	rm -fr ${ODIR}/${OFILES}
