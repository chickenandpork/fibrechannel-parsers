DIST_SUBDIRS =	depjars java convjars tests-autotest
SUBDIRS =	depjars
if DO_JAVA
SUBDIRS +=	java convjars
endif
SUBDIRS +=	tests-autotest

ACLOCAL_AMFLAGS = -I m4

# if missing mkinstalldirs install-sh trigger errors, ignore the result code on the "system (cp ...)" command in automake
CONFIG_CLEAN_FILES = aclocal.m4 config.guess config.sub missing install-sh Doxyfile @PACKAGE@.spec
CONFIG_STATUS_DEPENDENCIES = gitdescribe
EXTRA_DIST  = @PACKAGE@.spec gitdescribe
EXTRA_DIST += samples/dupealias.fcalias samples/dupealias.shzone samples/fcid-members.shzone samples/hangingmember.zoneshow
EXTRA_DIST += samples/non-fcalias.shzone samples/preambled.devicealias samples/simple-hardzone.zoneshow
EXTRA_DIST += samples/simple-multicfg.zoneshow samples/simple.devicealias samples/simple.shzone samples/simple.zoneshow
EXTRA_DIST += samples/Putty.Log.Die.Die.Die.shzone samples/VW3-4column-bogusname.csv


SOURCES = java/Alias4Parser.java.in java/FCParser.java.in java/NicknameParser.java.in java/ParserTee.java java/VW4InvalidAddedParser.java java/ZoneParser.java java/ZPAliasEntry.java java/ZPFCAliasEntry.java java/ZPZoneEntry.java java/ZoneParser.java java/ZoneParserI.java java/SwappedNicknameParser.java

if DO_DOXYGEN
doc: htdocs/README.dox Doxyfile $(SOURCES) htdocs/@PACKAGE@.jar
	$(DOXYGEN)
	@if test \! -d $(<D)/.git; then echo "subdir $(<D) not set as a gh-pages branch; see http://rickfoosusa.blogspot.com/2011/10/howto-use-doxygen-with-github.html"; git branch|grep gh-pages || echo git clone --branch gh-pages $$(git config --get remote.origin.url) $(<D); grep $(<D) .gitignore || echo "echo $(<D) > .gitignore"; echo "then delete everything in $(<D), commit, and push"; exit 1; fi
	@grep $(<D) .gitignore || { echo "echo $(<D) > .gitignore"; exit 1; }
	(cd $(<D) && git add -A)
	if test -n "$(DOXYMESSAGE)" ; then (cd $(<D) && git commit -a -m "$(DOXYMESSAGE)" && git push); fi

htdocs/README.dox: README.md Makefile
	mkdir -p $(@D)
if DO_DOXYGEN_MD
	awk 'BEGIN { print "@file "; print "@mainpage JavaDoc API Markup for @PACKAGE@"; } { printf "%s\n",$$0; } END {print "@tableofcontents" }' $< > $@
else
	awk 'BEGIN { print "/** @file "; print "@mainpage JavaDoc API Markup for @PACKAGE@"; } /@PACKAGE@$$/ {next} /^=+$$/ {next} { printf " * %s\n",$$0; } END {print "@tableofcontents"; print " */" }' $< > $@
endif DO_DOXYGEN_MD_FALSE
endif

htdocs/@PACKAGE@.jar: convjars/@PACKAGE@.jar
	cp $< $@


if DO_ASTYLE
formatting: $(SOURCES)
if DO_GITFORMATTING
	@git ls-files --other --exclude-standard --directory || { echo "###################### unignored local files: git add, git commit ?"; }
	@git diff --exit-code || { echo "###################### unstaged changes: git commit -a"; exit 1; }
	@git diff --cached --exit-code || { echo "###################### staged uncommitted changes: git commit"; exit 1; }
endif
	$(MAKE) check
	for i in $^; do $(ASTYLE) -A1 $$i; done
	$(MAKE) check
if DO_GITFORMATTING
	git commit -a -m 'formatting and whitespace'
endif DO_GITFORMATTING

endif DO_ASTYLE

if DO_GITFORMATTING
gitdescribe: .git/index
	git describe > $@
endif DO_GITFORMATTING


if HAVE_RPMBUILD
#srpm: distcheck
srpm: re-dist-bzip2
	@mkdir -p $$(rpm --eval '%{_srcrpmdir}')
	@RPMBUILD@ -ts $(PACKAGE)-$(VERSION).tar.bz2
if DO_SUDOPOSTMOVE
	mv -f $$(rpm --eval '%{_srcrpmdir}')/@PACKAGE@-$$(rpm --qf '%{VERSION}\n' -q --specfile @PACKAGE@.spec |head -1)-$$(rpm --qf '%{RELEASE}\n' -q --specfile @PACKAGE@.spec |head -1).src.rpm @SUDOPOSTMOVE@
endif   DO_SUDOPOSTMOVE

if DO_GITFORMATTING
re-dist-bzip2: gitdescribe
	rm -f @PACKAGE@.spec $(PACKAGE)-$(VERSION).tar.bz2
	touch configure
	$(MAKE) dist-bzip2
rpm: re-dist-bzip2
else
rpm: dist-bzip2
endif DO_GITFORMATTING_FALSE
	@RPMBUILD@ --define 'skipcheck 1' -ta $(PACKAGE)-$(VERSION).tar.bz2
endif

buildcheck: dist-bzip2
	rm -fr tests-autotest/testsuite.dir
	make check || cat tests-autotest/testsuite.dir/*/testsuite.log

