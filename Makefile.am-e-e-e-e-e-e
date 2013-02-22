DIST_SUBDIRS =	pipeparser byaccj-zonefile basic-nicknamefile
if DO_JAVA
SUBDIRS =	pipeparser byaccj-zonefile basic-nicknamefile
endif

# if missing mkinstalldirs install-sh trigger errors, ignore the result code on the "system (cp ...)" command in automake
dnl CONFIG_CLEAN_FILES = aclocal.m4 config.guess config.sub config.h config.h.in ltconfig ltmain.sh missing mkinstalldirs install-sh Doxyfile @PACKAGE@.spec @PACKAGE@.jar changes.atom
dnl EXTRA_DIST = @PACKAGE@.spec nginx-1.0.6.tar.gz



nodist_pkgdata_DATA = $(subdir).jar

EXTRA_DIST = $(nodist_pkgdata_JAVA)
CONFIG_CLEAN_FILES = $(subdir).jar

AM_JAVACFLAGS= -cp $(top_builddir)/java-pre/getopt.jar:$(top_builddir):.

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


$(subdir).jar: pipeparser/pipeparser.jar byaccj-zonefile/@PACKAGE@.jar  basic-nicknamefile/basic-nicknamefile.jar
	-rm -fr $(@D)/.workdir
	mkdir -p $(@D)/.workdir
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	rm -fr $(@D)/.workdir/META-INF/{LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY}.{RSA,SF}}
	echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF gnu org) > $@
	rm -fr $(@D)/.workdir
	touch $@
