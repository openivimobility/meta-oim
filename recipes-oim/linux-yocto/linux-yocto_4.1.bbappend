
PR="r5"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "\
	file://wlan.cfg \
	file://bt.cfg \
	file://tether.cfg \
	file://touchscreen.cfg \
	file://usbmon.cfg \
	file://qemu-vga.cfg \
	file://mmc.cfg \
	"
LINUX_VERSION_EXTENSION = "-ats"
