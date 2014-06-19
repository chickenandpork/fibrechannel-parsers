nodist_java_JAVA = Alias4Parser.java FCParser.java NicknameParser.java ParserTee.java ZPAliasEntry.java ZPFCAliasEntry.java ZPZoneEntry.java ZoneParser.java ZoneParserVal.java ZoneParserI.java
nodist_java_JAVA += AliShowZoneParser.java
nodist_java_JAVA += BNAZoneParser.java
nodist_java_JAVA += DeviceAliasParser.java
nodist_java_JAVA += Select2Sample.java
nodist_java_JAVA += ShowZoneParser.java
nodist_java_JAVA += version.java
javadir = $(pkgdir)


nodist_java_DATA = @PACKAGE@.jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel


EXTRA_DIST = $(nodist_java_JAVA)  zonefile.y bnazone.y version.java.in
CONFIG_CLEAN_FILES = @PACKAGE@.jar AliShowZoneParser.java AliShowZoneParserVal.java Alias4Parser.java BNAZoneParser.java BNAZoneParserVal.java DeviceAliasParser.java
CONFIG_CLEAN_FILES += DeviceAliasParserVal.java FCParser.java NicknameParser.java version.java
CONFIG_CLEAN_FILES += bnazone.y devicealias.y showzone.y zonefile.y

AM_JAVACFLAGS= @JAVAC_FLAGS@ -cp $(JAVAROOT):@GETOPTJAR@:$(top_builddir)/depjars/commons-io-2.3.jar

BUILDTMP=$(shell date +%s)
BUILT_SOURCES = FCParser.java


if DO_BYACC

BUILT_SOURCES += AliShowZoneParser.java

AliShowZoneParser.java: zonefile.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=AliShowZoneParser -J $<

BUILT_SOURCES += BNAZoneParser.java

BNAZoneParser.java: bnazone.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=BNAZoneParser -J $<

BUILT_SOURCES += ShowZoneParser.java

ShowZoneParser.java: showzone.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=ShowZoneParser -J $<

BUILT_SOURCES += DeviceAliasParser.java

DeviceAliasParser.java: devicealias.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=DeviceAliasParser -J $<

endif
	

JAVAROOT = bld
BUILT_SOURCES += javaroot.stamp

javaroot.stamp: $(nodist_java_JAVA)
	mkdir -p $(JAVAROOT) && touch $@

@PACKAGE@.jar: classjava.stamp
	awk -v BUILDTOP="bld" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_java_JAVA) | $(SHELL)
	touch @PACKAGE@.class

clean-local:
	CLASSES=`for i in $(nodist_java_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)
	rm -f @PACKAGE@.jar $(notdir $(subdir)).jar $(BUILT_SOURCES)

if DO_CHECKLOCAL_PARSER
check-local: BNAZoneParser.java BNAZoneParserVal.java test-bnazone.txt
	@JAVA@ -Ddebug.verboseTokenizer=true -classpath bld:$(top_builddir) org.smallfoot.parser.zone.BNAZoneParser < test-bnazone.txt
endif