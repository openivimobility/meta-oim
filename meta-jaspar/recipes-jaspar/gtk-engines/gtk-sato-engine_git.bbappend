FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI_append += " file://gtkrc-vga "
PR = "r59"

do_install_append() {
  install -d ${D}${datadir}/themes/Sato/gtk-2.0
  cp ${WORKDIR}/gtkrc-vga ${D}${datadir}/themes/Sato/gtk-2.0/gtkrc
}
