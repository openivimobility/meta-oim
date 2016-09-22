SUMMARY = "VPN configuration"
LICENSE = "CLOSED"

RDEPENDS_${PN} = "openvpn"

SRC_URI = " \
  file://ca.crt \
  file://dh2048.pem \
  file://vpn.conf \
  file://vpn.service \
  "

inherit systemd allarch
SYSTEMD_SERVICE_${PN} += "vpn.service"

do_install() {
  # Systemd Service
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/vpn.service ${D}${systemd_unitdir}/system

  # VPN Config.  Note that /etc/client.crt and /etc/client.key need to be
  # externally provisioned on a per-device basis
  install -d ${D}${sysconfdir}/openvpn
  install -m 0644 ${WORKDIR}/ca.crt ${D}${sysconfdir}/openvpn
  install -m 0644 ${WORKDIR}/dh2048.pem ${D}${sysconfdir}/openvpn
  install -m 0644 ${WORKDIR}/vpn.conf ${D}${sysconfdir}/openvpn
}
