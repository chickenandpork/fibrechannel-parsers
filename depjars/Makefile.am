depjardir = @datadir@/@PACKAGE@/jars
dist_depjar_DATA = $(JARS)

dist_data_DATA = jconn2-5.5.jar mysql-connector-java-5.1.14.jar postgresql-9.1-902.jdbc4.jar

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



#dnl com.jcraft.jzlib BSD
jzlib-1.1.2.zip:
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || curl -Lo $@ https://github.com/ymnk/jzlib/archive/1.1.2.zip
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
	touch $@

jzlib-1.1.2.jar: jzlib-1.1.2.zip
	test \! -d $$(basename $(@F) .jar) || rm -r $$(basename $(@F) .jar)
	unzip -o $<
	test -d $$(basename $(@F) .jar)-bld || mkdir -p $$(basename $(@F) .jar)-bld
	$(JAVAC) -d $$(basename $(@F) .jar)-bld $$(find $$(basename $(@F) .jar) -name *.java)
	(cd $$(basename $(@F) .jar)-bld && jar cf ../$(@F) com)


JARS += commons-net-ssh.jar

if BUILD_COMMONSNETSSHJAR
#dnl Apache License-2.0
commons-net-ssh.jar: slf4j-api-1.7.5.jar jzlib-1.1.2.jar bcpkix-jdk15on-149.jar bcprov-jdk15on-149.jar bcpg-jdk15on-149.jar
	test -d commons-net-ssh-read-only || svn co http://commons-net-ssh.googlecode.com/svn/ commons-net-ssh-read-only
	test -d $$(basename $(@F) .jar) || mkdir -p $$(basename $(@F) .jar)
	$(JAVAC) -cp slf4j-api-1.7.5.jar:jzlib-1.1.2.jar:bcpkix-jdk15on-149.jar:bcprov-jdk15on-149.jar -d $$(basename $(@F) .jar) $$(find commons-net-ssh-read-only/src/main/java/org -name *.java)
	(cd $$(basename $(@F) .jar) && jar cf ../$(@F) org)
else
if BUILD_NETSCHMIZZSSHJJAR
#dnl Apache License-2.0
https://codeload.github.com/hierynomus/sshj/zip/master
commons-net-ssh.jar: 
	test \! -f $${TMPDIR-/tmp}/$(@F) || jar tf $${TMPDIR-/tmp}/$(@F) >/dev/null || rm $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ https://codeload.github.com/hierynomus/sshj/zip/master || curl -o $@ https://codeload.github.com/hierynomus/sshj/zip/master
	test \! -f $@ || jar tf $@ >/dev/null || rm $@
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
else
commons-net-ssh.jar: @COMMONSNETSSHJAR@
	cp $< $@
endif
endif

JARS += bcprov-jdk15on-149.jar bcpkix-jdk15on-149.jar bcpg-jdk15on-149.jar

#dnl org.bouncycastle BSD-like requires copyright notice
# http://www.bouncycastle.org/download/bcprov-jdk15on-149.jar
# http://polydistortion.net/bc/download/bcprov-jdk15on-149.jar
# http://www.bouncycastle.org/download/bcpkix-jdk15on-149.jar
bcprov-jdk15on-149.jar bcpkix-jdk15on-149.jar bcpg-jdk15on-149.jar:
	test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || curl -Lo $@ http://www.bouncycastle.org/download/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
	touch $@


JARS += slf4j-api-1.7.5.jar slf4j-simple-1.7.5.jar

slf4j-api-1.7.5.jar slf4j-simple-1.7.5.jar: slf4j-1.7.5.zip
	BASE=$$(echo $$(basename $< .zip)); unzip -o -j $< $${BASE}/$@ && touch $@

slf4j-1.7.5.zip:
	test -f $${TMPDIR-/tmp}/$(@F) && unzip -t $${TMPDIR-/tmp}/$(@F) >/dev/null 2>&1 || rm -f $${TMPDIR-/tmp}/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) && ln $${TMPDIR-/tmp}/$(@F) $@ || true
	test \! -f $@ && test -f $${TMPDIR-/tmp}/$(@F) && cp $${TMPDIR-/tmp}/$(@F) $@ || true
	test -f $@ || wget -O $@ http://www.slf4j.org/dist/$(@F) || curl -Lo $@ http://www.slf4j.org/dist/$(@F)
	test -f $${TMPDIR-/tmp}/$(@F) || cp $@ $${TMPDIR-/tmp}/$(@F)
	-touch $@





BUILT_SOURCES := $(JARS)



check-local: $(JARS)
	for i in $<; do jar tf $$i >/dev/null || exit 1; done

clean-local:
	rm -f commons-io-2.3-bin.tar.gz

JAVAROOT = bld
#BUILT_SOURCES += javaroot.stamp
