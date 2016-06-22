DESCRIPTION = "Connman configuration for wireless networks"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=1;endline=2;md5=6d3fed36f73fadcb03d8cb78ac3b3236"

EXTERNALSRC = "${THISDIR}/../../../../client/connman-config"

inherit externalsrc

PR="10"

BBCLASSEXTEND = "native"

FILES_${PN} = " ${localstatedir}/lib/connman/openivi-wifi.config \
		${systemd_unitdir}/system/connman-openivi-wifi.service \
                ${bindir}/connman-openivi-wifi \
              "

DEPENDS += " connman "

do_install() {
  install -d ${D}${bindir}
  install -m 755 ${S}/connman-openivi-wifi ${D}${bindir}

  install -d ${D}${localstatedir}/lib/connman
  install -c ${S}/openivi-wifi.config ${D}${localstatedir}/lib/connman

  install -d ${D}${systemd_unitdir}/system
  install -c ${S}/connman-openivi-wifi.service ${D}${systemd_unitdir}/system
}

