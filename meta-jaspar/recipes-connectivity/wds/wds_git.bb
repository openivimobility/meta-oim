SUMMARY = "Wireless Display Software For Linux OS (WDS)"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=cb8aedd3bced19bd8026d96a8b6876d7"

SRC_URI = "git://github.com/advancedtelematic/wds.git \
	file://wds_fix_pthread_linking.patch \
	file://increase_timeout.patch \
	"

PV= "0.1.0+gitr${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "wpa-supplicant gstreamer1.0 connman"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DCMAKE_SKIP_RPATH=1"

do_install_append() {
	mkdir ${D}/usr/bin
	install -m 0755 sink/sink-test ${D}/usr/bin
}
