SUMMARY = "Provision by plugging in a USB Drive"
LICENSE = "CLOSED"

SRC_URI = " \
  file://usb-key-provision.service \
  file://usb-key-provision \
  file://media-provision.mount \
  "

inherit systemd allarch

SYSTEMD_SERVICE_${PN} = "\
  media-provision.mount \
  usb-key-provision.service \
  "

do_install() {
  # Systemd Services
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/usb-key-provision.service ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/media-provision.mount ${D}${systemd_unitdir}/system

  # Script to copy from /media/provision to /etc
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/usb-key-provision ${D}${bindir}

}
