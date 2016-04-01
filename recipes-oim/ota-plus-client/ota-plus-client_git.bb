DESCRIPTION = "ota-plus-client rust recipe"
LICENSE = "MIT | Apache-2.0"

SRC_URI = "git://github.com/advancedtelematic/ota-plus-client.git;protocol=ssh"
SRCREV = "${AUTOREV}"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=1;endline=2;md5=e5b4c4b4ef35489d85664eeb98d16a49"

inherit cargo

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

FILES_${PN} = " /usr/bin/ota_plus_client "

do_install() {
  install -d ${D}${bindir}
  install -m 0755 target/x86_64-poky-linux/release/ota_plus_client ${D}${bindir}
}
