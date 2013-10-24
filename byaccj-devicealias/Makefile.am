nodist_pkgdata_JAVA = DeviceAliasParser.java DeviceAliasParserVal.java

nodist_pkgdata_DATA = @PACKAGE@.jar $(notdir $(subdir)).jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel

EXTRA_DIST = $(nodist_pkgdata_JAVA)  devicealias.y
CONFIG_CLEAN_FILES = @PACKAGE@.jar $(notdir $(subdir)).jar DeviceAliasParser.java DeviceAliasParserVal.java

AM_JAVACFLAGS= -cp $(top_builddir)/pipeparser/pipeparser.jar:$(JAVAROOT):.

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


if DO_BYACC
BUILT_SOURCES += DeviceAliasParser.java DeviceAliasParserVal.java

DeviceAliasParser.java DeviceAliasParserVal.java: devicealias.y
	echo $(BYACC) -Jnoconstruct -Jnorun -Jpackage=org.smallfoot.parser.zone -Jimplements=ZoneParser -Jextends=Thread -Jclass=DeviceAliasParser -J $<
	$(BYACC) -Jnoconstruct -Jnorun -Jpackage=org.smallfoot.parser.zone -Jextends=ZoneParser -Jclass=DeviceAliasParser -J $<

endif
	

JAVAROOT = bld
BUILT_SOURCES += javaroot.stamp

javaroot.stamp: $(nodist_pkgdata_JAVA)
	mkdir -p $(JAVAROOT) && touch $@

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk -v BUILDTOP="bld" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	touch $(notdir $(subdir)).class

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)
	rm -f @PACKAGE@.jar $(notdir $(subdir)).jar $(BUILT_SOURCES)

if DO_CHECKLOCAL_PARSER
check-local: @PACKAGE@.jar test-devicealias.txt
	@JAVA@ -Ddebug.verboseTokenizer=true -classpath @PACKAGE@.jar:$(top_builddir)/pipeparser/bld org.smallfoot.parser.zone.DeviceAliasParser < test-devicealias.txt
endif


$(notdir $(subdir)).jar: $(BUILT_SOURCES) $(top_builddir)/pipeparser/pipeparser.jar @PACKAGE@.jar
	-rm -fr $(@D)/.workdir
	mkdir -p $(@D)/.workdir
	echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.parser.zone.DeviceAliasParser"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	rm -fr $(@D)/.workdir/META-INF/{LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY}.{RSA,SF}}
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF org) > $@
	rm -fr $(@D)/.workdir
	touch $@
