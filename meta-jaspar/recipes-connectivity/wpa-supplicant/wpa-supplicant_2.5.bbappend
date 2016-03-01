FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "\
            file://add-debug-flag.patch \
           "
PR="5"
DEPENDS += "libnl1"
