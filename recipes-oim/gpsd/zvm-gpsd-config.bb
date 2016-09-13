SUMMARY = "Configuration for GPS on ttyUSB0"
LICENSE = "CLOSED"

RDEPENDS_${PN} = "gpsd"

SRC_URI = " \
  file://gps-config \
  "

inherit allarch

do_install() {
  install -d ${D}${sysconfdir}
  install -m 0644 ${WORKDIR}/gps-config ${D}${sysconfdir}/gps-config
}
