dnl ***********************************************************************
dnl Process this file with autoconf to produce a configure script.
dnl ***********************************************************************
#AC_INIT(install-sh)
AC_INIT(fcparsers, 0.3)
AC_CONFIG_MACRO_DIR([m4])

AC_CANONICAL_HOST

AM_INIT_AUTOMAKE([foreign dist-bzip2])
AC_CONFIG_TESTDIR([tests-autotest])

dnl ***********************************************************************
dnl Checks for programs used during the build
dnl ***********************************************************************
dnl AC_PROG_CC
dnl AC_PROG_CXX
AC_PROG_INSTALL
AC_PROG_MAKE_SET

AC_SUBST(AUTOM4TE, autom4te)

SVN_CHECK_JDK([1.6],[1.6])
AM_CONDITIONAL(DO_JAVA, test "$JAVAC" != none)

AC_PATH_PROGS(BYACC, [yacc.macosx byaccj], [missing byacc], $PATH:$HOME/bin)
if test "x$BYACC" = "xmissing byacc"; then echo "BYACC available at http://byaccj.sourceforge.net/#download"; fi
AM_CONDITIONAL(DO_BYACC, true)
AM_CONDITIONAL(DO_CHECKLOCAL_PARSER, false)
dnl AM_CONDITIONAL(DO_BYACC, test "$BYACC" != "missing byacc")

dnl ***********************************************************************
dnl Checks for programs used during test, markup, diagrams. etc.  I'm a huge fan
dnl of automation, and if I can type a single command to make, check, package,
dnl and push a new release, HECK YEAH!
dnl 
dnl This was based on my work for the fctools project (nee vitools) so some may
dnl prune out in the near future
dnl ***********************************************************************
AC_PATH_PROGS(PATHAWK, [awk gawk nawk], [/bin/awk])
AC_PATH_PROGS(XMLLINT, [xmllint], [missing xmllint])
AC_ARG_WITH(xmllintcommentindent,
[AS_HELP_STRING([--with-xmllintcommentindent],[Set XML-comment indentation fudge such as xmllint-2.6.x/2.7.x differences; default=detect])],
[
    case "$withval" in
	"yes") WITH_XMLLINTINDENT="  " ;;
	"no")  WITH_XMLLINTINDENT=""  ;;
	*) case $(xmllint --version 2>&1 | awk '/version/ {print $NF}') in
	    206*) WITH_XMLLINTINDENT="" ;;
	    *) WITH_XMLLINTINDENT="  "  ;;
	    esac ;;
    esac
],
[
    case $(xmllint --version 2>&1 | awk '/version/ {print $NF}') in
	206*) WITH_XMLLINTINDENT="" ;;
	*) WITH_XMLLINTINDENT="  "  ;;
    esac
])
AC_SUBST([XMLLINTINDENT], ${WITH_XMLLINTINDENT})

AC_PATH_PROGS(XSLTPROC, [xsltproc], [missing xsltproc])
AC_PATH_PROGS(TIDY, [tidy], [missing tidy])
AC_PATH_PROGS(BZCAT, [bzcat], [missing bzcat])
AC_PATH_PROGS(SEVENZIP, [7za 7z keka7z], [missing 7za], $PATH:/Applications/Keka.app/Contents/Resources)
AC_PATH_PROGS(FILE, [file], [missing file])
AC_ARG_WITH(doxygen,
[AS_HELP_STRING([--with-doxygen],[Indicate Doxygen allowing a without-; (default=search)])],
[
    case "$withval" in
	""|"yes") 
		AC_PATH_PROGS(DOXYGEN, [doxygen], missing, $PATH:/Applications/Doxygen.app/Contents/Resources)
		AC_PATH_PROGS(FOP, [fop], missing)
		;;
	"no")  DOXYGEN=missing ;;
	*) DOXYGEN=$withval ;;
    esac

    case "$DOXYGEN" in
	"missing") HAVE_DOXYGEN_MARKDOWN=no ;;
	*)
	    case `$DOXYGEN --version` in
		1.[0-7]* ) HAVE_DOXYGEN_MARKDOWN=no;;
		*) HAVE_DOXYGEN_MARKDOWN=yes;;
	    esac ;;
    esac
],
[
	DOXYGEN=missing 
	HAVE_DOXYGEN_MARKDOWN=no
])
AC_SUBST(HAVE_DOXYGEN_MARKDOWN)
AM_CONDITIONAL(DO_DOXYGEN_MD, test "x$HAVE_DOXYGEN_MARKDOWN" = "xyes")

AC_PATH_PROGS(DOT, [dot], missing, $PATH:/usr/local/bin:/usr/local/graph*/bin)
AC_SUBST(PATHDOT, [`dirname "${DOT}"`])
AM_CONDITIONAL(DO_DOT, test "x$DOT" != "xmissing")
AM_CONDITIONAL(DO_DOXYGEN, test "x$DOXYGEN" != "xmissing" -a "x$DOT" != "xmissing")
if test "x$DOT" != "xmissing"; then HAVE_DOT=yes; else HAVE_DOT=no; fi
AC_SUBST(HAVE_DOT)
AC_PATH_PROGS(ASTYLE, [astyle], missing, $PATH:$HOME/bin)
AM_CONDITIONAL(DO_ASTYLE, test "x$ASTYLE" != "xmissing")
AC_PATH_PROGS(RPMBUILD, [rpmbuild], [missing rpmbuild])
AM_CONDITIONAL(HAVE_RPMBUILD, [test "x$RPMBUILD" != "xmissing rpmbuild"])


AC_PATH_PROGS(GIT, [git], [missing])
AC_MSG_CHECKING([version agreement with git tag])
BUILDID=$( (git describe; cat gitdescribe) 2>/dev/null |head -1| cut -d "-" -f 2)
CHKVER=$( (git describe; cat gitdescribe) 2>/dev/null |head -1| cut -d "-" -f 1)
if test "${CHKVER}" = "${VERSION}"
then 
  AC_MSG_RESULT([ok])
else
  AC_MSG_WARN([version mismatch])
fi
AC_SUBST(BUILDID)


AC_CHECK_PROG(SVN, [svn], [svn],[missing svn])
AM_CONDITIONAL(DO_SVNLOG, test "$SVN" != "missing svn" -a "$XSLTPROC" != "missing xsltproc" -a -f .svn/entries -a -f /usr/share/cnp/svnlog2atom.xsl)
AM_CONDITIONAL(DO_GITFORMATTING, test "$GIT" != "missing git" -a -f .git/config)
AC_PATH_PROGS(ECHO, [echo], [missing])
AC_PATH_PROGS(NC, [nc], [missing])
AC_PATH_PROGS(SED, [sed], [missing])
AC_PATH_PROGS(BASE64, [base64], [missing])
AM_CONDITIONAL(HAVE_BASE64, [test "x$BASE64" != "xmissing"])
AC_PATH_PROGS(N2A, [native2ascii])

AC_SUBST(yumrepos, [$sysconfdir/yum.repos.d])
AC_SUBST(faebase, [/home/services])
AC_SUBST(emaildest, [ftpupdates])
AC_SUBST(httpconfd, [/etc/httpd/conf.d])
AC_SUBST(localreposroot, [/var/ftp])

AC_ARG_WITH(distname,
[AS_HELP_STRING([--with-distname],[Define a repository name; default=guess])],
[
    DISTNAME=$withval
],
[
    DISTNAME=$(cat /etc/redhat-release /dev/null 2>/dev/null | awk 'BEGIN {REL=""} /^CentOS release/ { REL=$1$3} END { if ("" == REL) print "unknown"; else print REL} ')
])

AC_SUBST(localreposversion, ${DISTNAME})


AC_ARG_ENABLE(autotest,
[AS_HELP_STRING([--enable-autotest],[Enable autotest testcases; breaks: make distcheck; default=disabled])],
[
    case "$enableval" in
	"yes") ENAB=yes ;;
	"no")  ENAB=no  ;;
	*) ENAB=no ;;
    esac
],
[
    ENAB=no
])
AM_CONDITIONAL(DO_AUTOTEST, test "$ENAB" = yes)


AC_ARG_WITH(getoptplus,
[AS_HELP_STRING([--with-getoptplus],[Enable inclusion of an improved getopt .jar; default=search])],
[
    case "$withval" in
	"yes") WITH_GETOPTJAR=$((locate /getopt.jar) | grep -v .workdir | head -1) ;;
	"no")  WITH_GETOPTJAR=no  ;;
	*) WITH_GETOPTJAR="$withval" ;;
    esac
],
[
AC_MSG_CHECKING([for getopt.jar])
    WITH_GETOPTJAR=$((locate getopt.jar; echo $(pwd)/depjars/getopt.jar) | grep -v .workdir | head -1) ;
AC_MSG_RESULT([${WITH_GETOPTJAR}])
])
AM_CONDITIONAL(DO_GETOPTJAR, test "$WITH_GETOPTJAR" != no)
AM_CONDITIONAL(FETCH_GETOPTJAR, test "x$WITH_GETOPTJAR" = "x${PWD}/depjars/getopt.jar")
AC_SUBST([GETOPTJAR], ${WITH_GETOPTJAR})


AC_ARG_WITH(jconn2jar,
[AS_HELP_STRING([--with-jconn2jar],[Enable inclusion of an improved jconn2 .jar; default=search])],
[
    case "$withval" in
	"yes") WITH_JCONN2JAR=$((locate /jconn2-5.5.jar; echo "yes"; ) | grep -v .workdir | head -1) ;;
	"no")  WITH_JCONN2JAR=no  ;;
	*) WITH_JCONN2JAR="$withval" ;;
    esac
],
[
AC_MSG_CHECKING([for jconn2-5.5.jar])
    WITH_JCONN2JAR=$((locate /jconn2-5.5.jar; echo "yes"; ) | grep -v .workdir | head -1) ;
AC_MSG_RESULT([${WITH_JCONN2JAR}])
])
AM_CONDITIONAL(FETCH_JCONN2JAR, test "$WITH_JCONN2JAR" = yes)
AC_SUBST([JCONN2JAR], ${WITH_JCONN2JAR})


AC_MSG_CHECKING([for user-defined bna testcase])
AC_ARG_WITH(userbnatestcase,
[AS_HELP_STRING([--with-userbnatestcase],[Enable a one-off test using a user-defined testcase; default=ignore])],
[
    USERBNATESTCASE="$withval"
    AC_MSG_RESULT([${USERBNATESTCASE}])
],
[
    USERBNATESTCASE="missing"
    AC_MSG_RESULT([not given])
])
AC_SUBST([USERBNATESTCASE], ${USERBNATESTCASE})


AC_ARG_WITH(sshjar,
[AS_HELP_STRING([--with-sshjar],[Enable inclusion of a sshjar .jar; default=svn co and build])],
[
AC_MSG_CHECKING([for ssh jar])
    case "$withval" in
	"yes") WITH_SSHJAR=ltb-commons-net-ssh ;
	    AC_MSG_RESULT([look-then-build commons-net-ssh])
	    ;;
	"no")  WITH_SSHJAR=build-commons-net-ssh ;
	    AC_MSG_RESULT([build commons-net-ssh])
	    ;;
	*) WITH_SSHJAR=ltb-net-schmizz-sshj
	    AC_MSG_RESULT([look-then-build net-schmizz-sshj])
	    ;;
    esac
],
[
AC_MSG_CHECKING([for ssh jar])
WITH_SSHJAR=ltb-net-schmizz-sshj ;
AC_MSG_RESULT([default: look-then-build net-schmizz-sshj])
])
if test x${WITH_SSHJAR} = xltb-commons-net-ssh
then
    SSHJAR=$((locate /commons-net-ssh.jar) | grep -v .workdir | head -1)
    if test -z "${SSHJAR}" -o \! -f "${SSHJAR}"
    then
	WITH_SSHJAR=build-commons-net-ssh
    fi
fi

if test x${WITH_SSHJAR} = xltb-net-schmizz-sshj
then
    SSHJAR=$((locate /net-schmizz-sshj.jar) | grep -v .workdir | head -1)
    if test -z "${SSHJAR}" -o \! -f "${SSHJAR}"
    then
	WITH_SSHJAR=build-net-schmizz-sshj
    fi
fi

AM_CONDITIONAL(BUILD_COMMONSNETSSHJAR, test "$WITH_SSHJAR" = build-commons-net-ssh)
AM_CONDITIONAL(DO_COMMONSNETSSHJAR, test $(echo "$WITH_SSHJAR"|cut -d - -f2-11) = commons-net-ssh)
AM_CONDITIONAL(DO_NETSCHMIZZSSHJJAR, test $(echo "$WITH_SSHJAR"|cut -d - -f2-11) = net-schmizz-sshj)
AM_CONDITIONAL(BUILD_NETSCHMIZZSSHJJAR, test "$WITH_SSHJAR" = build-net-schmizz-sshj)
case ${WITH_SSHJAR} in
    ltb-*)
        ;;
    *-commons-net-ssh)
	SSHJAR=commons-net-ssh.jar ;;
    *-net-schmizz-sshj)
	SSHJAR=net-schmizz-sshj.jar ;;
    *)
	SSHJAR=net-schmizz-sshj.jar ;;
esac
AC_SUBST([SSHJAR], ${SSHJAR})
AC_MSG_CHECKING([    ssh jar])
AC_MSG_RESULT([${SSHJAR} (${WITH_SSHJAR})])


AC_ARG_WITH(sudopostmove,
[AS_HELP_STRING([--with-sudopostmove],[Enable sudopostmove of a built RPM; default=disabled])],
[
    case "$withval" in
        "no")  WITH_SUDOPOSTMOVE=no  ;;
        *) WITH_SUDOPOSTMOVE="$withval" ;;
    esac
],
[
    WITH_SUDOPOSTMOVE=no
])
AM_CONDITIONAL(DO_SUDOPOSTMOVE, test "$WITH_SUDOPOSTMOVE" != no)
AC_SUBST([SUDOPOSTMOVE], ${WITH_SUDOPOSTMOVE})



AC_CONFIG_FILES(Makefile
  convjars/Makefile 
  depjars/Makefile 
  java/Makefile java/zonefile.y java/bnazone.y java/NicknameParser.java java/TSVParser.java java/Alias4Parser.java java/devicealias.y java/showzone.y java/vw4invalid.y
  java/showzone2.y
  java/BNAPURLConnection.java java/DWHURLConnection.java java/MSDWHURLConnection.java java/OSMURLConnection.java java/DCNMSQLURLConnection.java
  java/CIMXMLCQLURLConnection.java
  java/FCParser.java
  java/SSHCLI.java
  java/SSHURLConnection.java
  java/VCenterURLConnection.java
  java/version.java
  tests-autotest/Makefile tests-autotest/testsuite.at
)

if test "x$RPMBUILD" != "xmissing rpmbuild"
then
  AC_CONFIG_FILES(fcparsers.spec:pkg/rpm/fcparsers.spec.in)
fi

if test "x$DOXYGEN" != "xmissing"
then
  AC_CONFIG_FILES(Doxyfile:pkg/Doxyfile.in)
fi

AC_OUTPUT
