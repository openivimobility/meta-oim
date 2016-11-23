SUMMARY = "ZVM image"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image
inherit image-repo-manifest

APPEND+="console=ttyS0,115200 quiet"

# We only use the ISO image
NOHDD = "1"

IMAGE_INSTALL_append = " \
	kernel-modules \
	linux-firmware \
	connman \
	connman-tools \
	connman-client \
	qtbase \
	qtbase-plugins \
	qtposition-gpsd \
	openivi-html5 \
	zvm-default-url \
	xinput \
	vpn-config \
	ssh-keys \
	zvm-gpsd-config \
	lrzsz \
	"

IMAGE_INSTALL_append = "${@bb.utils.contains('DISTRO_FEATURES', 'sota', '', 'ota-plus-demo-provision', d)}"
