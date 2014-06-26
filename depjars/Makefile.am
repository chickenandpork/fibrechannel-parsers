depjardir = @datadir@/@PACKAGE@/jars
dist_depjar_DATA = $(JARS)


EXTRA_DIST = $(nodist_pkgdata_JAVA)
CONFIG_CLEAN_FILES = $(JARS)

AM_JAVACFLAGS= -cp $(top_builddir)/$(subdir)/$(JAVAROOT)

BUILDTMP=$(shell date +%s)

JARS := commons-io-2.3.jar
BUILT_SOURCES := $(JARS)


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



check-local: $(JARS)
	for i in $<; do jar tf $$i >/dev/null || exit 1; done

JAVAROOT = bld
#BUILT_SOURCES += javaroot.stamp

