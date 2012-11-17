nodist_pkgdata_JAVA = Handler.java BNAPURLConnection.java 
nodist_pkgdata_DATA = $(subdir).jar

EXTRA_DIST = $(nodist_pkgdata_JAVA)  Handler.java.in BNAPURLConnection.java.in
CONFIG_CLEAN_FILES = $(subdir).jar

AM_JAVACFLAGS= -cp $(top_builddir)
BUILDTMP=$(shell date +%s)

BUILT_SOURCES = 

BUILT_SOURCES += postgresql-9.1-902.jdbc4.jar

postgresql-9.1-902.jdbc4.jar:
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://jdbc.postgresql.org/download/$(@F) || curl -Lo $@ http://jdbc.postgresql.org/download/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)

@PACKAGE@.jar: classnodist_pkgdata.stamp
	awk '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvf $@"; printf " -C .. %s",n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	awk -v BUILDTOP="$(top_builddir)" '/^package / {gsub (";$$",""); gsub ("\\.","/",$$2); SUBS[$$2]=$$2 } END { FIRST=0; for (n in SUBS) { if (0 == FIRST++) printf "jar cvef org.smallfoot.vi.VIFieldTool $@"; printf " -C %s %s",BUILDTOP,n }; printf "\n"; }' $(nodist_pkgdata_JAVA) | $(SHELL)
	touch $(subdir).class

clean-local:
	CLASSES=`for i in $(nodist_pkgdata_JAVA); do awk -v FILE="$$i" '/^package / {gsub (";$$",""); gsub (".java",".class",FILE); gsub ("\\\\.","/",$$2); printf "%s/%s\n",$$2,FILE}' $(srcdir)/$$i; done`; (cd $(top_builddir)/ && rm -f $$CLASSES)


$(subdir).jar: @PACKAGE@.jar $(BUILT_SOURCES)
	@-rm -fr $(@D)/.workdir
	@mkdir -p $(@D)/.workdir
	@echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	@echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.bnapsql.BNAPURLConnection"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	(cd $(@D)/.workdir && jar cm MANIFEST.MF org) > $@
	rm -fr $(@D)/.workdir


