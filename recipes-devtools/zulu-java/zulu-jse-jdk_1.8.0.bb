V_UPDATE = "91"
BUILD_NUMBER = "8.14.0.6"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the ARM architecture from Azul \
 Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/ezdk-1.8.0_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32.tar.gz

SRC_URI[md5sum] = "93ae43a706bb4583b56759cce8256e6b"
SRC_URI[sha256sum] = "ad204157dd34fe95c8dd3a0b83b6b1a3327019b90d2c14f33bd151917a5ad78a"

PR =. "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/ezdk${PV}_${PV_UPDATE}
  cp -a ${S}/ezdk${PV}_${PV_UPDATE} ${D}${datadir}/
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/ezdk${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "java2-runtime java2-vm"
PROVIDES += "virtual/java"
