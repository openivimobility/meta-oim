DESCRIPTION = "Atmel MXT touch testing app" 
SECTION = "base" 
LICENSE = "BSD" 
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8b6acde4490765c7b838377ac61e2d2d" 
PR = "r0" 

S = "${WORKDIR}/git"

SRC_URI = "git://git@github.com/atmel-maxtouch/mxt-app.git;protocol=ssh"
SRCREV = "afd6d0d401ae9c615de09a318b68e3c1af07f888"
inherit autotools


DEPENDS = " libusb1 "
RDEPENDS_${PN} += " libusb1 "
