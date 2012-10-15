nodist_pkgdata_JAVA = AliShowZoneParser.java AliShowZoneParserVal.java ZoneParser.java ZPAliasEntry.java ZPZoneEntry.java
nodist_pkgdata_DATA = @PACKAGE@.jar $(subdir).jar
# https://downloads.sourceforge.net/project/byaccj/byaccj/1.15/byaccj1.15_macosx.tar.gz?r=http%3A%2F%2Fbyaccj.sourceforge.net%2F&ts=1347235095&use_mirror=voxel

EXTRA_DIST = $(nodist_pkgdata_JAVA)  zonefile.y
CONFIG_CLEAN_FILES = 

AM_JAVACFLAGS= -cp $(top_builddir)/java-pre/getopt.jar:$(top_builddir):.

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


if DO_BYACC
BUILT_SOURCES += AliShowZoneParser.java AliShowZoneParserVal.java

AliShowZoneParser.java AliShowZoneParserVal.java: zonefile.y
	$(BYACC) -Jpackage=org.smallfoot.parser.zone -Jimplements=ZoneParser -Jclass=AliShowZoneParser -J $<
endif
	

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk -v BUILDTOP="$(top_builddir)" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	echo jar uef org.smallfoot.vi.VirtualRegent $@
	touch $$(basename $(subdir)).class

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)

check-local: $(top_builddir)/java-pre/getopt.jar @PACKAGE@.jar sample.zone
	$(JAVA) $(AM_JAVACFLAGS) org.smallfoot.parser.zone.AliShowZoneParser < sample.zone


$(subdir).jar: $(BUILT_SOURCES) $(top_builddir)/java-pre/getopt.jar @PACKAGE@.jar
	-rm -fr $(@D)/.workdir
	mkdir -p $(@D)/.workdir
	echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.vi.VIMailDrop"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	rm -fr $(@D)/.workdir/META-INF/{LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY}.{RSA,SF}}
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF gnu org) > $@
	rm -fr $(@D)/.workdir
	touch $@
