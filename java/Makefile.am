nodist_pkgdata_JAVA = Alias4Parser.java NicknameParser.java ParserTee.java ZPAliasEntry.java ZPFCAliasEntry.java ZPZoneEntry.java ZoneParser.java
nodist_pkgdata_JAVA += AliShowZoneParser.java AliShowZoneParserVal.java
nodist_pkgdata_JAVA += BNAZoneParser.java BNAZoneParserVal.java
nodist_pkgdata_JAVA += DeviceAliasParser.java DeviceAliasParserVal.java
nodist_pkgdata_JAVA += version.java

nodist_pkgdata_DATA = @PACKAGE@.jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel


EXTRA_DIST = $(nodist_pkgdata_JAVA)  zonefile.y bnazone.y version.java.in
CONFIG_CLEAN_FILES = @PACKAGE@.jar AliShowZoneParser.java AliShowZoneParserVal.java BNAZoneParser.java BNAZoneParserVal.java DeviceAliasParser.java DeviceAliasParserVal.java

AM_JAVACFLAGS= -cp $(JAVAROOT):$(top_builddir)/depjars/commons-io-2.3.jar

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


if DO_BYACC
BUILT_SOURCES += AliShowZoneParser.java AliShowZoneParserVal.java

AliShowZoneParser.java AliShowZoneParserVal.java: zonefile.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParser -Jclass=AliShowZoneParser -J $<

BUILT_SOURCES += BNAZoneParser.java BNAZoneParserVal.java

BNAZoneParser.java BNAZoneParserVal.java: bnazone.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParser -Jclass=BNAZoneParser -J $<

BUILT_SOURCES += DeviceAliasParser.java DeviceAliasParserVal.java

DeviceAliasParser.java DeviceAliasParserVal.java: devicealias.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParser -Jclass=DeviceAliasParser -J $<

endif
	

JAVAROOT = bld
BUILT_SOURCES += javaroot.stamp

javaroot.stamp: $(nodist_pkgdata_JAVA)
	mkdir -p $(JAVAROOT) && touch $@

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk -v BUILDTOP="bld" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	touch @PACKAGE@.class

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)
	rm -f @PACKAGE@.jar $(notdir $(subdir)).jar $(BUILT_SOURCES)

if DO_CHECKLOCAL_PARSER
check-local: BNAZoneParser.java BNAZoneParserVal.java test-bnazone.txt
	@JAVA@ -Ddebug.verboseTokenizer=true -classpath bld:$(top_builddir) org.smallfoot.parser.zone.BNAZoneParser < test-bnazone.txt
endif
