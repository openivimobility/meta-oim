
PV = "0.2.8.6"
SRC_URI = "https://www.torproject.org/dist/tor-${PV}.tar.gz"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0106cccf286dd2d2841ae80e315b73b4"

SRC_URI[md5sum] = "195e4b3f8d19ca2cd816f1e826b61f86"
SRC_URI[sha256sum] = "3dc9fc02f7cd22ed5fce707e0d9b26a72b1bd0976766a804cb13078d32e3ab5a"

S = "${WORKDIR}/tor-${PV}"

EXTRA_OECONF = "--disable-tool-name-check"
inherit autotools

DEPENDS = "libevent systemd libcap openssl"

do_install_append() {
  install -d ${D}/var/lib/tor
}
