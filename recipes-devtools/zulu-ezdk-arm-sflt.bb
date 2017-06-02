
PV = "1.8.0"
PV_UPDATE = "102"
BUILD_NUMBER = "8.17.0.19"
SUFFIX = "eval-linux_aarch32sf"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 32 bit ARM architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}/LICENSE;md5=7b4baeedfe2d40cb03536573bc2c89b1"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "f902e3a2b8616f8cfc021c69be427148"
SRC_URI[sha256sum] = "e44f2cba3ebf56f99288a9d6e199ffb6f8376d74116fc44c777bf7f32e9026fa"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/ezdk-${PV}_${PV_UPDATE}
  cp -a ${S}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}/* ${D}${datadir}/ezdk-${PV}_${PV_UPDATE}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/ezdk-${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

FILES_${PN}-doc = " \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/jre/lib/*/*.diz \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/jre/lib/*.diz \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/jre/lib/*/client/*.diz \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/jre/lib/*/jli/*.diz \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/man/ \
	"

FILES_${PN}-dev = " \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/src.zip \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/demo/ \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/sample/ \
	${datadir}/ezdk-${PV}_${PV_UPDATE}/include/ \
	"