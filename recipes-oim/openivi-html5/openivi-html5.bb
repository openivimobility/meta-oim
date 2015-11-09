DESCRIPTION = "OpenIVI Mobility HTML5 environment"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4641e94ec96f98fabc56ff9cc48be14b"

inherit pkgconfig cmake_qt5

PR="0"

SRCREV = "8d2be065a0122046546c0c226f72a01d8b02eb02"
PV = "0.0+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/cajun-rat/openivi-html5.git"

# SYSTEMD_SERVICE_${PN} = "sota-client.service sota-client.timer"

RDEPENDS_${PN} = "qtbase qtwebkit qtbase-fonts"
DEPENDS = "qtbase-native qtbase qtwebkit "

# inherit systemd

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 openivi-html5 ${D}${bindir} 
}
