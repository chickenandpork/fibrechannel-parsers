nodist_pkgdata_DATA = @PACKAGE@.jar

CONFIG_CLEAN_FILES = @PACKAGE@.jar

BUILDTMP=$(shell date +%s)

JAVAROOT = bld


@PACKAGE@.jar: ../java/@PACKAGE@.jar ../depjars/commons-io-2.3.jar @GETOPTJAR@ ../depjars/jconn2.jar ../depjars/postgresql-9.1-902.jdbc4.jar ../depjars/mysql-connector-java-5.1.14.jar ../depjars/ssh.jar ../depjars/slf4j-api-1.7.5.jar ../depjars/slf4j-simple-1.7.5.jar ../depjars/bcpg-jdk15on-151.jar ../depjars/bcpkix-jdk15on-151.jar ../depjars/bcprov-jdk15on-151.jar
	@-rm -fr $(@D)/.workdir
	@mkdir -p $(@D)/.workdir/META-INF/services
	rm -fr $(@D)/.workdir/META-INF/{maven,LICENSE.txt,MANIFEST.MF,{SUN_MICR,TESTKEY,BCKEY}.{DSA,RSA,SF}}
	@echo "Manifest-Version: 1.0" > $(@D)/.workdir/MANIFEST.MF
	@echo "Created-By: @PACKAGE@-@VERSION@" >> $(@D)/.workdir/MANIFEST.MF
	echo "Main-Class: org.smallfoot.parser.FCParser"  >> $(@D)/.workdir/MANIFEST.MF
	ln -f $^ $(@D)/.workdir/
	for i in $(^F) ; do (cd $(@D)/.workdir  && jar xf $$i) ; done
	(cd $(@D)/.workdir && find * -name Driver.class -print) | sed -e 's@/@.@g' -e 's/.class$$//g' > $(@D)/.workdir/META-INF/services/java.sql.Driver
	find $(@D)/.workdir '(' -name \*.java -o -name COPYING.LIB -o -name \*.html -o -name README -o -name Makefile -o -name ChangeLog -o -name GetoptDemo.class -o -name LANGUAGES -o -name \*.xml ')' -exec rm {} \;
	rm -fr $(@D)/.workdir/org/bouncycastle/jcajce/provider/asymmetric
	(cd $(@D)/.workdir && jar cm MANIFEST.MF META-INF/services/java.sql.Driver com gnu net* org*) > $@
	rm -fr $(@D)/.workdir
	touch $@

check-local: @PACKAGE@.jar
	java -jar $< --check

