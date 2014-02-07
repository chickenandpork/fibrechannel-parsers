DIST_SUBDIRS =	depjars java convjars tests-autotest
SUBDIRS =	depjars
if DO_JAVA
SUBDIRS +=	java convjars
endif
SUBDIRS +=	tests-autotest

ACLOCAL_AMFLAGS = -I m4

# if missing mkinstalldirs install-sh trigger errors, ignore the result code on the "system (cp ...)" command in automake
dnl CONFIG_CLEAN_FILES = aclocal.m4 config.guess config.sub config.h config.h.in ltconfig ltmain.sh missing mkinstalldirs install-sh Doxyfile @PACKAGE@.spec @PACKAGE@.jar changes.atom
EXTRA_DIST = @PACKAGE@.spec gitdescribe



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
formatting: java/Alias4Parser.java.in java/NicknameParser.java.in java/ParserTee.java java/ZoneParser.java java/ZPAliasEntry.java java/ZPFCAliasEntry.java java/ZPZoneEntry.java
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
gitdescribe: .git/ORIG_HEAD
	git describe > $@
endif DO_GITFORMATTING

if HAVE_RPMBUILD
rpm: dist-bzip2
	@RPMBUILD@ --define 'skipcheck 1' -ta $(PACKAGE)-$(VERSION).tar.bz2
endif
