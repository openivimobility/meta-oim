DESCRIPTION = "ATS Headunit demo"
SECTION = "base"
LICENSE = "CLOSED"

EXTERNALSRC = "${THISDIR}/../../../../client/html5-headunit"

inherit pkgconfig externalsrc systemd
PV = "2.0"
PR = "r0"

SYSTEMD_SERVICE_${PN} = "html5-headunit.service html5-headunit.socket"

RDEPENDS_${PN} = "python python-netserver python-simplejson"

FILES_${PN} = "/usr/share/html5-headunit/*"

do_install() {
  # HTML5
  install -d ${D}${datadir}/html5-headunit/
  cp -r ${S}/www ${D}${datadir}/html5-headunit/

  # proxy, and systemd service to start it
  install ${S}/proxy.py ${D}${datadir}/html5-headunit
  install -d ${D}/${systemd_unitdir}/system
  install -m 644 ${S}/systemd/html5-headunit.service ${D}/${systemd_unitdir}/system
  install -m 644 ${S}/systemd/html5-headunit.socket ${D}/${systemd_unitdir}/system
}
