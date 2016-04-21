DESCRIPTION = "Example package for installing in demo"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = \
"file://${WORKDIR}/README.md;beginline=1;endline=2;md5=dbdd220f6e0edf314571241db0b9beb5"

SRC_URI = "file://example.configfile.v1 \
           file://README.md "

FILESEXTRAPATHS_prepend := "${THISDIR}:"
                  
PR="r0"

FILES_${PN} = " \
                /etc/example.configfile \
              "

do_install() {
  install -d ${D}${sysconfdir}/
  install -c ${WORKDIR}/example.configfile.v1 ${D}${sysconfdir}/example.configfile
}
