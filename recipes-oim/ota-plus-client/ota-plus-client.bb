DESCRIPTION = "ota-plus-client rust recipe"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=1;endline=2;md5=e5b4c4b4ef35489d85664eeb98d16a49"

EXTERNALSRC = "${THISDIR}/../../../../client/ota-plus-client"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

inherit systemd cargo externalsrc

PR="10"

BBCLASSEXTEND = "native"

FILES_${PN} = " \
                /usr/bin/ota_plus_client \
                ${base_libdir}/systemd/system/ota-plus-client.service \
              "

SYSTEMD_SERVICE_${PN} = "ota-plus-client.service"

DEPENDS += " openssl "
RDEPENDS_${PN} = " libcrypto libssl "

do_install() {
  install -d ${D}${bindir}
  install -m 0755 target/x86_64-poky-linux/release/ota_plus_client ${D}${bindir}

  install -d ${D}${systemd_unitdir}/system
  install -c ${S}/pkg/yocto/ota-plus-client.service ${D}${systemd_unitdir}/system
}
