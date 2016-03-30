DESCRIPTION = "OpenIVI Mobility HTML5 environment"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=4;endline=7;md5=8d2127b230519ce4ad2c16070bdeb7b3"

SRCREV = "${PV}"
SRC_URI = "git://git@github.com/advancedtelematic/jaspar.git;protocol=ssh \
           file://org.jaspar.conf \
           file://jaspar.service \
          "
FILESEXTRAPATHS_prepend := "${THISDIR}:"

PR="0"

S = "${WORKDIR}/git"

FILES_${PN} = "/usr/share/jaspar/ivi-connection-manager/phone_configuration.py \
               /usr/share/jaspar/ivi-connection-manager/* \
               /usr/share/jaspar/browser.sh \
               /usr/share/jaspar/monitor.py \
               /usr/share/jaspar/assets/* \
               /usr/share/jaspar/version \
               /usr/share/themes/Sato/gtk-2.0/*.png \
               /usr/share/themes/Sato/gtk-2.0/*.xbm \
               /usr/share/themes/Sato/gtk-2.0/*.svg \
               /usr/local/bin/*.sh \
               /usr/local/bin/car_dealer_dialog.py \
               /usr/share/jaspar/epiphany-profile/ \
               /usr/share/applications/browser.desktop \
               /usr/share/applications/navigation.desktop \
               /usr/share/applications/radio.desktop \
               /usr/share/applications/streaming.desktop \
               /usr/share/matchbox/vfolders/jaspar.directory \
               ${sysconfdir}/dbus-1/system.d/org.jaspar.conf \
               ${base_libdir}/systemd/system/jaspar.service \
              "

SYSTEMD_SERVICE_${PN} = "jaspar.service"

DEPENDS = " systemd"

RDEPENDS_${PN} += " \
  systemd \
  epiphany \
  xserver-common \
  xinit \
  dzen2 \
  bash \
  python-dbus \
  python-ctypes \
  python-pyusb \
  python-pybluez \
  python-pyserial \
  python-pygobject \
  python-importlib \
  python-subprocess \
  python-pygtk \
"

inherit systemd

# Copy script to the deploy area with u-boot, uImage, and rootfs
do_install () {
  install -d ${D}${datadir}/jaspar/
  install -d ${D}${datadir}/jaspar/epiphany-profile/

  install -d ${D}${datadir}/applications/
  cp ${S}/*.desktop ${D}${datadir}/applications/
  install -d ${D}/usr/share/matchbox/vfolders
  install ${S}/jaspar.directory ${D}/usr/share/matchbox/vfolders

  cp -r ${S}/ivi-connection-manager/ ${D}${datadir}/jaspar/
  cp ${S}/monitor.py ${D}${datadir}/jaspar/

  install -d ${D}${datadir}/jaspar/assets
  cp ${S}/assets/* ${D}${datadir}/jaspar/assets
  install -d ${D}${datadir}/themes/Sato/gtk-2.0
  cp ${S}/assets/* ${D}${datadir}/themes/Sato/gtk-2.0

  install -d ${D}${prefix}/local/
  install -d ${D}${prefix}/local/bin/
  cp ${S}/*.sh ${D}${prefix}/local/bin/
  cp ${S}/ivi-connection-manager/car_dealer_dialog.py ${D}${prefix}/local/bin/

  install -D ${WORKDIR}/org.jaspar.conf ${D}${sysconfdir}/dbus-1/system.d/org.jaspar.conf

  install -d ${D}${systemd_unitdir}/system
  install -c ${WORKDIR}/jaspar.service ${D}${systemd_unitdir}/system

  echo ${SRCPV} | awk -F+ '{ print $2 }' > ${D}${datadir}/jaspar/version
}
