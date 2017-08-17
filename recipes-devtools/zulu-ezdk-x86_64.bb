
PV = "1.8.0"
PV_UPDATE = "131"
VERSION = "8.21.0.1"
BUILD_NUMBER = "8.0.131"
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
LIC_FILES_CHKSUM = "file://zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="http://cdn.azul.com/zulu/bin/zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "1931ed3beedee0b16fb7fd37e069b162"
SRC_URI[sha256sum] = "17218c6bdd608b5714ffba9d5e28522bb2efc309266ba46232b8b918e6e62133"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
  cp -a ${S}/zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}/* ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
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

