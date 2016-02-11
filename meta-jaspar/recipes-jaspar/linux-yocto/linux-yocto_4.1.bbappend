
PR="r4"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "\
	file://wlan.cfg \
	file://bt.cfg \
	file://tether.cfg \
	file://touchscreen.cfg \
	"
LINUX_VERSION_EXTENSION = "-ats"

