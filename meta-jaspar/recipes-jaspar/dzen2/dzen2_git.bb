DESCRIPTION = "dzen is a general purpose messaging, notification and menu program."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=6b46e938a20b07191fa895000af16167"
PR = "r0"

SRC_URI = "git://github.com/robm/dzen.git;branch=master; \
    file://dzen-extras.tar.gz \
    file://0001-Update-build-configurations.patch \
"
SRCREV = "488ab66019f475e35e067646621827c18a879ba1"

DEPENDS = "virtual/libx11 libxft"
RDEPENDS_${PN} = "ttf-dejavu-sans xrandr"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "all -C ${S}"

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} = "\
    ${bindir}/dzen2 \
    ${bindir}/dzen-battery \
    ${datadir}/dzen/* \
    "
