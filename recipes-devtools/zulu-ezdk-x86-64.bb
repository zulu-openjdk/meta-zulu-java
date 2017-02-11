
PV = "1.8.0"
PV_UPDATE = "91"
BUILD_NUMBER = "8.0.91"
SUFFIX = "linux_x64"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 64 bit Intel architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://zulu8.14.0.1-jdk${BUILD_NUMBER}-${SUFFIX}/LICENSE;md5=7b4baeedfe2d40cb03536573bc2c89b1"

SRC_URI="http://cdn.azul.com/zulu/bin/zulu8.14.0.1-jdk${BUILD_NUMBER}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "0c9ab2a48b13941e70cacf8bdd00d51c"
SRC_URI[sha256sum] = "66faeba9f310cb2cbfa783dea38251b0a57509d8d297d46ffa7a6d18f764dcff"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
  cp -a ${S}/zulu8.14.0.1-jdk${BUILD_NUMBER}-${SUFFIX}/* ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/zulu-${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

