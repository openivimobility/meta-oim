DESCRIPTION = "OpenIVI Mobility HTML5 environment"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=4;endline=7;md5=4f35c0a2a2fc517a1a824baf204781e3"

SRC_URI = "git://git@github.com/advancedtelematic/jaspar.git;protocol=ssh \
           file://org.jaspar.conf \
           file://jaspar.service \
          "
FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRCREV = "${AUTOREV}"
#PV = "0.1+git${SRCPV}"
PR="50"


#This package doesn't have any files for the rootfs in it, option needed to create an empty 
# package so when the rootfs image is made it finds the mksd_xxx.deb package and doesn't complain
# FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"
S = "${WORKDIR}/git"

FILES_${PN} = "/usr/share/jaspar/ivi-connection-manager/phone_configuration.py \
               /usr/share/jaspar/ivi-connection-manager/* \
               /usr/share/jaspar/browser.sh \
               /usr/local/bin/*.sh \
               /usr/share/jaspar/epiphany-profile/ \
               /usr/share/applications/browser.desktop \
               /usr/share/applications/navigation.desktop \
               /usr/share/applications/radio.desktop \
               /usr/share/applications/pair_bluetooth.desktop \
               ${sysconfdir}/dbus-1/system.d/org.jaspar.conf \
               ${base_libdir}/systemd/system/jaspar.service \
              "

SYSTEMD_SERVICE_${PN} = "jaspar.service"

DEPENDS = " systemd vlc"

RDEPENDS_${PN} += " \
  systemd \
  epiphany \
  xserver-common \
  xinit \
  bash \
  vlc \
  python-dbus \
  python-ctypes \
  python-pyusb \
  python-pybluez \
  python-pyserial \
  python-pygobject \
  python-importlib \
  python-subprocess \
"

inherit systemd

# Copy script to the deploy area with u-boot, uImage, and rootfs
do_install () {
  install -d ${D}${datadir}/jaspar/
  install -d ${D}${datadir}/jaspar/epiphany-profile/

  install -d ${D}${datadir}/applications/
  cp ${S}/*.desktop ${D}${datadir}/applications/

  cp -r ${S}/ivi-connection-manager/ ${D}${datadir}/jaspar/

  install -d ${D}${prefix}/local/
  install -d ${D}${prefix}/local/bin/
  cp ${S}/*.sh ${D}${prefix}/local/bin/

  install -D ${WORKDIR}/org.jaspar.conf ${D}${sysconfdir}/dbus-1/system.d/org.jaspar.conf

  install -d ${D}${systemd_unitdir}/system
  install -c ${WORKDIR}/jaspar.service ${D}${systemd_unitdir}/system
}
