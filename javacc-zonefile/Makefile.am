# example http://java.net/projects/javacc/downloads/download/contrib/grammars/filtreLdap.jj doesn't work (4x missing return statement) with javacc-5.0
# example http://www.engr.mun.ca/~theo/JavaCC-Tutorial/javacc-tutorial.pdf doesn't work (4x missing return statement) with javacc-5.0
# example http://www.engr.mun.ca/~theo/JavaCC-Tutorial/javacc-tutorial.pdf doesn't work (ZoneParse.java:265: as of release 5, 'enum' is a keyword, and may not be used as an identifier) with javacc-3.1

nodist_pkgdata_JAVA =
if DO_BUILD_JAVACC
nodist_pkgdata_JAVA += SimpleCharStream.java Token.java TokenMgrError.java ParseException.java ZoneParse.java ZoneParseTokenManager.java ZoneParseConstants.java
endif
nodist_pkgdata_DATA = @PACKAGE@.jar

EXTRA_DIST = $(nodist_pkgdata_JAVA)  ZoneParse.jj
CONFIG_CLEAN_FILES = vitools.jar

AM_JAVACFLAGS= -cp $(top_builddir)/xpath/xpath.jar:$(top_builddir)/java-pre/getopt.jar:$(top_builddir)/$(subdir)/AdventNetLogging.jar:$(top_builddir)/$(subdir)/AdventNetSnmp.jar:$(top_builddir)/$(subdir)/httpclient-4.1.1.jar:$(top_builddir)/$(subdir)/ftp4j-1.7.1.jar:$(top_builddir)/$(subdir)/commons-net-3.1.jar:$(top_builddir)
BUILDTMP=$(shell date +%s)

BUILT_SOURCES = 

BUILT_SOURCES += javacc.jar 
# http://java.net/downloads/javacc/oldversions/javacc-3.1.zip
VER_JAVACC := 3.2

javacc-$(VER_JAVACC).zip:
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || curl -o $@ http://java.net/downloads/javacc/javacc-$(VER_JAVACC).zip
	test ! -f $@ || unzip -t $@ || rm -f $@
	test -f $@ || curl -o $@ http://java.net/downloads/javacc/oldversions/javacc-$(VER_JAVACC).zip
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
	touch $@

javacc.jar: javacc-$(VER_JAVACC).zip
	unzip -o -j $< '*/bin/lib/$@' && touch $@
	jar uef javacc $@

SimpleCharStream.java Token.java TokenMgrError.java ParseException.java ZoneParse.java ZoneParseTokenManager.java ZoneParseConstants.java: javacc.jar ZoneParse.jj
	java -jar $^

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk -v BUILDTOP="$(top_builddir)" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvef org.smallfoot.parser.zone.ZoneParse $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)
	rm -f SimpleCharStream.java Token.java TokenMgrError.java ParseException.java ZoneParse.java ZoneParseTokenManager.java ZoneParseConstants.java

xcheck-local: check1.out

check0.out: @PACKAGE@.jar test.zone
	$(JAVA) -jar @PACKAGE@.jar < test.zone

check1.out: javacc.jar Calc.jj
	rm -f SimpleCharStream.java Token.java TokenMgrError.java ParseException.java 
	java -jar $^
	javac $(AM_JAVACFLAGS) SimpleCharStream.java Token.java TokenMgrError.java ParseException.java Calc.java CalcTokenManager.java CalcConstants.java
	$(JAVA) -cp . Calc

Calc.java CalcTokenManager.java CalcConstants.java: javacc.jar Calc.jj
	java -jar $^

%.class: %.java
	javac $(AM_JAVACFLAGS) $^

