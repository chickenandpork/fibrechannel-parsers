DIST_SUBDIRS =	depjars pipeparser byaccj-zonefile basic-nicknamefile basic-alias4file byaccj-devicealias tests-autotest
SUBDIRS =	depjars
if DO_JAVA
SUBDIRS +=	pipeparser byaccj-zonefile basic-nicknamefile basic-alias4file byaccj-devicealias tests-autotest
endif

ACLOCAL_AMFLAGS = -I m4

# if missing mkinstalldirs install-sh trigger errors, ignore the result code on the "system (cp ...)" command in automake
dnl CONFIG_CLEAN_FILES = aclocal.m4 config.guess config.sub config.h config.h.in ltconfig ltmain.sh missing mkinstalldirs install-sh Doxyfile @PACKAGE@.spec @PACKAGE@.jar changes.atom
dnl EXTRA_DIST = @PACKAGE@.spec nginx-1.0.6.tar.gz



nodist_pkgdata_DATA = @PACKAGE@.jar

EXTRA_DIST = $(nodist_pkgdata_JAVA)
CONFIG_CLEAN_FILES = @PACKAGE@.jar

AM_JAVACFLAGS= -cp $(top_builddir)/java-pre/getopt.jar:$(top_builddir):.

BUILDTMP=$(shell date +%s)
BUILT_SOURCES =


@PACKAGE@.jar: pipeparser/pipeparser.jar byaccj-zonefile/@PACKAGE@.jar  basic-nicknamefile/basic-nicknamefile.jar basic-alias4file/basic-alias4file.jar byaccj-devicealias/byaccj-devicealias.jar
	-rm -fr $(@D)/.workdir
	mkdir -p $(@D)/.workdir
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	rm -fr $(@D)/.workdir/META-INF/{LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY}.{RSA,SF}}
	echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF org) > $@
	rm -fr $(@D)/.workdir
	touch $(notdir $(subdir)).class


if DO_DOXYGEN
doc: Doxyfile
	$(DOXYGEN)

#dwd.zip: Doxyfile
#	$(DOXYGEN)
#	rm -f $@
#	for i in $$(basename -s .awk $$(grep -l @page scripts/*.awk) ; basename -s .xsl $$(grep -l @page scripts/*.xsl)) ; do ls htdocs/html/$$i.html; done | zip $@ -@
#	unzip -t $@ >/dev/null
endif

if DO_ASTYLE
formatting: basic-alias4file/Alias4Parser.java.in basic-nicknamefile/NicknameParser.java.in pipeparser/ParserTee.java pipeparser/ZoneParser.java pipeparser/ZPAliasEntry.java pipeparser/ZPFCAliasEntry.java pipeparser/ZPZoneEntry.java
	$(MAKE) check
	for i in $^; do $(ASTYLE) -A1 $$i; done
	$(MAKE) check

git-formatting:
	$(MAKE) formatting
	git commit -a -m 'formatting and whitespace'
	echo git push
	echo git dcommit
endif


