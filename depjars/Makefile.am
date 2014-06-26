depjardir = @datadir@/@PACKAGE@/jars
dist_depjar_DATA = $(JARS)


EXTRA_DIST = $(nodist_pkgdata_JAVA)
CONFIG_CLEAN_FILES = $(JARS)

AM_JAVACFLAGS= -cp $(top_builddir)/$(subdir)/$(JAVAROOT)

BUILDTMP=$(shell date +%s)

JARS := commons-io-2.3.jar


commons-io-2.3.jar: commons-io-2.3-bin.tar.gz
	tar xOf $< '*/$@' > $@

commons-io-2.3-bin.tar.gz:
	test \! -f $${TMPDIR-/tmp}/$(@F) || tar tzf $${TMPDIR-/tmp}/$(@F) >/dev/null || rm $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || curl -Lo $@ http://www.gtlib.gatech.edu/pub/apache//commons/io/binaries/commons-io-2.3-bin.tar.gz
	test \! -f $@ || tar tzf $@ >/dev/null || rm $@
	test -f $@ || curl -Lo $@ http://archive.apache.org/dist/commons/io/binaries/commons-io-2.3-bin.tar.gz
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
	touch $@

JARS += postgresql-9.1-902.jdbc4.jar

postgresql-9.1-902.jdbc4.jar:
	test \! -f $${TMPDIR-/tmp}/$(@F) || jar tf $${TMPDIR-/tmp}/$(@F) >/dev/null || rm $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://jdbc.postgresql.org/download/$(@F) || curl -Lo $@ http://jdbc.postgresql.org/download/$(@F)
	test \! -f $@ || jar tf $@ >/dev/null || rm $@
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)


JARS += jconn2-5.5.jar

jconn2-5.5.jar:
	test \! -f $${TMPDIR-/tmp}/$(@F) || jar tf $${TMPDIR-/tmp}/$(@F) >/dev/null || rm $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://oodt.jpl.nasa.gov/download/private/sybase/jconn2/5.5/$(@F) || curl -Lo $@ http://oodt.jpl.nasa.gov/download/private/sybase/jconn2/5.5/$(@F)
	test \! -f $@ || jar tf $@ >/dev/null || rm $@
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)

JARS += mysql-connector-java-5.1.14.jar

mysql-connector-java-5.1.14.jar:
	test \! -f $${TMPDIR-/tmp}/$(@F) || jar tf $${TMPDIR-/tmp}/$(@F) >/dev/null || rm $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://mirrors.ibiblio.org/maven2/mysql/mysql-connector-java/5.1.14/$(@F) || curl -o $@ http://mirrors.ibiblio.org/maven2/mysql/mysql-connector-java/5.1.14/$(@F)
	test \! -f $@ || jar tf $@ >/dev/null || rm $@
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)



BUILT_SOURCES := $(JARS)



check-local: $(JARS)
	for i in $<; do jar tf $$i >/dev/null || exit 1; done

JAVAROOT = bld
#BUILT_SOURCES += javaroot.stamp

