DESCRIPTION = "Implementation of QTPosition using gpsd"
SECTION = "base"
HOMEPAGE = "https://github.com/jmechnich/qtposition_gpsd"
BUGTRACKER = "https://github.com/jmechnich/qtposition_gpsd/issues"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=ddcdb5f9c0e01d2cb21f2735b9876fc1"

# f070a8973 is master as of 2016-09-09
SRCREV = "f070a89733487747c7edbc7e0beb3c2a41ec212c"
SRC_URI = "git://github.com/jmechnich/qtposition_gpsd"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit qmake5

RDEPENDS_${PN} = "qtbase"
DEPENDS = "qtbase qtlocation"

PLUGIN_PATH = "${OE_QMAKE_PATH_PLUGINS}/position"

FILES_${PN} = "${PLUGIN_PATH}/libqtposition_gpsd.so"
FILES_${PN}-dbg += "${PLUGIN_PATH}/.debug/libqtposition_gpsd.so"


do_install() {
  install -d ${D}${PLUGIN_PATH}
  install -m 0755 libqtposition_gpsd.so ${D}${PLUGIN_PATH}
}
