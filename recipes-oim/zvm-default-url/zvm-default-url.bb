SUMMARY = "Setting the ZVM homepage in the optional OpenIVI homepage config file"
LICENSE = "CLOSED"

SRC_URI = "file://openivi_homepage"

RDEPENDS_${PN} += "openivi-html5"

do_install() {
  install -d "${D}${sysconfdir}/"
  install -m 0644 "${WORKDIR}/openivi_homepage" "${D}/${sysconfdir}"
}
