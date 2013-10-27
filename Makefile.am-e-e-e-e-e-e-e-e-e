nodist_pkgdata_JAVA = Handler.java DCNMURLConnection.java 
nodist_pkgdata_DATA = $(subdir).jar

EXTRA_DIST = $(nodist_pkgdata_JAVA)  Handler.java.in DCNMURLConnection.java.in
CONFIG_CLEAN_FILES = $(subdir).jar

AM_JAVACFLAGS= -cp @FCPARSERSJAR@
BUILDTMP=$(shell date +%s)

BUILT_SOURCES = 

BUILT_SOURCES += postgresql-9.1-902.jdbc4.jar

postgresql-9.1-902.jdbc4.jar:
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://jdbc.postgresql.org/download/$(@F) || curl -Lo $@ http://jdbc.postgresql.org/download/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)

JAVAROOT = bld
BUILT_SOURCES += javaroot.stamp

javaroot.stamp: $(nodist_pkgdata_JAVA)
	mkdir -p $(JAVAROOT) && touch $@

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk -v BUILDTOP="bld" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	touch $(notdir $(subdir)).class

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)


$(subdir).jar: @PACKAGE@.jar $(BUILT_SOURCES)
	@-rm -fr $(@D)/.workdir
	@mkdir -p $(@D)/.workdir
	@echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	@echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.dcnmsql.DCNMURLConnection"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	(cd $(@D)/.workdir && find * -name Driver.class -print) | sed -e 's@/@.@g' -e 's/.class$$//g' > $(@D)/.workdir/META-INF/services/java.sql.Driver
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF/services/java.sql.Driver org) > $@
	rm -fr $(@D)/.workdir


