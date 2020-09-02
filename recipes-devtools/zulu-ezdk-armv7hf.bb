
PV = "8"
ZULU_VERSION = "8.48.3.246"
JDK_VERSION = "8.0.265"
SUFFIX = "linux_aarch32hf"
ARCHIVE_NAME = "zulu${ZULU_VERSION}-ca-jdk${JDK_VERSION}-${SUFFIX}"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 32 bit ARM architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${ARCHIVE_NAME}/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="https://cdn.azul.com/zulu-embedded/bin/${ARCHIVE_NAME}.tar.gz"

SRC_URI[md5sum] = "64114fe973a43faf44b4870bcfeb79ac"
SRC_URI[sha256sum] = "210f82991014fb0913133eb3433b370201d95a48c11ac4ace017bb59846feba8"

PR = "u${JDK_VERSION}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${libdir}/jvm
  cp -a ${S}/${ARCHIVE_NAME} ${D}${libdir}/jvm
  install -d -m 0755 ${D}${bindir}
  lnr ${D}${libdir}/jvm/${ARCHIVE_NAME}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

PACKAGE_BEFORE_PN = "${PN}-src"
FILES_${PN}-src = "${libdir}/jvm/${ARCHIVE_NAME}/src.zip"
FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk virtual/java"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

