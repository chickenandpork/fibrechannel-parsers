nodist_pkgdata_JAVA = ZoneParser.java ZPAliasEntry.java ZPZoneEntry.java ParserTee.java ZPFCAliasEntry.java

nodist_pkgdata_DATA = $(notdir $(subdir)).jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel

EXTRA_DIST = $(nodist_pkgdata_JAVA)
CONFIG_CLEAN_FILES = $(notdir $(subdir)).jar @PACKAGE@.jar

AM_JAVACFLAGS= -cp bld:$(top_builddir)/java-pre/getopt.jar:$(top_builddir)/documentation/commons-io-2.3.jar

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


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

$(notdir $(subdir)).jar: $(BUILT_SOURCES) $(top_builddir)/java-pre/getopt.jar $(top_builddir)/documentation/commons-io-2.3.jar @PACKAGE@.jar
	-rm -fr $(@D)/.workdir
	mkdir -p $(@D)/.workdir
	echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.parser.zone.AliShowZoneParser"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	rm -fr $(@D)/.workdir/META-INF/{LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY}.{RSA,SF}}
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF gnu org) > $@
	rm -fr $(@D)/.workdir
	touch $@
