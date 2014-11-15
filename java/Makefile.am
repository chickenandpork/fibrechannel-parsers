nodist_java_JAVA  = $(srcdir)/Alias4Parser.java $(srcdir)/FCParser.java $(srcdir)/NicknameParser.java $(srcdir)/SwappedNicknameParser.java
nodist_java_JAVA += $(srcdir)/ParserTee.java $(srcdir)/ZPAliasEntry.java $(srcdir)/ZPFCAliasEntry.java
nodist_java_JAVA += $(srcdir)/ZPZoneEntry.java $(srcdir)/ZoneParser.java $(srcdir)/ZoneParserVal.java
nodist_java_JAVA += $(srcdir)/ZoneParserI.java
nodist_java_JAVA += $(srcdir)/SSHCLI.java $(abs_builddir)/ssh/Handler.java $(srcdir)/SSHURLConnection.java
nodist_java_JAVA += $(srcdir)/Select2Sample.java $(srcdir)/TSVParser.java
nodist_java_JAVA += AliShowZoneParser.java
nodist_java_JAVA += BNAZoneParser.java
nodist_java_JAVA += DeviceAliasParser.java
nodist_java_JAVA += ShowZoneParser.java
nodist_java_JAVA += ShowZone2Parser.java
nodist_java_JAVA += VW4InvalidAddedParser.java
nodist_java_JAVA += $(abs_builddir)/genproto/bna/Handler.java $(abs_builddir)/genproto/bnapsql/Handler.java $(abs_builddir)/genproto/cmcne/Handler.java $(abs_builddir)/genproto/dcfm/Handler.java BNAPURLConnection.java
nodist_java_JAVA += $(abs_builddir)/genproto/ocidwh/Handler.java $(abs_builddir)/genproto/osmsql/Handler.java $(abs_builddir)/genproto/msosmsql/Handler.java OSMURLConnection.java DWHURLConnection.java MSDWHURLConnection.java
nodist_java_JAVA += $(abs_builddir)/genproto/dcnm/Handler.java $(abs_builddir)/genproto/dcnmsql/Handler.java DCNMURLConnection.java DCNMSQLURLConnection.java
nodist_java_JAVA += $(abs_builddir)/genproto/vmware/Handler.java VCenterURLConnection.java
nodist_java_JAVA += version.java
javadir = $(pkgdir)


nodist_java_DATA = @PACKAGE@.jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel


EXTRA_DIST  = Alias4Parser.java FCParser.java NicknameParser.java TSVParser.java SwappedNicknameParser.java
EXTRA_DIST += ParserTee.java ZPAliasEntry.java ZPFCAliasEntry.java
EXTRA_DIST += ZPZoneEntry.java ZoneParser.java ZoneParserVal.java
EXTRA_DIST += ZoneParserI.java
EXTRA_DIST += Select2Sample.java
EXTRA_DIST += SSHCLI.java.in SSHURLConnection.java sshHandler.java.in
EXTRA_DIST += zonefile.y bnazone.y version.java.in HandlerPrototype.javaproto
CONFIG_CLEAN_FILES =  @PACKAGE@.jar AliShowZoneParser.java AliShowZoneParserVal.java Alias4Parser.java BNAZoneParser.java BNAZoneParserVal.java DeviceAliasParser.java
CONFIG_CLEAN_FILES += DeviceAliasParserVal.java FCParser.java NicknameParser.java version.java TSVParser.java
CONFIG_CLEAN_FILES += bnazone.y devicealias.y showzone.y showzone2.y zonefile.y vw4invalid.y
CONFIG_CLEAN_FILES += BNAPURLConnection.java DCNMURLConnection.java DCNMSQLURLConnection.java DWHURLConnection.java MSDWHURLConnection.java OSMURLConnection.java VCenterURLConnection.java

AM_JAVACFLAGS= @JAVAC_FLAGS@ -cp $(JAVAROOT):@GETOPTJAR@:$(top_builddir)/depjars/commons-io-2.3.jar:$(top_builddir)/depjars/ssh.jar:$(top_builddir)/depjars/bcpg-jdk15on-151.jar:$(top_builddir)/depjars/bcpkix-jdk15on-151.jar:$(top_builddir)/depjars/bcprov-jdk15on-151.jar:$(top_builddir)/depjars/vijava5120121125.jar:$(top_builddir)/depjars/dom4j-1.6.1.jar

BUILDTMP=$(shell date +%s)
BUILT_SOURCES = FCParser.java

# build the Handler stubs
$(abs_builddir)/genproto/bna/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/bna/g' -e 's/##localtype##/BNAP/g' -e 's/##localtypepkg##/bnapsql/g' $< > $@

$(abs_builddir)/genproto/bnapsql/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/bnapsql/g' -e 's/##localtype##/BNAP/g' -e 's/##localtypepkg##/bnapsql/g' $< > $@

$(abs_builddir)/genproto/cmcne/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/cmcne/g' -e 's/##localtype##/BNAP/g' -e 's/##localtypepkg##/bnapsql/g' $< > $@

$(abs_builddir)/genproto/dcfm/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/dcfm/g' -e 's/##localtype##/BNAP/g' -e 's/##localtypepkg##/bnapsql/g' $< > $@

$(abs_builddir)/genproto/ocidwh/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/ocidwh/g' -e 's/##localtype##/DWH/g' -e 's/##localtypepkg##/ocidwh/g' $< > $@

$(abs_builddir)/genproto/osmsql/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/osmsql/g' -e 's/##localtype##/OSM/g' -e 's/##localtypepkg##/osmsql/g' $< > $@

$(abs_builddir)/genproto/msosmsql/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/msosmsql/g' -e 's/##localtype##/MSDWH/g' -e 's/##localtypepkg##/msosmsql/g' $< > $@

$(abs_builddir)/genproto/dcnmsql/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/dcnmsql/g' -e 's/##localtype##/DCNMSQL/g' -e 's/##localtypepkg##/dcnmsql/g' $< > $@

$(abs_builddir)/genproto/dcnm/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/dcnm/g' -e 's/##localtype##/DCNM/g' -e 's/##localtypepkg##/dcnm/g' $< > $@

$(abs_builddir)/genproto/vmware/Handler.java: $(srcdir)/HandlerPrototype.javaproto
	@mkdir -p $(@D)
	sed -e 's/##protocol##/vmware/g' -e 's/##localtype##/VCenter/g' -e 's/##localtypepkg##/vmware/g' $< > $@

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

BUILT_SOURCES += ShowZone2Parser.java

ShowZone2Parser.java: showzone2.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=ShowZone2Parser -J $<

BUILT_SOURCES += DeviceAliasParser.java

DeviceAliasParser.java: devicealias.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=DeviceAliasParser -J $<

BUILT_SOURCES += VW4InvalidAddedParser.java

VW4InvalidAddedParser.java: vw4invalid.y
	$(BYACC) -Jstack=3000 -Jnoconstruct -Jnorun -Jsemantic=ZoneParserVal -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParserI -Jclass=VW4InvalidAddedParser -J $<

endif
	

JAVAROOT = bld
BUILT_SOURCES += javaroot.stamp

javaroot.stamp: $(nodist_java_JAVA)
	mkdir -p $(JAVAROOT) && touch $@

@PACKAGE@.jar: classjava.stamp
	pwd
	awk -v BUILDTOP="bld" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_java_JAVA) | $(SHELL)
	touch @PACKAGE@.class

clean-local:
	CLASSES=`for i in $(nodist_java_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)
	rm -f @PACKAGE@.jar $(notdir $(subdir)).jar $(BUILT_SOURCES)
	rm -fr $(JAVAROOT) genproto 

if DO_CHECKLOCAL_PARSER
check-local: BNAZoneParser.java BNAZoneParserVal.java test-bnazone.txt
	@JAVA@ -Ddebug.verboseTokenizer=true -classpath bld:$(top_builddir) org.smallfoot.parser.zone.BNAZoneParser < test-bnazone.txt
endif
