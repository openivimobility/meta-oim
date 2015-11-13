DESCRIPTION = "OpenIVI Mobility HTML5 environment"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4641e94ec96f98fabc56ff9cc48be14b"

inherit pkgconfig cmake_qt5

PR="0"

SRCREV = "${AUTOREV}"
PV = "0.1+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/openivimobility/openivi-html5.git"

# SYSTEMD_SERVICE_${PN} = "sota-client.service sota-client.timer"

RDEPENDS_${PN} = "qtbase qtwebkit qtbase-fonts"
DEPENDS = "qtbase-native qtbase qtwebkit "

# inherit systemd

S = "${WORKDIR}/git"

FILES_${PN} = "/usr/bin/openivi-html5 /usr/share/openivi/*"

do_install() {
  install -d ${D}${bindir}
  install -m 0755 openivi-html5 ${D}${bindir}

  install -d ${D}${datadir}/openivi/
  cp -r ${S}/example ${D}${datadir}/openivi/
}
