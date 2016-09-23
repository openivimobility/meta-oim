SUMMARY = "Tor configuration and systemd service"
LICENSE = "CLOSED"

RDEPENDS_${PN} = "tor"

SRC_URI = " \
  file://torrc \
  file://tor.service \
  "

inherit systemd allarch
SYSTEMD_SERVICE_${PN} = "tor.service"

do_install() {
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/tor.service ${D}${systemd_unitdir}/system
  install -d ${D}${sysconfdir}/tor
  install -m 0644 ${WORKDIR}/torrc ${D}${sysconfdir}/tor
}
