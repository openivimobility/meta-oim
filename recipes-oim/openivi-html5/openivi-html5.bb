DESCRIPTION = "OpenIVI Mobility HTML5 environment"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4641e94ec96f98fabc56ff9cc48be14b"

EXTERNALSRC = "${THISDIR}/../../../../client/openivi-html5"

inherit pkgconfig cmake_qt5 externalsrc
PV = "0.1"
PR = "r0"


RDEPENDS_${PN} = "qtbase qtwebkit qtbase-fonts"
DEPENDS = "qtbase-native qtbase qtwebkit "

FILES_${PN} = "/usr/bin/openivi-html5 /usr/share/openivi/*"

do_install() {
  install -d ${D}${bindir}
  install -m 0755 openivi-html5 ${D}${bindir}

  install -d ${D}${datadir}/openivi/
  cp -r ${S}/example ${D}${datadir}/openivi/
}
