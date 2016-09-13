SUMMARY = "A TCP/IP Daemon simplifying the communication with GPS devices"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d217a23f408e91c94359447735bc1800"
DEPENDS = "dbus dbus-glib ncurses python libusb1 chrpath-replacement-native"
RDEPENDS_${PN} = "udev"

PROVIDES = "virtual/gpsd"

EXTRANATIVEPATH += "chrpath-native"

SRC_URI = "${SAVANNAH_GNU_MIRROR}/${BPN}/${BP}.tar.gz \
    file://60-gpsd.rules \
    file://gpsd.service \
    file://gpsd.socket \
    file://0001-SConstruct-prefix-includepy-with-sysroot-and-drop-sy.patch \
    file://0004-SConstruct-disable-html-and-man-docs-building-becaus.patch \
"

SRC_URI[md5sum] = "68691b5de4c94f82ec4062b042b5eb63"
SRC_URI[sha256sum] = "03579af13a4d3fe0c5b79fa44b5f75c9f3cac6749357f1d99ce5d38c09bc2029"

inherit scons systemd

PACKAGECONFIG[qt] = "qt='yes',qt='no',qt4-x11-free"
EXTRA_OESCONS = " \
    sysroot=${STAGING_DIR_TARGET} \
    libQgpsmm='false' \
    python='no' \
    debug='true' \
    strip='false' \
    chrpath='yes' \
    bluez='false' \
    qt='no' \
    systemd='yes' \
    manbuild='no' \
    ${EXTRA_OECONF} \
"


do_install_append() {
    #support for udev
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-gpsd.rules ${D}/${sysconfdir}/udev/rules.d
    install -d ${D}${base_libdir}/udev/
    install -m 0755 ${S}/gpsd.hotplug ${D}${base_libdir}/udev/

    #support for systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/${BPN}.service ${D}${systemd_unitdir}/system/${BPN}.service
    install -m 0644 ${WORKDIR}/${BPN}.socket ${D}${systemd_unitdir}/system/${BPN}.socket
}

FILES_${PN}-dev += " \
  ${libdir}/pkgconfig/libgpsd.pc \
  ${libdir}/pkgconfig/libgps.pc \
  ${libdir}/libQgpsmm.prl"


FILES_${PN}_append = "\
  ${libdir}/libgpsd.so.* \
  ${libdir}/libgps.so.* \
  ${bindir}/gpsctl \
  ${base_libdir}/udev \
  ${sysconfdir}/udev/* \
  "

SYSTEMD_SERVICE_${PN} = "${BPN}.service ${BPN}.socket"
