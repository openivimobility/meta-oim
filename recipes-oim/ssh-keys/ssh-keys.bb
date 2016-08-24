SUMMARY = "Default SSH master keys"
LICENSE = "CLOSED"


SRC_URI = "file://authorized_keys"

inherit allarch

FILES_${PN} = "${ROOT_HOME}/.ssh/authorized_keys"

do_install() {
  install -d ${D}${ROOT_HOME}/.ssh
  install -m 0644 ${WORKDIR}/authorized_keys ${D}${ROOT_HOME}/.ssh
}
