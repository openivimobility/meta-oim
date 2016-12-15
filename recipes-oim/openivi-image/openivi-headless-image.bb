SUMMARY =  "Headless OpenIVI image"

IMAGE_FEATURES += "package-management ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image
inherit image-repo-manifest

APPEND+="console=ttyS0,115200 quiet"

# We only use the ISO image
NOHDD = "1"

# In 512 byte blocks
BOOTIMG_EXTRA_SPACE = "2000000"

IMAGE_INSTALL_append = " \
	kernel-modules \
	linux-firmware \
	ssh-keys \
	connman \
	connman-client \
	ldd \ 
	"

# If debug-tweaks is set, don't start the tor service automatically, because it
# exposes ssh to the world, and debug-tweaks allows no-password root logins
# IMAGE_INSTALL_append = "${@bb.utils.contains('EXTRA_IMAGE_FEATURES', 'debug-tweaks', '', ' tor-config', d)}"

IMAGE_INSTALL_append = "${@bb.utils.contains('DISTRO_FEATURES', 'sota', '', ' ota-plus-demo-provision', d)}"
