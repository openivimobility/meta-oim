FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "\
            file://add-debug-flag.patch \
           "
PR="2"
DEPENDS += "libnl1"
