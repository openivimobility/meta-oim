DESCRIPTION = "ATS Headunit demo"
SECTION = "base"
LICENSE = "CLOSED"

SRC_URI = "git://git@github.com/advancedtelematic/html5-headunit.git;protocol=ssh;branch=v1-old-gray-ui;rev=0196140f8dbbd190f6c912ec8023ec257dc50727"

inherit pkgconfig systemd
PR = "r0"

S = "${WORKDIR}/git"

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
